/*Parse XML File*/

package cbrsystem;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;
import org.xml.sax.SAXException;

public class ParseXML {
    
    //Keep track of useful information
    private FeatureList featureList;
    private Feature currentFeature;
    private Property currentProperty;
    private CaseBase caseBase;
    private Case currentCase;
    private Fact currentFact;
    private Fact currentOutput;
    
    public ParseXML(String filelocation) throws ParserConfigurationException,SAXException,IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder loader = factory.newDocumentBuilder();
        Document document = loader.parse(filelocation);
        
        DocumentTraversal traversal = (DocumentTraversal) document;
        
        TreeWalker walker = traversal.createTreeWalker(document.getDocumentElement(),
                NodeFilter.SHOW_ELEMENT, null, true);
        
        //Build list of feature definitions first if one exists
        traverseFeatures(walker,"");
        
        //Build case base
        traverseCases(walker,"");
        
        //Verify output
        System.out.println(caseBase);
        System.out.println(featureList);
    }
    
    private void traverseFeatures(TreeWalker walker, String parent) {
        Node parend = walker.getCurrentNode();
        //Build feature list
        if(((Element) parend).getTagName().equals("featureDefinitions"))
        {
            featureList = new FeatureList();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"FeatureList");
            }
            walker.setCurrentNode(parend);
        }
        //Add feature
        else if(((Element) parend).getTagName().equals("featureDefinition") && parent.equals("FeatureList"))
        {
            currentFeature = new Feature();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"Feature");
            }
            featureList.add(currentFeature);
            walker.setCurrentNode(parend);
        }
        //Assign name to feature
        else if(((Element) parend).getTagName().equals("name") && parent.equals("Feature"))
        {
            String name = parend.getFirstChild().getNodeValue();
            currentFeature.setName(name);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"Name");
            }
            walker.setCurrentNode(parend);
        }
        //Assign type to feature
        else if(((Element) parend).getTagName().equals("type") && parent.equals("Feature"))
        {
            String type = parend.getFirstChild().getNodeValue();
            currentFeature.setType(type);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"Type");
            }
            walker.setCurrentNode(parend);
        }
        //Examine properties
        else if(((Element) parend).getTagName().equals("properties") && parent.equals("Feature"))
        {
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"Properties");
            }
            walker.setCurrentNode(parend);
        }
        //Add property
        else if(((Element) parend).getTagName().equals("property") && parent.equals("Properties"))
        {
            currentProperty = new Property();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"Property");
            }
            currentFeature.addProperty(currentProperty);
            walker.setCurrentNode(parend);
        }
        //Assign name to property
        else if(((Element) parend).getTagName().equals("propertyName") && parent.equals("Property"))
        {
            String name = parend.getFirstChild().getNodeValue();
            currentProperty.setName(name);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"PropertyName");
            }
            walker.setCurrentNode(parend);
        }
        //Assign value to property
        else if(((Element) parend).getTagName().equals("propertyValue") && parent.equals("Property"))
        {
            String value = parend.getFirstChild().getNodeValue();
            currentProperty.setValue(value);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"PropertyValue");
            }
            walker.setCurrentNode(parend);
        }
        //Assign weight to feature
        else if(((Element) parend).getTagName().equals("weight") && parent.equals("Feature"))
        {
            double weight = 0;
            weight = Double.parseDouble(parend.getFirstChild().getNodeValue());
            currentFeature.setWeight(weight);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"PropertyValue");
            }
        }
        else{
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseFeatures(walker,"");
            }
            walker.setCurrentNode(parend);
        }
        walker.setCurrentNode(parend);
    }
    
    private void traverseCases(TreeWalker walker, String parent) {
        Node parend = walker.getCurrentNode();
        //Build new case base
        if(((Element) parend).getTagName().equals("caseBase"))
        {
            caseBase = new CaseBase();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"CaseBase");
            }
            walker.setCurrentNode(parend);
        }
        else if(((Element) parend).getTagName().equals("cases") && parent.equals("CaseBase"))
        {
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Cases");
            }
        }
        //New case
        else if(((Element) parend).getTagName().equals("case") && (parent.equals("Cases") || parent.equals("CaseBase")))
        {
            currentCase = new Case();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Case");
            }
            caseBase.add(currentCase);
        }
        //Assign name to case
        else if(((Element) parend).getTagName().equals("name") && parent.equals("Case"))
        {
            String name = parend.getFirstChild().getNodeValue();
            currentCase.setName(name);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Name");
            }
        }
        else if(((Element) parend).getTagName().equals("features") && parent.equals("Case"))
        {
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Facts");
            }
        }
        //New fact, add to case
        else if(((Element) parend).getTagName().equals("feature") && parent.equals("Facts"))
        {
            currentFact = new Fact();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Fact");
            }
            currentCase.add(currentFact);
        }
        //Set attribute name for fact
        else if(((Element) parend).getTagName().equals("name") && parent.equals("Fact"))
        {
            String attribute = parend.getFirstChild().getNodeValue();
            currentFact.setAttribute(attribute);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Attribute");
            }
        }
        //Set value for fact
        else if(((Element) parend).getTagName().equals("value") && parent.equals("Fact"))
        {
            String value = parend.getFirstChild().getNodeValue();
            currentFact.setValue(value);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Value");
            }
        }
        else if(((Element) parend).getTagName().equals("output") && parent.equals("Case"))
        {
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"Output");
            }
        }
        //New output fact
        else if(((Element) parend).getTagName().equals("feature") && parent.equals("Output"))
        {
            currentOutput = new Fact();
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"OutputFact");
            }
            currentCase.setOutput(currentOutput);
        }
        //Assign output attribute name
        else if(((Element) parend).getTagName().equals("name") && parent.equals("OutputFact"))
        {
            String attribute = parend.getFirstChild().getNodeValue();
            currentOutput.setAttribute(attribute);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"OutputAttribute");
            }
        }
        //Assign output value
        else if(((Element) parend).getTagName().equals("value") && parent.equals("OutputFact"))
        {
            String value = parend.getFirstChild().getNodeValue();
            currentOutput.setValue(value);
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"OutputValue");
            }
        }
        else{
            for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
                traverseCases(walker,"");
            }
            walker.setCurrentNode(parend);
        }
        walker.setCurrentNode(parend);
    }
    
    public FeatureList getFeatureDefinitions(){
        return featureList;
    }
    
    public CaseBase getCaseBase(){
        return caseBase;
    }
}