package cbrsystem;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class ParseXML {
    public static void main(String[] argv) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        Document document = loader.parse("/Users/zachdaniels/Desktop/Casebase.xml");
        
        DocumentTraversal traversal = (DocumentTraversal) document;
        
        TreeWalker walker = traversal.createTreeWalker(document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT, null, true);
        
        traverseLevel(walker, "");
    }
    
    private static final void traverseLevel(TreeWalker walker, String indent) {
        Node parend = walker.getCurrentNode();
        System.out.println(indent + "- " + ((Element) parend).getTagName());
        if(((Element) parend).getTagName().equals("name"))
        {
            System.out.println(parend.getFirstChild().getNodeValue());
        }
        if(((Element) parend).getTagName().equals("value"))
        {
            System.out.println(parend.getFirstChild().getNodeValue());
        }  
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            traverseLevel(walker, indent + '\t');
        }
        walker.setCurrentNode(parend);
    }
}