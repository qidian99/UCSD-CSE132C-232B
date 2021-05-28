import java.util.LinkedList;

import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

    
public class XPathEvaluator extends XPathBaseVisitor<LinkedList<Node>> {
    LinkedList<Node> curNodes = new LinkedList<>();
    Document xmlDoc = null;

    public Document getXmlDoc() {
        return this.xmlDoc;
    }

    /**
     * Take an xml file name, return an Document Object
     * @param fileName
     * @return parsed document
     */
    private Document parseXML(String fileName) {

        try {
            File xmlFile = new File(fileName);

            // init builder
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            // parse file
            xmlDoc = builder.parse(xmlFile);
            xmlDoc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return xmlDoc;
    }

    /**
     * Get descendents of current nodes
     * @return descendents
     */
    private LinkedList<Node> getAllDesc() {
        Set<Node> result = new HashSet<>();
        for(int i = 0; i < this.curNodes.size(); i++) {
            Node node = this.curNodes.get(i);
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

    /**
     * Get all direct children of curNodes
     * @return children
     */
    private LinkedList<Node> getAllChildren() {
        Set<Node> result = new HashSet<>();
        for(int i = 0; i < this.curNodes.size(); i ++) {
            NodeList children = this.curNodes.get(i).getChildNodes();
            for(int j = 0; j < children.getLength(); j ++) {
                result.add(children.item(j));
            }
        }
        return new LinkedList<>(result);
    }

    /**
     * Get all direct parent of curNodes
     * @return children
     */
    private LinkedList<Node> getAllParent() {
        // Use set to prevent duplicates
        Set<Node> result = new HashSet<>();
        for(int i = 0; i < this.curNodes.size(); i++) {
            Node node = curNodes.get(i);
            result.add(node.getParentNode());
        }
        return new LinkedList<>(result);
    }

    @Override 
    public LinkedList<Node> visitDirectChild(XPathParser.DirectChildContext ctx) { 
        String fileName = ctx.NAME().getText();
        Document xmlDoc = parseXML(fileName);

        // Error condition
        if (xmlDoc == null) {
            return new LinkedList<>();
        }
        
        this.curNodes.add(xmlDoc);
        return visit(ctx.rp());
    }

    @Override
    public LinkedList<Node> visitIndirectChild(XPathParser.IndirectChildContext ctx) { 
        String fileName = ctx.NAME().getText();
        Document xmlDoc = parseXML(fileName);

        // Error condition
        if (xmlDoc == null) {
            return new LinkedList<>();
        }

        this.curNodes.add(xmlDoc);

        // add all desendents
        this.curNodes.addAll(getAllDesc());

        return visit(ctx.rp()); 
    }

    // modified
    @Override public LinkedList<Node> visitTagName(XPathParser.TagNameContext ctx) { 
        LinkedList<Node> res = new LinkedList<>();
        for (Node node: getAllChildren()) {
            if (node.getNodeName().equals(ctx.NAME().getText())) {
                res.add(node);
            }
        }
        this.curNodes = res;
        return res;
    }

    @Override 
    public LinkedList<Node> visitAll(XPathParser.AllContext ctx) { 
        LinkedList<Node> res = getAllChildren();
        this.curNodes = res;
        return res;
    }

    @Override public LinkedList<Node> visitCurrent(XPathParser.CurrentContext ctx) { 
        return this.curNodes;
    }

    @Override public LinkedList<Node> visitParent(XPathParser.ParentContext ctx) { 
        LinkedList<Node> res = getAllParent();
        this.curNodes = res;
        return res;
    }

    // TODO: Need to filter non-text node or not?
    @Override public LinkedList<Node> visitText(XPathParser.TextContext ctx) { 
        LinkedList<Node> res = getAllChildren();
        this.curNodes = res;
        return res;
    }

    @Override public LinkedList<Node> visitAttr(XPathParser.AttrContext ctx) { 
        String attrName = ctx.NAME().getText();
        LinkedList<Node> res = new LinkedList<>();
        for(int i = 0; i < this.curNodes.size(); i ++) {
            Node node = curNodes.get(i);
            if (node.hasAttributes()) {
                String attribute = ((Element)node).getAttribute(attrName);
                if (attribute.length() > 0) {
                    res.add(node);
                }
            }
        }

        this.curNodes = res;
        return res; 
    }

    @Override public LinkedList<Node> visitParenRP(XPathParser.ParenRPContext ctx) { 
        return visit(ctx.rp());
    }

    // modified rp / rp
    @Override public LinkedList<Node> visitDirectChildRP(XPathParser.DirectChildRPContext ctx) { 
        // Update curNodes with visit rp 0
        visit(ctx.rp(0));
        return visit(ctx.rp(1));
    }

    // modified rp // rp
    // is short for /descendant-or-self::node()/
    @Override public LinkedList<Node> visitIndirectChildRP(XPathParser.IndirectChildRPContext ctx) { 
        visit(ctx.rp(0));
        this.curNodes.addAll(getAllDesc());
        return visit(ctx.rp(1));
    }

    // modified rp [ f ]
    @Override public LinkedList<Node> visitFilter(XPathParser.FilterContext ctx) { 
        visit(ctx.rp());
        LinkedList<Node> original = this.curNodes;
        LinkedList<Node> res = new LinkedList<>();

        for (Node n : original) {
            this.curNodes = new LinkedList<>(List.of(n));
            // treat f as boolean
            if (!visit(ctx.f()).isEmpty()) {
                res.add(n);
            }
        }

        // side effect
        this.curNodes = res;
        return res;
    }

    @Override public LinkedList<Node> visitConcat(XPathParser.ConcatContext ctx) { 
        Set<Node> res = new HashSet<>();
        LinkedList<Node> original = this.curNodes;
        res.addAll(visit(ctx.rp(0)));
        this.curNodes = original;
        res.addAll(visit(ctx.rp(1)));
        this.curNodes = new LinkedList<>(res);
        return curNodes;
    }

    @Override public LinkedList<Node> visitRpFt(XPathParser.RpFtContext ctx) {
        return visit(ctx.rp()); 
    }

    @Override public LinkedList<Node> visitEq1(XPathParser.Eq1Context ctx) { 
        LinkedList<Node> original = this.curNodes;
        LinkedList<Node> res = new LinkedList<>();

        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes0 = visit(ctx.rp(0));
        this.curNodes = original;
        LinkedList<Node> visitRes1 = visit(ctx.rp(1));
        this.curNodes = original;
        for(Node resNode0 : visitRes0) {
            for(Node resNode1 : visitRes1) {
                if(resNode0.isEqualNode(resNode1)) {
                    res.add(resNode0);
                }
            }
        }
  
        return res;
    }

    @Override public LinkedList<Node> visitEq2(XPathParser.Eq2Context ctx) { 
        LinkedList<Node> original = this.curNodes;
        LinkedList<Node> res = new LinkedList<>();

        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes0 = visit(ctx.rp(0));
        this.curNodes = original;
        LinkedList<Node> visitRes1 = visit(ctx.rp(1));
        this.curNodes = original;
        for(Node resNode0 : visitRes0) {
            for(Node resNode1 : visitRes1) {
                if(resNode0.isEqualNode(resNode1)) {
                    res.add(resNode0);
                }
            }
        }
  
        return res;
    }

    @Override public LinkedList<Node> visitStr(XPathParser.StrContext ctx) {
        LinkedList<Node> original = this.curNodes;
        LinkedList<Node> res = new LinkedList<>();

        String text = ctx.STR().getText();
        text = text.substring(1, text.length() - 1);
        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes = visit(ctx.rp());
        this.curNodes = original;
        for(Node resNode : visitRes) {
            if(resNode.getNodeType() == Node.TEXT_NODE && resNode.getTextContent().equals(text)) {
                res.add(resNode);
            }
        }
  
        return res;  
    }


    @Override public LinkedList<Node> visitIs(XPathParser.IsContext ctx) { 
        LinkedList<Node> original = this.curNodes;
        LinkedList<Node> res = new LinkedList<>();

        // return cur set of nodes after find one pair of elements equal
        LinkedList<Node> visitRes0 = visit(ctx.rp(0));
        this.curNodes = original;
        LinkedList<Node> visitRes1 = visit(ctx.rp(1));
        this.curNodes = original;
        for(Node resNode0 : visitRes0) {
            for(Node resNode1 : visitRes1) {
                if(resNode0.isSameNode(resNode1)) {
                    res.add(resNode0);
                }
            }
        }

        return res;  
    }

    @Override public LinkedList<Node> visitParenFt(XPathParser.ParenFtContext ctx) { 
        return visit(ctx.f()); 
    }

    // TODO: double check
    @Override public LinkedList<Node> visitOr(XPathParser.OrContext ctx) {
        LinkedList<Node> res = new LinkedList<>();
        res.addAll(visit(ctx.f(0)));
        res.addAll(visit(ctx.f(1)));
        return res;
    }

    // TODO: double check
    @Override public LinkedList<Node> visitAnd(XPathParser.AndContext ctx) { 
        LinkedList<Node> res = new LinkedList<>();
        if(visit(ctx.f(0)).isEmpty() || visit(ctx.f(1)).isEmpty()) {
            return new LinkedList<>();
        }
        res.addAll(visit(ctx.f(0)));
        res.addAll(visit(ctx.f(1)));
        return res;
    }

    @Override public LinkedList<Node> visitNot(XPathParser.NotContext ctx) { 
        LinkedList<Node> original = new LinkedList<>(curNodes);
        LinkedList<Node> res = visit(ctx.f());
        if(!res.isEmpty()) {
            return new LinkedList<>();
        }
        return original;//this.curNodes;
    }
}
