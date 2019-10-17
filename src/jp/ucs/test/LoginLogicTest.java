package jp.ucs.test;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.LoginLogic;


//コミット時にエラーが出てソースを失ってしまったため今時点でOK
public class LoginLogicTest{

	//管理者としてログインできる
	@Test
	public void testLoginCheck1() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00001234","M0i6K2z5");
		loginLogic.loginCheck(employee);
	}

	//一般社員としてログインできる
	@Test
	public void testLoginCheck2() throws HrsmUcsDBException {
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00192818","M0e7S2k5");
		loginLogic.loginCheck(employee);
	}

	//ユーザIDの不備で管理者としてログインできない
	@Test
	public void testLoginCheckError1() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00001235","M0i6K2z5");
		loginLogic.loginCheck(employee);
	}

	//ユーザIDの不備で一般社員としてログインできない
	@Test
	public void testLoginCheckError2()throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00192817","M0e7S2k5");
		loginLogic.loginCheck(employee);

	}

	//パスワードの不備で管理者としてログインできない
		@Test
		public void testLoginCheckError3() throws HrsmUcsDBException{
			LoginLogic loginLogic = new LoginLogic();
			EmployeeBean employee = new EmployeeBean("00001234","M0i6K2z4");
			loginLogic.loginCheck(employee);
		}

	//パスワードの不備で一般社員としてログインできない
	@Test
	public void testLoginCheckError4()throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("00192818","M0e7S2k6");
		loginLogic.loginCheck(employee);

	}

	//ユーザID、パスワードに値を入力していないため、ログインできない
	public void testLoginCheckError5() throws HrsmUcsDBException{
		LoginLogic loginLogic = new LoginLogic();
		EmployeeBean employee = new EmployeeBean("","");
		loginLogic.loginCheck(employee);
	}

}

