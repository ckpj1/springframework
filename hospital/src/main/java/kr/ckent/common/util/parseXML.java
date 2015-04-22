package kr.ckent.common.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;

public class parseXML {

	 private DocumentBuilder builder;
	  private Document document;
	  
	  public parseXML(InputStream xmlData) throws Exception
	  {
//	    builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//	    ByteArrayInputStream stream = new ByteArrayInputStream(xmlData.getBytes());
//	    document = builder.parse(stream);
		  
		  builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		  document = builder.parse(xmlData);
	  }
	  
	  public parseXML(File file) throws Exception
	  {
	    builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    document = builder.parse(new FileInputStream(file));
	  }
	  
	  public String getValue(String xpathExpression) throws Exception
	  {
	    XPathFactory xPathfactory = XPathFactory.newInstance();
	    XPath xpath = xPathfactory.newXPath();
	    XPathExpression expr = xpath.compile(xpathExpression);
	    return (String)expr.evaluate(document, XPathConstants.STRING);
	  }
	  
	  public Node getNode(String xpathExpression) throws Exception
	  {
	    XPathFactory xPathfactory = XPathFactory.newInstance();
	    XPath xpath = xPathfactory.newXPath();
	    XPathExpression expr = xpath.compile(xpathExpression);
	    return (Node)expr.evaluate(document, XPathConstants.NODE);
	  }
	  
	  public NodeList getNodeSet(String xpathExpression) throws Exception{
		    XPathFactory xPathfactory = XPathFactory.newInstance();
		    XPath xpath = xPathfactory.newXPath();
		    XPathExpression expr = xpath.compile(xpathExpression);
		    return (NodeList)expr.evaluate(document, XPathConstants.NODESET);		  
	  }
}
