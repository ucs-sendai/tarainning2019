package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.ChangeLogic;


//コミット時にエラーが出てソースを失ってしまったため今時点でOK
public class ChangeLogicTest{

	//データベースが更新される
	@Test
	public void testChangeExecute1() throws HrsmUcsDBException{
		EmployeeBean afterEmp = new EmployeeBean("00192818","M0e7S2k5");
		ChangeLogic chaLogic = new ChangeLogic();
		assertTrue(chaLogic.changeExecute(afterEmp));
	}

	//データベースが更新されない
	@Test
	public void testChangeExecute2() throws HrsmUcsDBException {
		EmployeeBean afterEmp = new EmployeeBean("1076584839392","deta");
		ChangeLogic chaLogic = new ChangeLogic();
		assertFalse(chaLogic.changeExecute(afterEmp));
	}

	//入力項目に不備がない
	@Test
	public void testErrorCheck1(){
		EmployeeBean beforeEmp = new EmployeeBean("00192818","M0e7S2k5");
		EmployeeBean afterEmp = new EmployeeBean("00192818","M0e7S2k5");
		ChangeLogic chaLogic = new ChangeLogic();
		assertEquals(afterEmp,chaLogic.errorCheck(beforeEmp, afterEmp));
	}

	//入力項目に不備がある
	@Test
	public void testErrorCheck2(){
		EmployeeBean beforeEmp = new EmployeeBean("00192818","M0e7S2k5");
		EmployeeBean afterEmp = new EmployeeBean("08","M0");
		ChangeLogic chaLogic = new ChangeLogic();
		assertNotEquals(afterEmp,chaLogic.errorCheck(beforeEmp, afterEmp));
	}
}

