package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.LoginLogic;



public class LoginLogicTest{

	//正常系（ログインできる）
	@Test
	public void testLoginCheck1() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00001234","M0i6K2z5");
		assertTrue(loginLogic.loginCheck(employee));
	}


	//正常系（ユーザIDの不備でログインできない）
	@Test
	public void testLoginCheck2() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("111111111","M0i6K2z5");
		assertFalse(loginLogic.loginCheck(employee));
	}


	//正常系（パスワードの不備でログインできない）
	@Test
	public void testLoginCheck3() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00001234","M0i6K2z4");
		assertFalse(loginLogic.loginCheck(employee));
	}


	//異常系（ユーザID、パスワードに値を入力していないため、ログインできない）
	@Test(expected = NullPointerException.class)
	public void testLoginCheck4() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		loginLogic.loginCheck(null);
	}

	//異常系（DBエラー）
	@Test
		public void testLoginCheck5() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("111111111","M0i6K2z5");
		assertFalse(loginLogic.loginCheck(employee));
	}
}


