package com.spring.test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/form")
	public String form() {
		return "form"; // views/form.jsp를 의미함
	}
	
	@RequestMapping("/add")
	public ModelAndView add(String name, String b_day, String phone) throws Exception {
		// 이미 존재하는 XML문서를 로드한다.
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(getClass().getResource("/data/members.xml").getPath());
		Element root = doc.getRootElement();
		//System.out.println(root.getName());
		
		/* <member>
		 * 		<name>홍길동</name>
		 * 		<b_day>2020-02-22</b_day>
		 * 		<phone>010</phone>
		 * </member>
		 */
		 Element member = new Element("member");
		 Element ename = new Element("name");
		 Element ebday = new Element("b_day");
		 Element ephone = new Element("phone");
		 
		 //현재 메서드의 인자들을 ename,ebday,ephone의 문자열로 지정한다.
		 ename.setText(name);
		 ebday.setText(b_day);
		 ephone.setText(phone);
		 
		 //member안에 하위요소로 ename, ebday, ephone을 지정한다.
		 member.addContent(ename);
		 member.addContent(ebday);
		 member.addContent(ephone);
		 
		 root.addContent(member);
		 
		 // XML문서에 저장
		 XMLOutputter out = new XMLOutputter();
		 Format f = out.getFormat();
		 
		 f.setEncoding("utf-8");
		 f.setIndent("    ");
		 f.setLineSeparator("\r\n");
		 f.setTextMode(Format.TextMode.TRIM);
		 
		 out.setFormat(f);
		 URL url = getClass().getResource("/data/members.xml");
		 out.output(doc, new FileWriter(url.getPath()));
		
		return null;
	}
}
