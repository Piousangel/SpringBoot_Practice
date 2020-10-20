package com.project.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

	@RequestMapping("/")      // "/" <-- 프로젝트를 실행했을때~
	public String index() {
		
		return "index";
	}
	
	
	
	
}
