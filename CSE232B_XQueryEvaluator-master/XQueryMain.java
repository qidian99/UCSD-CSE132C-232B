import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTree;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import java.util.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;

public class XQueryMain {
    public static void trimWhitespace(Node node) {
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if(child.getNodeType() == Node.TEXT_NODE) {
                child.setTextContent(child.getTextContent().trim());
            }
            trimWhitespace(child);
        }
    }

    private static XQueryParser generateParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        XQueryLexer lexer = new XQueryLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        XQueryParser parser = new XQueryParser(tokenStream);
        parser.removeErrorListeners();
        return parser;
    }

    public static LinkedList<Node> evaluateXQueryJoin(String join) throws Exception {
        XQueryParser parser = generateParser(join);
        ParseTree parseTree = parser.xq();
        XQueryEvaluator evaluator = new XQueryEvaluator();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        evaluator.output = builder.newDocument();
        return evaluator.visit(parseTree);
    }

    public static void main(String[] args) throws Exception {
        // "Java -jar CSE-232B-M1.jar milestone1_input_queries.txt"
        String fileName = args[0];
        CharStream charStream = CharStreams.fromFileName(fileName);
        XQueryLexer lexer = new XQueryLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        XQueryParser parser = new XQueryParser(tokenStream);
        parser.removeErrorListeners();
        ParseTree parseTree = parser.xq();
        XQueryEvaluator evaluator = new XQueryEvaluator();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        evaluator.output = builder.newDocument();
       
        List<Node> res = evaluator.visit(parseTree);
        
        // create result node
        Document output = evaluator.output;
        Node outputNode = output.createElement("query_result");
        for(Node node : res) {
            Node importedNode = output.importNode(node, true);
            outputNode.appendChild(importedNode);
        }
        
        // Use a Transformer for output
        trimWhitespace(outputNode);
        output.appendChild(outputNode);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(output);
        StreamResult streamRes = new StreamResult(System.out);
        transformer.transform(source, streamRes);
    }
}
