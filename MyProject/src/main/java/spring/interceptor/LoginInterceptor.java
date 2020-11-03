package spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 체크
		
		HttpSession session = request.getSession(true);  //false면 session없어도 새로 만들지 않습니다.
		
		//로그인 시 저장했던 객체를 얻어내는 것..
		Object obj = session.getAttribute("mvo");  //obj != null -> true 반환하면 됩니다..
		
		if(obj == null) {
			response.sendRedirect("/login"); 
			return false;
		}
		
		

		return true;
	}

	
}
