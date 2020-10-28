package com.project.ex;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

@Controller
public class ViewController {

	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("view")
	public ModelAndView view(String cPage, String b_idx) {
		
		//세션에서 한번 봤던 게시물(BbsVO)들을 저장해 두는 List<BbsVO>를 저장했다고 가정하자!
		//그것을 "view_list" 라는 이름으로 가져옵니다.
		Object obj = session.getAttribute("view_list");
		List<BbsVO> v_list = null;
		if(obj == null) {
			//저장한 적이 없는 경우는 새롭게 만들어서 저장합니다.
			v_list = new ArrayList<BbsVO>();
			
			//세션에 없는 경우였으므로 세션에 v_list를 저장!
			session.setAttribute("view_list", v_list);
			
		}else
			v_list = (List<BbsVO>) obj;
		
		//v_list에 저장된 요소들의 b_idx가 인자로 넘어온 b_idx와 같은 것이 있다면 
		//예전에 한번 본적이 있는 게시물입니다. 
		boolean chk = false;
		for(BbsVO bvo : v_list) {
			if(bvo.getB_idx().equals(b_idx)) {
				//같은 것이 있는 경우 (한번 봤던 게시물인경우!)
				chk = true;
				break;
			}
		}
		
		//사용자가 선택한 게시물을 BbsVO로 검색한다.
		BbsVO vo = b_dao.getBbs(b_idx);
		
		if(!chk) {
			b_dao.updateHit(b_idx);
			vo.setHit(String.valueOf(Integer.parseInt(vo.getHit())+1));
			v_list.add(vo);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", vo);
		//mv.addObject("cPage", cPage); 써도 되지만 forward방식으로 전송했기 때문에 view.jsp에서 ${param.cPage}로 인자를 받을 수 있다.
		
		mv.setViewName("bbs/view");
		return mv;
	}
	
}

