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

public class SpringConfigClass implements WebApplicationInitializer{//�ʱ⼳��

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// System.out.println("onStartup");
		//web.xml���� servlet �����ο� ����
		//���� ������ ���� Ŭ���� ��ü ����
		
		AnnotationConfigWebApplicationContext servletAppContext=new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		//register�� ctx�ּҸ� ���� �����ִ´�. ������ ��� ����
		//������̼ǿ� Ŭ�����߰�, ������Ʈ���� ����ϴ� ����
		
		//��û������ �м��ؼ� ��Ʈ�ѷ��� �����ϴ� ���� ����
		DispatcherServlet dispatcherServlet=new DispatcherServlet(servletAppContext); //�ϰ� �� ������ �˴� ������̼����δٰ� �����ž�
		ServletRegistration.Dynamic servlet=servletContext.addServlet("dispatcher", dispatcherServlet);
		//dispatcherServlet�� servletContext�� �־�� ��. �������� ���
		//�Ű������� ����� servletContext��ü�� �̿��ؼ� servlet�� �߰��ϴ°���
		
		//�ΰ�����
		servlet.setLoadOnStartup(1); //���� �ö󰡸� ���ϸ��� �����Ϸ�
		servlet.addMapping("/"); //��� ��ο��ٰ� �����Ϸ�
		
		//web.xml���� <context-param>�κ�
		//Bean�� ������ xml���� ����
		AnnotationConfigWebApplicationContext rootAppContext=new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		//web.xml���� <listener>�κ�
		ContextLoaderListener listener=new ContextLoaderListener(rootAppContext); //��Ʈ�����ؽ�Ʈ�� �� �����Ͷ�
		servletContext.addListener(listener); //�м��Ұ� ��� dispatcher �ȵ�, �ٷ� �־������
		
		//web.xml���� <filter>�κ�
		//�Ķ���� ���ڵ�����
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
	    //dispatcher�� ���ؼ� �߰��� Servlet�� UTF-8�� encoding�ϰڴٴ� ������
		filter.addMappingForServletNames(null, false, "dispatcher");
		
		
		
	} 
		
		
}