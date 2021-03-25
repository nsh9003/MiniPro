package kr.co.softsoldesk.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{

	//비로그인시 정보수정페이지 접근을 막는 이너셉터
	//로그인여부 판단을 위한 loginUserBean객체 삽입
	

@Resource(name="loginUserBean")
@Lazy
private UserBean loginUserBean;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//로그인되어있지 않으면
		if(loginUserBean.isUserLogin()==false) {
			//로그인 전의 경로로 돌리기
			String contextPath=request.getContextPath();
			//로그인처리가 안되어있으므로 not_login으로 페이지 전환하기
			
			response.sendRedirect(contextPath+"/user/not_login");
			//다음단계로 이동하지 않ㅇㅁ
			return false;		
		}
		
		//로그인이면 지나가세요
		return true;
	
	}
	
}
