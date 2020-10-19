package com.it.test;



import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	private String msg;
	
	@RequestMapping("/t1")
	public String ex(HttpServletRequest request) {
		
		Date now = new Date(System.currentTimeMillis());
		String now_date = now.toString();
		msg = now_date;
		request.setAttribute("now",now_date);
		
		
		return "test";  //views라는 폴더 
	}
	
	@RequestMapping("/t2")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		
		//ModelAndView에 값을 저장합니다.
		mv.addObject("value","안녕하세요...");
		mv.addObject("msg", msg);
		
		//보여질 JSP의 경로를 지정하는 것이 있습니다.
		mv.setViewName("test2");  //views폴더의 test2.jsp로 갑니다.
		
		return mv;
	}
}
