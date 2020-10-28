package com.project.ex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;
import mybatis.vo.CommVO;
import mybatis.vo.MemVO;
import spring.util.FileUploadUtil;

@Controller
public class ViewController {

	@Autowired
	private BbsDAO b_dao;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ServletContext application;
	
	//첨부파일 저장위치!
	private String uploadPath = "/resources/upload";
	
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
		
		session.setAttribute("bvo", vo); //수정을 선택했을 때 edit.jsp에서 표현하기 위함
		
		mv.setViewName("bbs/view");
		return mv;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(BbsVO vo) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		String c_type = request.getContentType();
		
		if(c_type != null && c_type.startsWith("multipart")) {
			//DB수정작업을 해야합니다.
		
		MultipartFile mf = vo.getFile();
		
		//정상적인 접근에서 파일이 첨부되지 않았다면 mf가 null은 아니지만 용량이 0일 것입니다. 
		//따라서 파일첨부에 대한 확인은 size로 비교합니다.
		
		
		if(mf != null && mf.getSize() > 0 && mf.getOriginalFilename().trim().length() > 0) {
			
			//절대경로 설정하기
			String path = application.getRealPath(uploadPath);
			
			//파일명 얻기
			String f_name = mf.getOriginalFilename();
			
			//동일한 파일명이 있으면 변경
			f_name = FileUploadUtil.checkSameFileName(f_name, path);
			
			//업로드
			mf.transferTo(new File(path, f_name));
			
			//DB작업 => vo객체 저장!
			vo.setFile_name(f_name);
		}
		
			vo.setIp(request.getRemoteAddr()); //ip저장도 해야
		
			BbsVO bvo = (BbsVO) session.getAttribute("bvo");
			
			vo.setB_idx(bvo.getB_idx());
			vo.setcPage(bvo.getcPage());
		
			//b_dao.add(vo); 어떤걸 업데이트로 바꾸라고 하셨는데...
			
			mv.setViewName("redirect:/view?b_idx="+vo.getB_idx()+"&cPage=vo.getcPage"); //+vo.getCPage() , b_idx는 파라미터로(조건판단) 무조건 받아야합니다. 
		}
		else		
		mv.setViewName("bbs/edit");
		
		return mv;
	}
	
	
	
}

