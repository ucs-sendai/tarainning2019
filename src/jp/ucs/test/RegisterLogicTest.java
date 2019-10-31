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

	// 正常処理
	// 入力チェック(名前:未入力)
	@Test
	public void registerExecute2() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("", "ああああ", dept, "12345678", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		registerLogic.checkEmp(empBean);
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("氏名が入力されていません"));
	}

	// 正常処理
	// 登録不可(名前:入力制限超え)
	@Test
	public void registerExecute3() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("あああああああああああああああああああああああああああああああ", "ああああ", dept, "12345678",
				"1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("氏名が入力されていません"));
	}

	// 正常処理
	// 登録不可(ふりがな:未入力)
	@Test
	public void registerExecute4() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "", dept, "12345678", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("ふりがなが入力されていません"));
	}

	// 正常処理
	// 登録不可(ふりがな:入力値制限超え)
	@Test
	public void registerExecute5() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああああああああああああああああああああああああああああああああああああああああ", dept, "12345678",
				"1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("ふりがなが入力されていません"));
	}

	// 正常処理
	// 登録不可(部門ID:未入力)
	@Test
	public void registerExecute6() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("null", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("部門が選択されていません"));
	}

	// 正常処理
	// 登録不可(pass:未入力)
	@Test
	public void registerExecute7() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("パスワードが入力されていません"));
	}

	// 正常処理
	// 登録不可(pass:入力値制限超え)
	@Test
	public void registerExecute8() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "123456789000000000", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("パスワードが入力されていません"));
	}

	// 正常処理
	// 登録不可(pass:入力値制限未満)
	@Test
	public void registerExecute9() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "1234567", "1234/12/34");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("パスワードが入力されていません"));
	}

	// 正常処理
	// 登録不可(入社年月日:未入力)
	@Test
	public void registerExecute10() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("入社年月日が入力されていません"));
	}

	// 正常処理
	// 登録不可(入社年月日:入力制限値超え)
	@Test
	public void registerExecute11() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/123");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("入社年月日が入力されていません"));
	}

	// 正常処理
	// 登録不可(入社年月日:入力制限値未満)
	@Test
	public void registerExecute12() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("101", "");
		EmployeeBean empBean = new EmployeeBean("ああああ", "ああああ", dept, "12345678", "1234/12/1");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(1, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("入社年月日が入力されていません"));
	}

	// 正常処理
	// 登録不可(全項目:未入力)
	@Test
	public void registerExecute13() throws HrsmUcsDBException {
		DeptBean dept = new DeptBean("null", "");
		EmployeeBean empBean = new EmployeeBean("", "", dept, "", "");
		RegisterLogic registerLogic = new RegisterLogic();
		assertEquals(5, registerLogic.checkEmp(empBean).size());
		assertTrue(registerLogic.checkEmp(empBean).get(0).equals("氏名が入力されていません"));
		assertTrue(registerLogic.checkEmp(empBean).get(1).equals("ふりがなが入力されていません"));
		assertTrue(registerLogic.checkEmp(empBean).get(2).equals("部門が選択されていません"));
		assertTrue(registerLogic.checkEmp(empBean).get(3).equals("パスワードが入力されていません"));
		assertTrue(registerLogic.checkEmp(empBean).get(4).equals("入社年月日が入力されていません"));
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