package kr.co.softsoldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softsoldesk.beans.UserBean;

public class UserValidator implements Validator{

	//
	@Override 
	public boolean supports(Class<?> clazz) {
		// 유효성검사가 유효한지 확인
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 형변환
		UserBean userBean=(UserBean)target;
		
		String beanName=errors.getObjectName();
		System.out.println(beanName);
		if (beanName.equals("joinUserBean")) { //조인유저일때만 유효성 거르라고 지시
		if(userBean.getUser_pw().equals(userBean.getUser_pw2())==false) {
			errors.rejectValue("user_pw", "NotEquals");
			errors.rejectValue("user_pw2", "NotEquals");
		}
		if(userBean.isUserIdExist()==false) {
			errors.rejectValue("user_id", "DontCheckUserIdExist");
		}
		}
		
	}

	
	
}
