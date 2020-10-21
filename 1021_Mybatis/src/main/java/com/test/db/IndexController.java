package com.test.db;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mybatis.dao.MemDAO;
import mybatis.vo.MemVO;

@Controller
public class IndexController {

	@Autowired
	private MemDAO m_dao;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/total")
	@ResponseBody
	public Map<String,Object> total(){
		
		Map<String, Object> map = new Hashtable<String, Object>();
		
		MemVO[] ar = m_dao.getAll();
		map.put("list", ar);
		 
		return map;  //여기의 total은 index.jsp의 $.ajax비동기식 통신으로 호출되었고,
					 //반환을 JSON으로 해야하지만  Map으로 하고있습니다. 즉, Map을 JSON으로 변환시켜줘야 합니다.
					 //이 과정을 라이브러리를 통해서 합니다.
	}
}
