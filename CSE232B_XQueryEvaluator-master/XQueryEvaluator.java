import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.regex.*;
import java.util.stream.Collectors;


public class XQueryEvaluator extends XQueryBaseVisitor<LinkedList<Node>>{
    Document output = null;
    HashMap<String, LinkedList<Node>> context = new HashMap<>();
    Stack<HashMap<String, LinkedList<Node>>> contextStack = new Stack<>();

    public Document getoutput() {
        return this.output;
    }

    private LinkedList<Node> getAllDesc(LinkedList<Node> curNodes) {
        Set<Node> result = new HashSet<>();
        for(int i = 0; i < curNodes.size(); i++) {
            Node node = curNodes.get(i);
            getDescHelper(result, node);
        }
        return new LinkedList<>(result);
    }

    private void getDescHelper(Set<Node> result, Node node) {
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            result.add(child);
            getDescHelper(result, child);
        }
    }

    private LinkedList<Node> getAllChildren(Node node) {
        Set<Node> result = new HashSet<>();
        NodeList children = node.getChildNodes();
        for(int j = 0; j < children.getLength(); j ++) {
            result.add(children.item(j));
        }
        return new LinkedList<>(result);
    }

    @Override
    public LinkedList<Node> visitXqVar(XQueryParser.XqVarContext ctx) {
        return this.context.get(ctx.var().getText());
    }

    @Override
    public LinkedList<Node> visitXqStr(XQueryParser.XqStrContext ctx) {
        String literal = ctx.STR().getText();
        String text = literal.substring(1, literal.length() - 1);
        Node newNode = output.createTextNode(text);
        return new LinkedList<>(Arrays.asList(newNode));
    }

    @Override
    public LinkedList<Node> visitXqAp(XQueryParser.XqApContext ctx) {
        return XPathMain.evaluateXPathAp(ctx.getText());
    }

    @Override
    public LinkedList<Node> visitXqParen(XQueryParser.XqParenContext ctx) {
        return visit(ctx.xq());
    }

    @Override
    public LinkedList<Node> visitXqConcat(XQueryParser.XqConcatContext ctx) {
        Set<Node> res = new HashSet<>();
        res.addAll(visit(ctx.xq(0)));
        res.addAll(visit(ctx.xq(1)));
        return new LinkedList<>(res);
    }

    @Override
    public LinkedList<Node> visitXqDirectChild(XQueryParser.XqDirectChildContext ctx) {
        LinkedList<Node> curNodes = new LinkedList<>(visit(ctx.xq()));
        return XPathMain.evaluateXPathRp(new LinkedList<>(curNodes), ctx.rp().getText()); 
    }

    @Override
    public LinkedList<Node> visitXqIndirectChild(XQueryParser.XqIndirectChildContext ctx) {
        LinkedList<Node> curNodes = new LinkedList<>(visit(ctx.xq()));
        curNodes.addAll(getAllDesc(curNodes));
        return XPathMain.evaluateXPathRp(curNodes, ctx.rp().getText()); 
    }

    @Override 
    public LinkedList<Node> visitXqTag(XQueryParser.XqTagContext ctx) { 
        String tagName = ctx.NAME(0).getText();
        LinkedList<Node> res = visit(ctx.xq());
        Node tagNode = output.createElement(tagName);

        for(Node elementNode : res) {
            tagNode.appendChild(output.importNode(elementNode, true));
        }
        return new LinkedList<>(Arrays.asList(tagNode));
    }
    
    @Override 
    public LinkedList<Node> visitXqClause(XQueryParser.XqClauseContext ctx) { 
        // TODO: Diffrentiate the result for join-optimization
        try{
            String returnedQuery = generateRewriteJoin(ctx);
            if (returnedQuery != null) {
                // Need rewrite
                System.out.println("Rewritten Query: \n" + returnedQuery + "\n");
                return XQueryMain.evaluateXQueryJoin(returnedQuery);
            } 
        }  catch(Exception e) {
            System.err.println(e);
        }

        HashMap<String, LinkedList<Node>> curContext = new HashMap<>(this.context);
        LinkedList<Node> res = new LinkedList<>();
        this.contextStack.push(curContext);
        visitXqClauseHelper(res, 0, ctx.forclause().xq().size(), ctx);
        this.context = this.contextStack.pop();
        return res;
    }

    public void visitXqClauseHelper(LinkedList<Node> res, int varIdx, int forVarsCnt, XQueryParser.XqClauseContext ctx) {
        // finished for, start evaluate let, where and return
        if(varIdx > forVarsCnt - 1) {
            if(ctx.letclause() != null) {
                visit(ctx.letclause());
            }

            if(ctx.whereclause() == null || !visit(ctx.whereclause()).isEmpty()) {
                res.addAll(visit(ctx.returnclause()));
            }

            return;
        }

        // recursively check for variables
        LinkedList<Node> curRes = visit(ctx.forclause().xq(varIdx));
        for(Node node : curRes) {
            HashMap<String, LinkedList<Node>> curContext = new HashMap<>(this.context);
            this.contextStack.push(curContext);
            this.context.put(ctx.forclause().var(varIdx).getText(), new LinkedList<>(Arrays.asList(node)));
            visitXqClauseHelper(res, varIdx + 1, forVarsCnt, ctx);
            this.context = this.contextStack.pop();
        }
    }

    class Group {
        Set<String> keys = new HashSet<>();
        List<String> queries = new ArrayList<>();
        List<String> conds = new ArrayList<>();
        final int id;

        Group(String key, String query, int id) {
            this.keys.add(key);
            this.queries.add(query);
            this.id = id;
        } 

        boolean contains(String var) {
            return this.keys.contains(var);
        }

        void add(String key, String query) {
            this.keys.add(key);
            this.queries.add(query);
        }

        void addCond(String cond) {
            this.conds.add(cond);
        }
    }

    class GroupNode {
        Group value;
        Map<Integer, GroupNode> children = new HashMap<>();
        Map<Integer, List<String>> myVar = new HashMap<>();
        Map<Integer, List<String>> otherVar = new HashMap<>();

        GroupNode(Group g) {
            this.value = g;
        }

        String dump() {
            String query = "for " + String.join(", ", this.value.queries) + " ";
            if (!this.value.conds.isEmpty()) {
                query += "where " + String.join(", ", this.value.conds) + " ";
            }
            query += "return <tuple>{" + this.value.keys.stream().map(key -> String.format("<%s>{%s}</%s>", key.substring(1), key, key.substring(1))).collect(Collectors.joining(", ")) + "}</tuple>";
            return query;
        }

        String generateTag(int id) {
            return "["+ this.myVar.get(id).stream().map(s -> s.substring(1)).collect(Collectors.joining(", ")) + "], [" + 
                        this.otherVar.get(id).stream().map(s -> s.substring(1)).collect(Collectors.joining(", ")) + "]";
        }
    }

    private String generateRewriteJoin(XQueryParser.XqClauseContext ctx) {
        // Initialize groups and var map
        List<Group> groups = new ArrayList<>();
        Map<String, Integer> varMap = new HashMap<>();

        groupForClause(ctx, groups, varMap);

        // check if there is need to join
        if(groups.size() < 2) {
            return null;
        }

        // Make copy of groups into group node
        Map<Integer, GroupNode> groupNodes = new HashMap<>();
        for (int i = 0; i < groups.size(); i++) {
            groupNodes.put(i, new GroupNode(groups.get(i)));
        }

        groupWhereClause(ctx, groupNodes, varMap);

        String returnedQuery = generateJoinQuery(ctx, groupNodes);

        if (returnedQuery == null) {
            return null;
        }

        return returnedQuery;
    }

    private void groupForClause(XQueryParser.XqClauseContext ctx, List<Group> groups, Map<String, Integer> varMap) {
        // group up vars for each xq in join clause
        for(int i = 0; i < ctx.forclause().xq().size(); i++) {
            String xq = ctx.forclause().xq(i).getText();
            String leftVar = ctx.forclause().var(i).getText();
            String rightVar = xq.split("/")[0];

            // The start is not another var
            if (rightVar.charAt(0) != '$') {
                Group group = new Group(leftVar, leftVar + " in " + xq, groups.size());
                groups.add(group);
                varMap.put(leftVar, group.id);
                continue;
            }

            Group group = groups.get(varMap.get(rightVar));
            group.add(leftVar, leftVar + " in " + xq);
            varMap.put(leftVar, group.id);
        }
    }

    private void groupWhereClause(XQueryParser.XqClauseContext ctx, Map<Integer, GroupNode> groupNodes, Map<String, Integer> varMap) {
        // split out single condition
        String[] whereConds = ctx.whereclause().cond().getText().split("and");

        // for each condition
        for (String cond : whereConds) {
            String[] operands = cond.split("=|eq");

            String leftOp = operands[0];
            String rightOp = operands[1];

            // If both operands are string literal, trival case
            if (leftOp.charAt(0) != '$' && rightOp.charAt(0) != '$') {
                continue;
            }
            
            // One operand is string literal, append the cond into the group directly
            if (leftOp.charAt(0) != '$' || rightOp.charAt(0) != '$') {
                if (leftOp.charAt(0) == '$') {
                    groupNodes.get(varMap.get(leftOp)).value.addCond(leftOp + " eq " + rightOp);//cond);
                } else {
                    groupNodes.get(varMap.get(rightOp)).value.addCond(leftOp + " eq " + rightOp);//cond);
                }
                continue;
            }

            // Both operands are var
            Integer leftGroupId = varMap.get(leftOp);
            Integer rightGroupId = varMap.get(rightOp);

            GroupNode leftNode = groupNodes.get(leftGroupId);
            GroupNode rightNode = groupNodes.get(rightGroupId);

            // Add each other as children
            leftNode.children.put(rightGroupId, rightNode);
            rightNode.children.put(leftGroupId, leftNode);
            
            // Two group are not synced before
            if (leftNode.myVar.get(rightGroupId) == null) {
                leftNode.myVar.put(rightGroupId, new ArrayList<>(Arrays.asList(leftOp)));
                leftNode.otherVar.put(rightGroupId, new ArrayList<>(Arrays.asList(rightOp)));
                rightNode.myVar.put(leftGroupId, new ArrayList<>(Arrays.asList(rightOp)));
                rightNode.otherVar.put(leftGroupId, new ArrayList<>(Arrays.asList(leftOp)));
            } else {
                leftNode.myVar.get(rightGroupId).add(leftOp);
                leftNode.otherVar.get(rightGroupId).add(rightOp);
                rightNode.myVar.get(leftGroupId).add(rightOp);
                rightNode.otherVar.get(leftGroupId).add(leftOp);
            }
        }
    }

    private String generateJoinQuery(XQueryParser.XqClauseContext ctx, Map<Integer, GroupNode> groupNodes) {

        String query = "";

        GroupNode root = groupNodes.get(0);

        // Remove root from others' children
        for (int i = 1; i < groupNodes.size(); i++) {
            groupNodes.get(i).children.remove(0);
        }

        while (!root.children.isEmpty()) {
            // Get the next node
            Map.Entry<Integer, GroupNode> entry = root.children.entrySet().iterator().next();
            Integer childId = entry.getKey();
            GroupNode childNode = entry.getValue();

            String g1;
            String g2;

            if (query.isEmpty()) {
                g1 = root.dump();
            } else {
                g1 = query;
            }

            g2 = childNode.dump();

            query = "join (" + g1 + ", " + g2 + ", " + root.generateTag(childId) + ")";

            // Absorb child into root
            for (GroupNode node : groupNodes.values()) {
                node.children.remove(childId);
            }

            for (int key : childNode.children.keySet()) {
                if (root.children.containsKey(key)) {
                    root.myVar.get(key).addAll(childNode.myVar.get(key));
                    root.otherVar.get(key).addAll(childNode.otherVar.get(key));
                } else {
                    root.children.put(key, childNode.children.get(key));
                    root.myVar.put(key, childNode.myVar.get(key));
                    root.otherVar.put(key, childNode.otherVar.get(key));
                }
            }
        }

        query = "for $tuple in " + query;

        // return
        query += " return ";
        String returnClause = ctx.returnclause().xq().getText();
        Pattern regex = Pattern.compile("\\$[a-zA-Z0-9]+");
		Matcher regexMatcher = regex.matcher(returnClause);
        while (regexMatcher.find()) {
            // Fetching Group from String
            String varName = regexMatcher.group(0);
            String varNameWithoutDollar = varName.substring(1);
            
            returnClause = returnClause.replaceAll("\\" + varName, 
                                        String.format("\\$tuple/%s/*", varNameWithoutDollar));
        }
        
        query += returnClause;
        return query;
    }

    @Override
    public LinkedList<Node> visitXqLet(XQueryParser.XqLetContext ctx) {
        HashMap<String, LinkedList<Node>> curContext = new HashMap<>(this.context);
        this.contextStack.push(curContext);
        LinkedList<Node> res = visitChildren(ctx);
        this.context = this.contextStack.pop();
        return res;
    }

    @Override 
    public LinkedList<Node> visitForclause(XQueryParser.ForclauseContext ctx) { 
        return null;
    }

    @Override 
    public LinkedList<Node> visitLetclause(XQueryParser.LetclauseContext ctx) { 
        for(int i = 0; i < ctx.var().size(); i++) {
            LinkedList<Node> res = visit(ctx.xq(i));
            this.context.put(ctx.var(i).getText(), new LinkedList<>(res));
        }
        return null;
    }

    @Override
    public LinkedList<Node> visitWhereclause(XQueryParser.WhereclauseContext ctx) { 
        return visit(ctx.cond());
    }

    @Override
    public LinkedList<Node> visitReturnclause(XQueryParser.ReturnclauseContext ctx) { 
        return visit(ctx.xq());
    }

    @Override
    public LinkedList<Node> visitJoinclause(XQueryParser.JoinclauseContext ctx) { 
        // result tuples
        LinkedList<Node> res = new LinkedList<>();

        // get tuples
        LinkedList<Node> joinRes0 = visit(ctx.xq(0));
        LinkedList<Node> joinRes1 = visit(ctx.xq(1));
        
        // build hashtable on result of first xq, value -> list of tuples
        Map<String, LinkedList<Node>> hashtable = buildHashtable(ctx.varArr(0), joinRes0);
        
        // match result
        XQueryParser.VarArrContext varArr1 = ctx.varArr(1);
        for(Node tuple1 : joinRes1) {
            LinkedList<Node> tupleElements = getAllChildren(tuple1);
            String key = "";

            for(int i = 0; i < varArr1.NAME().size(); i ++) {
                for(Node ele1 : tupleElements) {  
                    String varName = varArr1.NAME(i).getText();
                    String nodeName = ele1.getNodeName();
                    
                    // check if it is the element to join, pick values as key in hashtable
                    if(varName.equals(nodeName)) {
                        String eleValue = ele1.getFirstChild().getTextContent();
                        key += eleValue;
                    }
                }
            }

            // cartisian product
            if(hashtable.containsKey(key)) {
                LinkedList<Node> tuples0 = new LinkedList<Node>(hashtable.get(key));
                // add results as tuples to result documents
                for(Node node : tuples0){
                    Node outputNode = output.createElement("tuple");
                    
                    // for xq0
                    for(Node ele : tupleElements) {
                        outputNode.appendChild(output.importNode(ele, true));
                    }

                    // for xq1
                    for(Node ele : getAllChildren(node)) {
                        outputNode.appendChild(output.importNode(ele, true));
                    }
                    
                    res.add(outputNode);
                }
            }
        }

        return res;
    }

    private HashMap<String, LinkedList<Node>> buildHashtable(XQueryParser.VarArrContext varArr,  
                                                             LinkedList<Node> tuples) {
        HashMap<String, LinkedList<Node>> hashtable = new HashMap<>();
        for(Node tuple : tuples) {
            String key = "";
            LinkedList<Node> tupleElements = getAllChildren(tuple);
            

            for(int i = 0; i < varArr.NAME().size(); i ++) {
                for(Node ele : tupleElements) {
                    String varName = varArr.NAME(i).getText();
                    String nodeName = ele.getNodeName();
                    
                    // check if it is the element to join, pick values as key in hashtable
                    if(varName.equals(nodeName)) {
                        String eleValue = ele.getFirstChild().getTextContent();
                        key += eleValue;
                    }
                }
            }

            if(!hashtable.containsKey(key)) {
                hashtable.put(key, new LinkedList<>());
            }
            
            hashtable.get(key).add(tuple);
        }

        return hashtable;
    }

    @Override 
    public LinkedList<Node> visitCondEq1(XQueryParser.CondEq1Context ctx) { 
        LinkedList<Node> res = new LinkedList<>();

        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes0 = visit(ctx.xq(0));
        LinkedList<Node> visitRes1 = visit(ctx.xq(1));
        for(Node resNode0 : visitRes0) {
            for(Node resNode1 : visitRes1) {
                if(resNode0.isEqualNode(resNode1)) {
                    res.add(resNode0);
                }
            }
        }
        
        return res;
    }

    @Override 
    public LinkedList<Node> visitCondEq2(XQueryParser.CondEq2Context ctx) { 
        LinkedList<Node> res = new LinkedList<>();

        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes0 = visit(ctx.xq(0));
        LinkedList<Node> visitRes1 = visit(ctx.xq(1));
        for(Node resNode0 : visitRes0) {
            for(Node resNode1 : visitRes1) {
                if(resNode0.isEqualNode(resNode1)) {
                    res.add(resNode0);
                }
            }
        }
        
        return res;
    }

    @Override 
    public LinkedList<Node> visitCondIs(XQueryParser.CondIsContext ctx) { 
        LinkedList<Node> res = new LinkedList<>();

        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes0 = visit(ctx.xq(0));
        LinkedList<Node> visitRes1 = visit(ctx.xq(1));
        for(Node resNode0 : visitRes0) {
            for(Node resNode1 : visitRes1) {
                if(resNode0.isSameNode(resNode1)) {
                    res.add(resNode0);
                }
            }
        }
        
        return res;
    }

    @Override
    public LinkedList<Node> visitCondEmpty(XQueryParser.CondEmptyContext ctx) {
        LinkedList<Node> visitRes = visit(ctx.xq());
        LinkedList<Node> res = new LinkedList<>();

        if(visitRes.isEmpty()) {
            res.add(output.createTextNode("hehe"));
        }

        return res;
    }

    @Override 
    public LinkedList<Node> visitCondSome(XQueryParser.CondSomeContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        HashMap<String, LinkedList<Node>> curContext = new HashMap<>(this.context);
        this.contextStack.push(curContext);
        visitCondSomeHelper(0, ctx.var().size(), ctx, res);
        this.context = this.contextStack.pop();
        return res;
    }

    private void visitCondSomeHelper(int varIdx, int varSize, XQueryParser.CondSomeContext ctx, LinkedList<Node> res) {
        if(varIdx > varSize - 1) {
            res.addAll(visit(ctx.cond()));
            return;
        }

        // recursively check for variables
        LinkedList<Node> curRes = visit(ctx.xq(varIdx));
        for(Node node : curRes) {
            HashMap<String, LinkedList<Node>> curContext = new HashMap<>(this.context);
            this.contextStack.push(curContext);
            this.context.put(ctx.var(varIdx).getText(), new LinkedList<>(Arrays.asList(node)));
            visitCondSomeHelper(varIdx + 1, varSize, ctx, res);
            this.context = this.contextStack.pop();
        }
    }

    @Override 
    public LinkedList<Node> visitCondParen(XQueryParser.CondParenContext ctx) { 
        return visit(ctx.cond());
    }

    @Override 
    public LinkedList<Node> visitCondAnd(XQueryParser.CondAndContext ctx) { 
        LinkedList<Node> res = new LinkedList<>();
        if(visit(ctx.cond(0)).isEmpty() || visit(ctx.cond(1)).isEmpty()) {
            return new LinkedList<>();
        }
        res.addAll(visit(ctx.cond(0)));
        res.addAll(visit(ctx.cond(1)));
        return res; 
    }

    @Override 
    public LinkedList<Node> visitCondOr(XQueryParser.CondOrContext ctx) { 
        LinkedList<Node> res = new LinkedList<>();
        res.addAll(visit(ctx.cond(0)));
        res.addAll(visit(ctx.cond(1)));
        return res;
    }

    @Override 
    public LinkedList<Node> visitCondNot(XQueryParser.CondNotContext ctx) { 
        LinkedList<Node> visitRes = visit(ctx.cond());
        LinkedList<Node> res = new LinkedList<>();

        if(visitRes.isEmpty()) {
            res.add(output.createTextNode("hehe"));
        }

        return res;
    }
}
