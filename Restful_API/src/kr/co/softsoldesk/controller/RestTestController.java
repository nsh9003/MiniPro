package kr.co.softsoldesk.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softsoldesk.beans.DataBean;

@RestController //����Ʈ��Ʈ�ѷ� ����. ������̼Ǹ�� �Ȱ��� Ŭ�������� ������ ����
public class RestTestController {
	/*
	@GetMapping("/test2")
	public String test2() {
		return "test2"; //�������� �״�� �����ͷ� ����
		//test2�������� �������� ������ �ּҴ� test2�� ��. �����Ͱ� �ּҷ� �ߴ°�
		//
	}*/
	
	@GetMapping("/test2")
	public ResponseEntity<ArrayList<DataBean>> test2() {
	DataBean bean1= new DataBean("���ڿ� 1", 100, 11.11, false);
	DataBean bean2= new DataBean("���ڿ� 2", 200, 12.11, false);
	DataBean bean3= new DataBean("���ڿ� 3", 300, 13.11, false);
	ArrayList<DataBean> list= new ArrayList<DataBean>();
	list.add(bean3);
	list.add(bean2);
	list.add(bean1);
	//JSON���� ��ȯ
ResponseEntity<ArrayList<DataBean>> entry= new ResponseEntity<ArrayList<DataBean>>(list, HttpStatus.OK);
//���󿡼� �����Ͱ� json���� ��ȯ. �̿��ڰ� ���ٽ� �����͸� ���Ϸ� ���� ����	
	return entry; //��ü°�� ����
	}
}
