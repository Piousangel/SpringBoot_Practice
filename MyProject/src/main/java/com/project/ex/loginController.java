package com.project.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mybatis.vo.MemVO;

@Controller
public class loginController {

	@RequestMapping("/login")        //  "/" GET방식으로 전달됩니다.
	public String login() {
		
		return "login";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(MemVO vo) {
		
		ModelAndView mv = new ModelAndView();
		//mv.addObject("", vo.getM_id());
		//mv.addObject("", vo.getM_pw());
		
		mv.setViewName("index");
		return mv;
	}
}
