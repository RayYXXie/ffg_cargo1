package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import pojo.CXFreturnResult;

public class XMLDocumentUtil {

	/**
	 * 解析CXF返回的XML字符串流，并封装成为CXFreturnResult对象
	 * @param xmlstr
	 * @param object
	 */
	public void parseXML(String xmlstr,CXFreturnResult object) {
		StringReader reader = new StringReader(xmlstr);
		InputSource source = new InputSource(reader);
		SAXBuilder sax = new SAXBuilder();
		sax.setValidation(false);
		try {
			Document doc = sax.build(source);
			Element root = doc.getRootElement();

			Element resCode = (Element) root.getChildren("resCode").get(0);
			Element returnMsg = (Element) root.getChildren("returnMsg").get(0);
			object.setResCode(resCode.getValue());
			object.setReturnMsg(returnMsg.getValue());

			Element ele = (Element) root.getChildren("item").get(0);
			List<?> element = ele.getChildren();
			Element e = null;
			for (int k = 0; k < element.size(); k++) {
				
				switch(k){
				case 0:
					 e= (Element) element.get(k);
					object.setAccount(e.getValue());
					break;
				case 1:
					e = (Element) element.get(k);
					object.setDataId(e.getValue());
					break;
				case 2:
					e = (Element) element.get(k);
					object.setExpectedValue(e.getValue());
					break;
				case 3:
					e = (Element) element.get(k);
					object.setMsgType(e.getValue());
					break;
				case 4:
					e = (Element) element.get(k);
					object.setOnBehalfOfCompID(e.getValue());
					break;
				case 5:
					e = (Element) element.get(k);
					object.setTransactTime(e.getValue());
					break;
				
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 自封装的将 InputStream 转为String字符串返回
	 * @param is
	 * @return
	 */
	public  String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			System.out.println("Error=" + e.toString());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				System.out.println("Error=" + e.toString());
			}
		}
		return sb.toString();
	}


	public static void main(String[] args) {
//		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<msg>"
//				+ "<resCode>0</resCode>" + "<returnMsg>操作成功</returnMsg>"
//				+ "<item>" + "<id>12</id>" + "<msgType>a</msgType>"
//				+ "<account>1234</account>"
//				+ "<transactTime>2015-8-10 14:59:07</transactTime>"
//				+ "<onBehalfOfCompID>23456</onBehalfOfCompID>"
//				+ "<expectedValue>123.00</expectedValue>" + "</item>"
//				+ "</msg>";
//		XMLDocumentUtil util = new XMLDocumentUtil();
//		util.parseXML(xmlStr);

	}

}
