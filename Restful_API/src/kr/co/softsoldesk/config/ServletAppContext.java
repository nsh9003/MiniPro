package kr.co.softsoldesk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//서블릿 구현부

@Configuration // servlet-context.xml에서 <annotation-driven/>와 같은 부분
@EnableWebMvc // controller어노테이션이 설정되어있는 클래스를 등록하는 어노테이션
@ComponentScan("kr.co.softsoldesk.controller") // 스캔할 패키지 등록
public class ServletAppContext implements WebMvcConfigurer {
	
	
	// controller 메서드에서 반환하는 모든 문자열 앞, 뒤에 붙을 경로
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// 정적 데이터(이미지,사운드,동양상, 자바스크립트, css 등)의 경로 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	

}
