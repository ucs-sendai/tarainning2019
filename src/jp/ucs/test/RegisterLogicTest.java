package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.RegisterLogic;

public class RegisterLogicTest {

	// 正常処理
	// 入力チェック(名前:未入力)
	@Test
	public void registerExecute1() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("", "ああああ", dept, "12345678", "2019/12/31");
		RegisterLogic registerLogic = new RegisterLogic();
		registerLogic.checkEmp(empBean);
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR01));
	}

	// 正常処理
	// 登録不可(名前:入力制限超え)
	@Test
	public void registerExecute2() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("あああああああああああああああああああああああああああああああああ", "ああああ", dept, "12345678",
				"1234/12/31");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR06));
	}

	// 正常処理
	// 登録不可(ふりがな:未入力)
	@Test
	public void registerExecute3() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "", dept, "12345678", "1234/12/31");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR02));
	}

	// 正常処理
	// 登録不可(ふりがな:入力値制限超え)
	@Test
	public void registerExecute4() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああああああああああああああああああああああああああああああああああああああああ", dept, "12345678",
				"1234/12/31");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR07));
	}

	// 正常処理
	// 登録不可(ふりがな:かな判断)
	@Test
	public void registerExecute5() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "aaaaaa", dept, "12345678", "1234/12/31");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR10));
	}

	// 正常処理
	// 登録不可(部門ID:未入力)
	@Test
	public void registerExecute6() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("null", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/31");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR03));
	}

	// 正常処理
	// 登録不可(pass:未入力)
	@Test
	public void registerExecute7() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR04));
	}

	// 正常処理
	// 登録不可(pass:規定値範囲内か)
	@Test
	public void registerExecute8() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "123456789000000000", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR08));
	}

	// 正常処理
	// 登録不可(入社年月日:未入力)
	@Test
	public void registerExecute9() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR05));
	}

	// 正常処理
	// 登録不可(入社年月日:規定値でない)
	@Test
	public void registerExecute10() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/1");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR09));
	}

	// 正常処理
	// 登録不可(入社年月日:無効な年)
	@Test
	public void registerExecute11() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/123");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR11));
	}

	// 正常処理
	// 登録不可(全項目:未入力)
	@Test
	public void registerExecute12() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("null", "");
		EmployeeBean empBean = new EmployeeBean("", "", dept, "", "");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(9, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR01));// 氏名:未入力
		assertTrue(registerLogic.checkEmp(empBean).get(1).equals(MessageConstants.REGEMP_ERR02));// かな:未入力
		assertTrue(registerLogic.checkEmp(empBean).get(2).equals(MessageConstants.REGEMP_ERR10));// かな:異常値
		assertTrue(registerLogic.checkEmp(empBean).get(3).equals(MessageConstants.REGEMP_ERR03));// 部門:未選択
		assertTrue(registerLogic.checkEmp(empBean).get(4).equals(MessageConstants.REGEMP_ERR04));// pass:未選択
		assertTrue(registerLogic.checkEmp(empBean).get(5).equals(MessageConstants.REGEMP_ERR08));// pass:異常値
		assertTrue(registerLogic.checkEmp(empBean).get(6).equals(MessageConstants.REGEMP_ERR05));// 入社:未入力
		assertTrue(registerLogic.checkEmp(empBean).get(7).equals(MessageConstants.REGEMP_ERR09));// 入社:異常値
		assertTrue(registerLogic.checkEmp(empBean).get(8).equals(MessageConstants.REGEMP_ERR11));// 入社:無効値
	}

	// 正常処理
	// 登録不可(全項目:異常値)
	@Test
	public void registerExecute13() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("null", "");
		EmployeeBean empBean = new EmployeeBean("ああああああああああああああああああああああああああああ",
				"ああああああああああああああああああああああああああああああああああああああああああ", dept, "123456789000000000", "1234/12/123");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(5, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals(MessageConstants.REGEMP_ERR06));
		assertTrue(registerLogic.checkEmp(empBean).get(1).equals(MessageConstants.REGEMP_ERR07));
		assertTrue(registerLogic.checkEmp(empBean).get(2).equals(MessageConstants.REGEMP_ERR03));
		assertTrue(registerLogic.checkEmp(empBean).get(3).equals(MessageConstants.REGEMP_ERR08));
		assertTrue(registerLogic.checkEmp(empBean).get(4).equals(MessageConstants.REGEMP_ERR09));
	}

	// 正常処理
	// 登録可能
	@Test
	public void registerExecute14() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/31");
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