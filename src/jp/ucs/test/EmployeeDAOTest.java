package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.EmployeeDAO;
import jp.ucs.exception.HrsmUcsDBException;



public class EmployeeDAOTest{

	//正常系（ユーザーが見つかる）
	@Test
	public void testFindByEmployee1() throws HrsmUcsDBException{
		EmployeeBean empbean = new EmployeeBean("00001234","M0i6K2z5");
		EmployeeDAO empDAO = new EmployeeDAO();
		EmployeeBean employeebean  = empDAO.findByEmployee(empbean);
		assertNotNull(employeebean.getEmpName());
	}

	//正常系（ユーザーが見つからない）
	@Test
	public void testFindByEmployee2()throws HrsmUcsDBException{
		EmployeeBean empbean = new EmployeeBean("00001235","M0i6K2z5");
		EmployeeDAO empDAO = new EmployeeDAO();
		EmployeeBean employeebean  = empDAO.findByEmployee(empbean);
		assertNull(employeebean.getEmpName());
	}

	//異常系（DB例外の発生）
	@Test
	public void testFindByEmployee3(){
		try {
			throw new HrsmUcsDBException();
		} catch (HrsmUcsDBException e) {
			e.printStackTrace();
		}
	}
}


