package kr.co.softsoldesk.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softsoldesk.beans.DataBean;

@RestController //레스트컨트롤러 선언. 어노테이션명과 똑같은 클래스명은 만들지 말자
public class RestTestController {
	/*
	@GetMapping("/test2")
	public String test2() {
		return "test2"; //응답결과를 그대로 데이터로 보냄
		//test2페이지가 실존하지 않지만 주소는 test2가 뜸. 데이터가 주소로 뜨는거
		//
	}*/
	
	@GetMapping("/test2")
	public ResponseEntity<ArrayList<DataBean>> test2() {
	DataBean bean1= new DataBean("문자열 1", 100, 11.11, false);
	DataBean bean2= new DataBean("문자열 2", 200, 12.11, false);
	DataBean bean3= new DataBean("문자열 3", 300, 13.11, false);
	ArrayList<DataBean> list= new ArrayList<DataBean>();
	list.add(bean3);
	list.add(bean2);
	list.add(bean1);
	//JSON으로 변환
ResponseEntity<ArrayList<DataBean>> entry= new ResponseEntity<ArrayList<DataBean>>(list, HttpStatus.OK);
//웹상에서 데이터가 json으로 변환. 이용자가 접근시 데이터를 파일로 저장 가능	
	return entry; //객체째로 리턴
	}
}
