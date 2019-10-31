package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.RegisterLogic;

public class RegisterLogicTest {

	@Test
	// 正常系（登録処理）
	public void registerLogicTest1() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("社員情報管理", "しゃいんじょうほうかんり", "05", "K0m9T2g9", "2019/11/10");
		assertTrue(relogic.registerExecute(empbean));
	}

	@Test(expected = HrsmUcsDBException.class)
	// 異常系(DBエラー）
	public void registerLogicTest2() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean();
		assertFalse(relogic.registerExecute(empbean));
	}

	@Test
	// 正常系（すべての項目が入力されている場合）
	public void checkInputTest1() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("社員情報管理", "しゃいんじょうほうかんり", "05", "K0m9T2g9", "2019/11/10");
		assertNotNull(relogic.checkInputData(empbean));
	}

	@Test
	// 正常系（氏名が入力されていない場合）
	public void checkInputTest2() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("", "しゃいんじょうほうかんり", "05", "K0m9T2g9", "2019/11/10");
		assertNotNull(relogic.checkInputData(empbean));
	}

	@Test
	// 正常系（ふりがなが入力されていない場合）
	public void checkInputTest3() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("社員情報管理", "", "05", "K0m9T2g9", "2019/11/10");
		assertNotNull(relogic.checkInputData(empbean));
	}

	@Test
	// 正常系（部門が選択されていない場合）
	public void checkInputTest4() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("社員情報管理", "しゃいんじょうほうかんり", "", "K0m9T2g9", "2019/11/10");
		assertNotNull(relogic.checkInputData(empbean));
	}

	@Test
	// 正常系（パスワードが入力されていない場合）
	public void checkInputTest5() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("社員情報管理", "しゃいんじょうほうかんり", "05", "", "2019/11/10");
		assertNotNull(relogic.checkInputData(empbean));
	}

	@Test
	// 正常系（入社年月日が入力されていない場合）
	public void checkInputTest6() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean("社員情報管理", "しゃいんじょうほうかんり", "05", "K0m9T2g9", "");
		assertNotNull(relogic.checkInputData(empbean));
	}

	@Test(expected = NullPointerException.class)
	// 異常系(すべての項目が未入力の場合）
	public void checkInputTest7() throws HrsmUcsDBException {
		RegisterLogic relogic = new RegisterLogic();
		EmployeeBean empbean = new EmployeeBean();
		assertNull(relogic.checkInputData(empbean));
	}
}
