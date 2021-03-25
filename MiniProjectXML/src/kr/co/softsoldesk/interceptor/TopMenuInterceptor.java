package kr.co.softsoldesk.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{
	
	@Autowired 
	private TopMenuService topMenuService;
	//java에서는 인터셉터에 오토와이어가 불가능했지만  xml은 가능! 밑의 메소드를 버리고 어노테이션 등록해주면 된다
	/*
	 * public TopMenuInterceptor(TopMenuService topMenuService) {
	 * this.topMenuService=topMenuService; }
	 */
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoBean> topMenuList= topMenuService.getTopMenuList();
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		return true;
	}
	
}