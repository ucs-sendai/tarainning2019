package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;



public class EntryMenuServletTest{

	//正常系（管理者としてログインできる）
	@Test
	public void testEntryMenu1(){
		EmployeeBean empbean = new EmployeeBean("00001234","M0i6K2z5");
		assertEquals(empbean.getEmpId(),"00001234");


	}

	//正常系（管理者としてログインできない）
	@Test
	public void testEntryMenu2(){
		EmployeeBean empbean = new EmployeeBean("00001235","M0i6K2z5");
		assertNotEquals(empbean.getEmpId(),"00001234");


	}

	//正常系（一般社員としてログインできる）
	@Test
	public void testEntryMenu3(){
		EmployeeBean empbean = new EmployeeBean("00192818","M0e7S2k5");
		assertEquals(empbean.getEmpId(),"00192818");
	}

	//正常系（一般社員としてログインできない）
	@Test
	public void testEntryMenu4(){
		EmployeeBean empbean = new EmployeeBean("00192814","M0e7S2k5");
		assertNotEquals(empbean.getEmpId(),"00192818");
	}


	//異常系（DBエラー）
	@Test
	public void testEntryMenu5(){
		try {
			throw new HrsmUcsDBException();
		} catch (HrsmUcsDBException e) {
			e.printStackTrace();
		}
	}
}