package com.it.test;



import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/t1")
	public String ex(HttpServletRequest request) {
		
		Date now = new Date(System.currentTimeMillis());
		String now_date = now.toString();
		request.setAttribute("now",now_date);
		
		return "test";  //views라는 폴더 
	}
}
