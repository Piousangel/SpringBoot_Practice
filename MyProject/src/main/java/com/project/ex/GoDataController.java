package com.project.ex;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.go.vo.DataVO;

@Controller
public class GoDataController {
	
	private DataVO[] ar;

	@RequestMapping("/goData")
	public ModelAndView data() throws Exception{
		//REST API서버의 URL을 객체로 만들어 두려고 합니다.
		URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?serviceKey=GvebJ06IblSF6gJOjKIdqnPiYpIM%2Bxkol4whFKgiU%2FBLuvfnCwhTmu4iif2U4s0%2FwDpfpU12zO56f%2FwI5ez9%2FA%3D%3D&MobileOS=ETC&MobileApp=AppTest&arrange=A&listYN=Y&eventStartDate=20201101");
		
		//자바에서 외부 자원을 읽거나 쓰기를 할 대 반드시 스트림 작업을 해야합니다.
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestProperty("Content-Type", "application/xml");   //MIME Type입니다.
		conn.connect(); //연결및 요청...
		
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(conn.getInputStream()); //XML형식으로 읽어서 doc에 저장시킵니다.
		
		Element root = doc.getRootElement(); //response태그..
		
		//우린 response안에 body/items/item들이 필요합니다.
		
		Element body = root.getChild("body");
		Element items = body.getChild("items");
		
		//items안에 있는 여러개의 item들을 얻어냅니다.
		
		List<Element> e_list = items.getChildren("item");
		
		//e_list에 있는 것은 Element입니다. 이것을 List<DataVO) 또는 DataVO[]로 바꿔주어야 합니다.
		//JSP에서 표현할 수 있습니다.
		
		//List<DataVO> list = null;
		ar = new DataVO[e_list.size()];
		int i = 0;
		for(Element e : e_list) {
			String addr1 = e.getChildText("addr1");
			String addr2 = e.getChildText("addr2");
			String eventstartdate = e.getChildText("eventstartdate");
			String eventenddate = e.getChildText("eventenddate");
			String firstimage = e.getChildText("firstimage");
			String firstimage2 = e.getChildText("firstimage2");
			String mapx = e.getChildText("mapx");
			String mapy = e.getChildText("mapy");
			String tel = e.getChildText("tel");
			String title = e.getChildText("title");
			
			DataVO vo = new DataVO(addr1, addr2, eventenddate, eventstartdate, firstimage, firstimage2, mapx, mapy, tel, title);
			
			ar[i++] = vo;
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", ar);
		
		mv.setViewName("tour/goData");
		
		return mv;
	}
	
	
	
	@RequestMapping("viewData")
	public ModelAndView viewData(int idx) {
		ModelAndView mv = new ModelAndView();
		
		//System.out.println(idx);
		//DataVO vo = ar[idx];
		mv.addObject("vo", ar[idx]);
		mv.setViewName("tour/viewData");				
		
		return mv;
	}
	
}
