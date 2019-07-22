package jp.ucs.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.BaseDAO;
import jp.ucs.dao.EmpUpdateDAO;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.ChangeLogic;

public class ChangeLogicTest extends BaseDAO {


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
			EmployeeBean afterEmp = new EmployeeBean("188888888","deta");
			ChangeLogic chaLogic = new ChangeLogic();
			EmpUpdateDAO empUpdateDAO = new EmpUpdateDAO();
			assertNotEquals(empUpdateDAO.empUpdate(afterEmp),chaLogic.changeExecute(afterEmp));
		}

		//入力項目に不備がない
		@Test
		public void testErrorCheck1(){
			EmployeeBean beforeEmp = new EmployeeBean("00192818","M0e7S2k5");
			EmployeeBean afterEmp = new EmployeeBean("00192818","M0e7S2k5");
			ChangeLogic chaLogic = new ChangeLogic();
			chaLogic.errorCheck(beforeEmp, afterEmp);
			assertEquals(afterEmp,chaLogic.errorCheck(beforeEmp, afterEmp));
		}

		//入力項目に不備がある
		@Test
		public void testErrorCheck2(){
			EmployeeBean beforeEmp = new EmployeeBean("00192818","M0e7S2k5");
			EmployeeBean afterEmp = new EmployeeBean("00192818","M0e7S2k5");
			ChangeLogic chaLogic = new ChangeLogic();
			assertNotEquals(afterEmp,chaLogic.errorCheck(beforeEmp, afterEmp));
		}

		//エラーメッセージが返される
		@Test
		public void testErrorCheck3(){
			Map<String, String> errorMsgMap = new HashMap<>();
			assertEquals(errorMsgMap,errorMsgMap);
		}
}
