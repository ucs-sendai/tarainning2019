package jp.ucs.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.DeptDAO;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.DeptFindAllLogic;

public class DeptFindAllLogicTest {

	@Test
	//部門が登録されている場合（データが取得できる場合）
	public void deptest1() throws HrsmUcsDBException {
		DeptFindAllLogic deptFindAllLogic = new DeptFindAllLogic();
		DeptDAO deptDAO = new DeptDAO();
		HttpServletRequest request = null;
		HttpSession session = request.getSession();
		EmployeeBean afterEmp =(EmployeeBean)session.getAttribute("afterEmp");
		assertEquals(deptDAO.deptFindAll(),deptDAO.deptFindAll());

	}

	@Test
	//部門が登録されていない場合（データが取得できない場合）
	public void deptest2() throws HrsmUcsDBException{
		DeptFindAllLogic deptFindAllLogic = new DeptFindAllLogic();
		DeptDAO deptDAO = new DeptDAO();
		HttpServletRequest request = null;
		HttpSession session = request.getSession();
		EmployeeBean afterEmp =(EmployeeBean)session.getAttribute("afterEmp");
		assertNotEquals(deptDAO.deptFindAll(),deptDAO.deptFindAll());
		assertNotEquals(deptFindAllLogic.deptExecute(),afterEmp);
    }


	public void deptest3() throws HrsmUcsDBException{
		DeptFindAllLogic deptFindAllLogic = new DeptFindAllLogic();
		assertNull(deptFindAllLogic.deptExecute());
	}
}
