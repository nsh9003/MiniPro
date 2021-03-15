package kr.co.softsoldesk.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//���� ������

@Configuration // servlet-context.xml���� <annotation-driven/>�� ���� �κ�
@EnableWebMvc // controller������̼��� �����Ǿ��ִ� Ŭ������ ����ϴ� ������̼�
@ComponentScan("kr.co.softsoldesk.controller") // ��ĵ�� ��Ű�� ���
public class ServletAppContext implements WebMvcConfigurer {
	
	
	// controller �޼��忡�� ��ȯ�ϴ� ��� ���ڿ� ��, �ڿ� ���� ���
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// ���� ������(�̹���,����,�����, �ڹٽ�ũ��Ʈ, css ��)�� ��� ����
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	

}
