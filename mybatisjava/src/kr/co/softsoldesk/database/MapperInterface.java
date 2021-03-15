package kr.co.softsoldesk.database;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.softsoldesk.beans.DataBean1;

public interface MapperInterface {

	@Insert("insert into spring_mvc_table (data1, data2, data3) values(#{data1}, #{data2}, #{data3})")
	void insertData(DataBean1 dataBean);

	@Select("select * from spring_mvc_table")
	List<DataBean1> select_data();
	
}
