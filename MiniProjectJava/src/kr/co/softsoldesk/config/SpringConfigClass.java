package kr.co.softsoldesk.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringConfigClass implements WebApplicationInitializer{//초기설정

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// System.out.println("onStartup");
		//web.xml에서 servlet 구현부와 같음
		//플젝 구현을 위한 클래스 객체 생성
		
		AnnotationConfigWebApplicationContext servletAppContext=new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		//register로 ctx주소를 따로 끼워넣는다. 생성자 사용 안함
		//어노테이션에 클래스추가, 레지스트리에 등록하는 개념
		
		//요청정보를 분석해서 컨트롤러를 선택하는 서블릿 지정
		DispatcherServlet dispatcherServlet=new DispatcherServlet(servletAppContext); //니가 뭘 쓰더라도 죄다 어노테이션으로다가 조질거야
		ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher", dispatcherServlet);
		//dispatcherServlet을 servletContext에 넣어야 함. 최종적인 등록
		//매개변수로 선언된 servletContext객체를 이용해서 servlet에 추가하는거임
		
		//부가설정
		servlet.setLoadOnStartup(1); //서버 올라가면 제일먼저 세팅하렴
		servlet.addMapping("/"); //모든 경로에다가 적용하렴
		
		//web.xml에서 <context-param>부분
		//Bean을 정의할 xml파일 지원
		AnnotationConfigWebApplicationContext rootAppContext=new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		//web.xml에서 <listener>부분
		ContextLoaderListener listener=new ContextLoaderListener(rootAppContext); //루트앱콘텍스트를 꼭 가져와라
		servletContext.addListener(listener); //분석할게 없어서 dispatcher 안들어감, 바로 넣어버리기
		
		//web.xml에서 <filter>부분
		//파라미터 인코딩설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
	    //dispatcher에 의해서 추가된 Servlet에 UTF-8로 encoding하겠다는 구현부
		filter.addMappingForServletNames(null, false, "dispatcher");
		
		
		
	} 
		
		
}