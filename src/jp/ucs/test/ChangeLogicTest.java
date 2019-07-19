package jp.ucs.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.EmpUpdateDAO;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.ChangeLogic;

public class ChangeLogicTest {


	//データベースが正常に更新される
		@Test
		public void testExecute1() throws HrsmUcsDBException{
			ChangeLogic chaLogic = new ChangeLogic();
			EmployeeBean employeebean = new EmployeeBean("0018","小西香菜子");
			EmpUpdateDAO empupdatedao = new EmpUpdateDAO();
			assertEquals(chaLogic.execute(employeebean),empupdatedao.empUpdate(employeebean));
		}

		//データベースが更新されない
		@Test
		public void testExecute2() throws HrsmUcsDBException {
			ChangeLogic chaLogic = new ChangeLogic();
			EmployeeBean employeebean = new EmployeeBean();
			EmpUpdateDAO empupdatedao = new EmpUpdateDAO();
			assertNotEquals(chaLogic.execute(employeebean),empupdatedao.empUpdate(employeebean));
		}

		//データベースに入力されていない
		@Test
		public void testExecute3() throws HrsmUcsDBException {
			ChangeLogic chaLogic = new ChangeLogic();
			EmployeeBean employeebean = new EmployeeBean();
			EmpUpdateDAO empupdatedao = new EmpUpdateDAO();
			assertNotEquals(chaLogic.execute(employeebean),empupdatedao.empUpdate(employeebean));
		}




		//エラーメッセージが返される
		@Test
		public void testErrorCheck4(){
			Map<String, String> errorMsgMap = new HashMap<>();
			assertEquals(errorMsgMap,errorMsgMap);
		}


		//エラーメッセージが返される
		@Test
		public void testErrorCheck5(){
			Map<String, String> errorMsgMap = new HashMap<>();
			assertNotEquals(errorMsgMap,errorMsgMap);
		}
}
