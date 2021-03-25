package kr.co.softsoldesk.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.UserService;
import kr.co.softsoldesk.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	@Lazy
	private UserService userService;
	
	@Resource(name="loginUserBean") //root에 등록한걸 받아와야 함. 오토와이어+이름지정=리소스
	@Lazy
	private UserBean loginUserBean; 
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLogin") UserBean tempLogin,
			@RequestParam(value="fail", defaultValue = "false")boolean fail, Model model ) {
		//fail이 true면 실패->실패화면, fail이 fail이면 성공->로그인띄움
		model.addAttribute("fail", fail);
		
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String loginPro(@Valid @ModelAttribute("tempLogin")UserBean tempLogin, BindingResult res) {
	if(res.hasErrors()) {
		return "user/login"; //유효성 위배시 로그인으로 돌아감
	}
		userService.getLoginUserInfo(tempLogin);
		
		if(loginUserBean.isUserLogin()==true) {
			return "user/login_success"; 
		}
		return "user/login_fail"; 
		
	}
		//return "user/login_fail"; //한바퀴 더돌려야 함. 
	//'DontCheckUserIdExist' 가 회원가입에서만 읽혀야 하는데 로그인할때도 읽고있음
	//UserValidator는 빈 전체를 읽어들이기때문에 회원가입과 겹치는 속성이 있으면 자기 잣대를 들이댐
	//해결책: 가입용과 로그인용 빈/프롭을 따로 판다. 가능하지만 만들기도 유지보수도 어려움
	//해결책2: if문으로 joinUserBean에만 읽으라고 시키면 됌. 코드 딱 한줄ㅋ
	
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean")UserBean joinUserBean) {
		
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean")UserBean joinUserBean, BindingResult result) {
		
		if(result.hasErrors()) {		
		return "user/join";
		}
		userService.addUserInfo(joinUserBean);
		return "user/join_success";
		
	}
	
	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		
		return "user/modify";
	}
	
	@GetMapping("/logout")
	public String logout() {
		loginUserBean.setUserLogin(false);
		return "user/logout";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) { //유효성 검사할곳을 웹데이터에서 하겠다
		UserValidator validator1= new UserValidator();
		binder.addValidators(validator1);		
	}
	
}
