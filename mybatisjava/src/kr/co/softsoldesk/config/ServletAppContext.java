package kr.co.softsoldesk.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.softsoldesk.database.MapperInterface;

//서블릿 구현부

@Configuration // servlet-context.xml에서 <annotation-driven/>와 같은 부분
@EnableWebMvc // controller어노테이션이 설정되어있는 클래스를 등록하는 어노테이션
@ComponentScan("kr.co.softsoldesk.controller") // 스캔할 패키지 등록
@PropertySource("/WEB-INF/properties/db.properties") //프로퍼티 소스 등록
public class ServletAppContext implements WebMvcConfigurer {
	
	//프로퍼티 내용 치환
	@Value("${db.classname}") 
	private String db_classname;
	
	@Value("${db.url}") 
	private String db_url;
	
	@Value("${db.username}") 
	private String db_username;
	
	@Value("${db.password}") 
	private String db_password;
	
	

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

	
	//db 접속정보 관리
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source= new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	
	
	//쿼리문 접속 관리
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setDataSource(source);
		SqlSessionFactory factory=factoryBean.getObject();
		return factory;
	}
	

	//쿼리문 실행을 위한 객체
	@Bean
	public MapperFactoryBean<MapperInterface> test_mapper(SqlSessionFactory factory) throws Exception{
		MapperFactoryBean<MapperInterface> factoryBean=new MapperFactoryBean<MapperInterface>(MapperInterface.class);
		factoryBean.setSqlSessionFactory(factory);
		
		return factoryBean;
		
	}
}
