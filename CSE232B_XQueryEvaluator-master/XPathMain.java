import java.io.IOException;

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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;

public class XPathMain {
    public static void trimWhitespace(Node node)
    {
        NodeList children = node.getChildNodes();
        for(int i = 0; i < children.getLength(); ++i) {
            Node child = children.item(i);
            if(child.getNodeType() == Node.TEXT_NODE) {
                child.setTextContent(child.getTextContent().trim());
            }
            trimWhitespace(child);
        }
    }

    private static XPathParser generateParser(String input) {
        CharStream charStream = CharStreams.fromString(input);
        XPathLexer lexer = new XPathLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        XPathParser parser = new XPathParser(tokenStream);
        parser.removeErrorListeners();
        return parser;
    }

    public static LinkedList<Node> evaluateXPathAp(String ap) {
        XPathParser parser = generateParser(ap);
        ParseTree parseTree = parser.ap();
        XPathEvaluator evaluator = new XPathEvaluator();
        return evaluator.visit(parseTree);
    }

    public static LinkedList<Node> evaluateXPathRp(LinkedList<Node> curNodes, String rp) {
        XPathParser parser = generateParser(rp);
        ParseTree parseTree = parser.rp();
        XPathEvaluator evaluator = new XPathEvaluator();
        evaluator.curNodes = curNodes;
        return evaluator.visit(parseTree);
    }

    public static void main(String[] args) throws Exception {
        // "Java -jar CSE-232B-M1.jar milestone1_input_queries.txt"
        String fileName = args[0];
        CharStream charStream = CharStreams.fromFileName(fileName);
        XPathLexer lexer = new XPathLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        XPathParser parser = new XPathParser(tokenStream);
        parser.removeErrorListeners();
        ParseTree parseTree = parser.ap();
        XPathEvaluator evaluator = new XPathEvaluator();
        List<Node> res = evaluator.visit(parseTree);

        Document resDoc = DocumentBuilderFactory
                          .newInstance()
                          .newDocumentBuilder()
                          .newDocument();
        
        // create result node
        Document xmlDoc = evaluator.getXmlDoc();
        Node result = xmlDoc.createElement("query_result");
        for(Node node : res) {
            Node importedNode = xmlDoc.importNode(node, true);
            result.appendChild(importedNode);
        }
        
        // Use a Transformer for output
        Node outputNode = resDoc.importNode(result, true);
        trimWhitespace(outputNode);
        resDoc.appendChild(outputNode);
        TransformerFactory tFactory =
        TransformerFactory.newInstance();
        Transformer transformer = 
        tFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(resDoc);
        StreamResult streamRes = new StreamResult(System.out);
        transformer.transform(source, streamRes);
    }
}
