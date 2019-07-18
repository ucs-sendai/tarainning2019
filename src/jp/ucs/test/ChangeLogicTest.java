package jp.ucs.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		EmployeeBean employeebean = new EmployeeBean();
		EmpUpdateDAO empupdatedao = new EmpUpdateDAO();
		assertEquals(chaLogic.execute(employeebean),empupdatedao.empUpdate(employeebean));
	}

	//データベースが更新されない(異常）
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

	//正常に入力されている
	@Test
	public void testErrorCheck1(){
		ChangeLogic chaLogic = new ChangeLogic();
		HttpServletRequest request = null;

		HttpSession session = request.getSession();
		EmployeeBean employee =(EmployeeBean) session.getAttribute("beforeEmp");;
		EmployeeBean afterEmp=(EmployeeBean) session.getAttribute("afterEmp");
		assertEquals(chaLogic.errorCheck(employee, afterEmp),afterEmp);

	}

	//入力項目に誤りがある
	@Test
	public void testErrorCheck2() {
		ChangeLogic chaLogic = new ChangeLogic();
		HttpServletRequest request = null;

		HttpSession session = request.getSession();
		EmployeeBean employee =(EmployeeBean) session.getAttribute("beforeEmp");;
		EmployeeBean afterEmp=(EmployeeBean) session.getAttribute("afterEmp");
		assertNull(chaLogic.errorCheck(employee, afterEmp));
	}

	//入力されていない
	@Test
	public void testErrorCheck3(){
		ChangeLogic chaLogic = new ChangeLogic();
		HttpServletRequest request = null;
		HttpSession session = request.getSession();
		EmployeeBean employee =(EmployeeBean) session.getAttribute("beforeEmp");;
		EmployeeBean afterEmp=(EmployeeBean) session.getAttribute("afterEmp");
		assertNull(chaLogic.errorCheck(employee, afterEmp));
	}

}
