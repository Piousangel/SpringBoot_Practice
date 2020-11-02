package com.spring.test;

import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping("/add")
	public ModelAndView add(String name, String b_day, String phone) throws Exception {
		//이미 존재하는 XML문서를 로드합니다.
		
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build("classpath:data/members.xml");
		Element root = doc.getRootElement();
		//System.out.println(root.getName());
		
		/*
		 * <member>
		 * 		<name>홍길동</name>
		 * 		<b_day>2020-02-11</b_day>
		 * 		<phone>010</phone>
		 * </member>
		 */
		
		Element member = new Element("member");
		Element ename = new Element("name");
		Element eb_day = new Element("b_day");
		Element ephone = new Element("phone");
		//현재 메서드의 인자들을  name,b_day,phone의 문자열로 지정합니다.
		
		ename.setText(name);
		eb_day.setText(b_day);
		ephone.setText(phone);
		
		//member안에 하위요소로 3개를 넣어줍니다.
		member.addContent(ename);
		member.addContent(eb_day);
		member.addContent(ephone);
		
		root.addContent(member);
		
		//XML문서로 저장하기 위해..
		XMLOutputter out = new XMLOutputter();
		Format f = out.getFormat();
		
		f.setEncoding("utf-8");
		f.setIndent("  ");
		f.setLineSeparator("\r\n");
		f.setTextMode(Format.TextMode.TRIM);
		
		out.setFormat(f);
		
		out.output(doc, new FileWriter("classpath:data/members.xml"));
		
		
		
		
		return null;
		
		
		
	}
	
}
