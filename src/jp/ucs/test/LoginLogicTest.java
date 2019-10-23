package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.LoginLogic;

public class LoginLogicTest{

	//正常処理
	//ログインできる(管理者として)
	@Test
	public void testLoginExecute1() throws HrsmUcsDBException{
		EmployeeBean empBean = new EmployeeBean("0000","0001","a15263389");
		LoginLogic loginLogic = new LoginLogic();
		assertTrue(loginLogic.loginExecute(empBean));
	}

	//正常処理
	//ログインできる(一般ユーザーとして)
	@Test
	public void testLoginExecute2() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("2019","0001","00000000");
		LoginLogic loginLogic = new LoginLogic();
		assertTrue(loginLogic.loginExecute(empBean));
	}

	//正常処理
	//ログインできない
	@Test
	public void testLoginExecute3() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("1111","1111","12345678");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginExecute(empBean));
	}
	//正常処理
	//ログインできない(管理者として)
	//ID:〇 PASS:X
	public void testLoginExecute4() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("0000","0001","12345678");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginExecute(empBean));
	}

	//正常処理
	//ログインできない(管理者として)
	//ID:X PASS:〇
	public void testLoginExecute5() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("0000","1234","a15263389");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginExecute(empBean));
	}

	//正常処理
	//ログインできない(一般ユーザーとして)
	//ID:〇 PASS:X
	public void testLoginExecute6() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("2019","0001","11111111");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginExecute(empBean));
	}

	//正常処理
	//ログインできない(一般ユーザーとして)
	//ID:X PASS:〇
	public void testLoginExecute7() throws HrsmUcsDBException {
		EmployeeBean empBean = new EmployeeBean("2019","1234","00000000");
		LoginLogic loginLogic = new LoginLogic();
		assertFalse(loginLogic.loginExecute(empBean));
	}

	//異常処理
	//入力値がnullの場合
	@Test
	public void testErrorCheck1() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		loginLogic.loginExecute(null);
	}
}