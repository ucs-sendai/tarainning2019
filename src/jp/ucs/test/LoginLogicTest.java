package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.LoginLogic;

public class LoginLogicTest{

	//正常処理
	//ログイン可能
	@Test
	public void testLoginExecute1() throws HrsmUcsDBException{
		EmployeeBean empBean = new EmployeeBean("0000","0001","a15263389");
		LoginLogic loginLogic = new LoginLogic();
		assertTrue(loginLogic.loginCheck(empBean));
	}

	//正常処理
	//ログイン不可
	//ID:X PASS:X
	@Test
	public void testLoginExecute2() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("1111","1111","12345678");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginCheck(empBean));
	}
	//正常処理
	//ログイン不可
	//ID:〇 PASS:X
	@Test
	public void testLoginExecute3() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("0000","0001","12345678");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginCheck(empBean));
	}

	//正常処理
	//ログイン不可
	//ID:X PASS:〇
	@Test
	public void testLoginExecute4() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("0000","1234","a15263389");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginCheck(empBean));
	}

	//異常処理
	//HrsmUcsDBExceptionの発生
	//DBの接続を切断して行うこと
	@Test(expected = HrsmUcsDBException.class)
	public void testErrorCheck1() throws HrsmUcsDBException{

		EmployeeBean empBean = new EmployeeBean("0000","0001","a15263389");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginCheck(empBean));
	}
}