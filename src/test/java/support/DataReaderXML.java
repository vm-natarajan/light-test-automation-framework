package support;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 
 * @author         :VNatarajan
 * @since          :Sep 25, 2017
 * @filename       :DataReaderXML.java
 * @version		   :
 * @description    :
 */

public class DataReaderXML extends DataReader{
	String xmlName;
	String testName;
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder builder;
	Document document = null;
	XPath xpath;

	public DataReaderXML(String xmlName,String testName) {
		this.xmlName = xmlName;
		this.testName = testName;
	}
	/**
	 * 
	 * Method Name : readXMLByXPath
	 * Return Type : void
	 * Author      : VNatarajan
	 * Date		   : Sep 25, 2017
	 * Description :
	 */
	private void readXMLByXPath() {
		factory.setNamespaceAware(true);
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(xmlName);

			// Create XPathFactory object
			XPathFactory xpathFactory = XPathFactory.newInstance();

			// Create XPath object
			xpath = xpathFactory.newXPath();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Method Name : get
	 * Return Type : String
	 * Author      : VNatarajan
	 * Date		   : Sep 25, 2017
	 * Description : 
	 * @param data
	 * @return
	 */
	@Override
	public synchronized String get(String data) {
		if(document==null) {
			readXMLByXPath();
		}
		try {
			XPathExpression expr = xpath.compile("//test[@name='"+testName+"']/data/@"+data);
			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
			if(nodes.getLength()>0) {
				return nodes.item(0).getNodeValue();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "NOT_FOUND";
	}
	/**
	 * 
	 * Method Name : getTestId
	 * Return Type : String
	 * Author      : VNatarajan
	 * Date		   : Sep 25, 2017
	 * Description : 
	 * @return
	 */
	@Override
	public String getTestId() {
		if(document==null) {
			readXMLByXPath();
		}
		try {
			XPathExpression expr = xpath.compile("//test[@name='"+testName+"']/@test-id");
			NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
			if(nodes.getLength()>0) {
				return nodes.item(0).getNodeValue();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "NOT_FOUND";
	}

}
