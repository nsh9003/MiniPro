package kr.co.softsoldesk.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	public boolean checkUserIdExist(String user_id) {
		String user_name=userDao.checkUserIdExist(user_id);
	if (user_name==null) {
		return true;
	}else return false;
}
	
	public void addUserInfo(UserBean joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}
	
public void getLoginUserInfo(UserBean tempLogin) {
		
		UserBean tempLogin2 = userDao.getLoginUserInfo(tempLogin);
		//가져온 데이터가 있다면
		if(tempLogin2 != null) {
			loginUserBean.setUser_idx(tempLogin2.getUser_idx());
			loginUserBean.setUser_name(tempLogin2.getUser_name());
			loginUserBean.setUserLogin(true);
		}
	}

	
}
