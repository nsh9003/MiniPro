package kr.co.softsoldesk.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.softsoldesk.beans.UserBean;

@Controller //�Ϲ� Ŭ������ ��Ʈ�ѷ��� ����
public class HomeController {
	 
	
	@Resource(name="loginUserBean")
	@org.springframework.context.annotation.Lazy
	private UserBean loginUserBean;
	
	
	//��𼭵� � �ּҵ� �Է��ϸ� �ش� �޼ҵ带 ������. "/"�� ��� �ּҸ� �ǹ�
	@RequestMapping(value="/", method=RequestMethod.GET) 	
	public String Home() {
		System.out.println(loginUserBean);
		
		
		
		
		return "redirect:/main";
		//�ּҸ� ���Ͻ�Ű�� �� �ּҷ� �̵��� - context���� �ּ� �� ��ġ�� �̸��� �ĵ� ã������ ��������
		
	}
	
}
