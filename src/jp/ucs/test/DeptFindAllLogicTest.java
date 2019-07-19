package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.dao.DeptDAO;
import jp.ucs.exception.HrsmUcsDBException;

public class DeptFindAllLogicTest {

	@Test
	//部門が登録されている場合（データが取得できる場合）
	public void deptest1() throws HrsmUcsDBException {
		DeptDAO deptDAO = new DeptDAO();
		assertEquals(deptDAO.deptFindAll(),deptDAO.deptFindAll());

	}

	@Test
	//部門が登録されていない場合（データが取得できない場合）
	public void deptest2() throws HrsmUcsDBException{
		DeptDAO deptDAO = new DeptDAO();
		assertNotEquals(deptDAO.deptFindAll(),deptDAO.deptFindAll());
    }

}
