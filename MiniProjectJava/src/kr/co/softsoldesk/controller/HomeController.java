package kr.co.softsoldesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //�Ϲ� Ŭ������ ��Ʈ�ѷ��� ����
public class HomeController {
	 
	//��𼭵� � �ּҵ� �Է��ϸ� �ش� �޼ҵ带 ������. "/"�� ��� �ּҸ� �ǹ�
	@RequestMapping(value="/", method=RequestMethod.GET) 	
	public String Home() {
		System.out.println("home");
		
		
		
		
		return "redirect:/main";
		//�ּҸ� ���Ͻ�Ű�� �� �ּҷ� �̵��� - context���� �ּ� �� ��ġ�� �̸��� �ĵ� ã������ ��������
		
	}
	
}
