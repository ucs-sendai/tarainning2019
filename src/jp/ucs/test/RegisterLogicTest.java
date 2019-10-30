package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.RegisterLogic;

public class RegisterLogicTest {

	@Test
	// 正常系（データが取得できる）
	public void registerLogicTest1() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empBean = new EmployeeBean("社員情報管理", "しゃいんじょうほうかんり", "05", "G0h4K0u6", "2019/11/11");
		assertTrue(relogic.registerExecute(empBean));
	}

	@Test
	// 正常系（データが取得できない）
	public void registerLogicTest2() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empBean = new EmployeeBean();
		assertFalse(relogic.registerExecute(empBean));
	}

	@Test(expected = HrsmUcsDBException.class)
	// 異常系(DBエラー）
	public void registerLogicTest3() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean();
		relogic.registerExecute(empbean);
	}
}
