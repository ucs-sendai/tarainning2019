package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.RegisterLogic;

public class RegisterLogicTest {

	// 正常処理
	// 登録可能
	@Test
	public void registerExecute1() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertTrue(registerLogic.registerExecute(empBean));
	}

	// 異常処理
	// HrsmUcsDBExceptionの発生
	// DBの接続を切断して行うこと
	@Test(expected = HrsmUcsDBException.class)
	public void testErrorCheck1() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertFalse(registerLogic.registerExecute(empBean));
	}
}