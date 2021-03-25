package kr.co.softsoldesk.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.UserBean;

@Repository
public class UserDao {

	//servlet에 세션템플릿으로 처리한다고 등록되어있음. 오토와이어로 걸ㅇ서 쓰기
	 @Autowired
	 private SqlSessionTemplate sqlSessionTemplate;
	 
	 public String checkUserIdExist(String user_id) {
		 return sqlSessionTemplate.selectOne("user.checkUserIdExist", user_id);
	
	 }
	 
	 public void addUserInfo(UserBean joinUserBean) {
		 sqlSessionTemplate.insert("user.addUserInfo", joinUserBean);
	 }
	 
	 public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
			return sqlSessionTemplate.selectOne("user.getLoginUserInfo", tempLoginUserBean);
		}

	 
}
