package kr.co.softsoldesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //일반 클래스를 컨트롤러로 지정
public class HomeController {
	
	//어디서든 어떤 주소든 입력하면 해당 메소드를 실행함. "/"이 모든 주소를 의미
	@RequestMapping(value="/", method=RequestMethod.GET) 	
	public String Home() {
		System.out.println("home");
		
		
		
		
		return "index";
		//주소를 리턴시키면 그 주소로 이동함 - context에서 주소 다 안치고 이름만 쳐도 찾아지게 세팅했음
		
	}
	
}
