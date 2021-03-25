package kr.co.softsoldesk.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.BoardInfoBean;

//select는 repository 로 등록, 서블렛에 컴포넌트스캔으로 등록
@Repository 
public class TopMenuDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<BoardInfoBean> getTopMenuList(){
		List<BoardInfoBean>	topMenuList=sqlSessionTemplate.selectList("topmenu.get_topmenu_list");
				
		 return topMenuList;
		 
	}
	
	
}
