package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.dao.DeptDAO;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.DeptFindAllLogic;

public class DeptFindAllLogicTest {

	@Test
	// 部門が登録できる場合
	public void deptest1() throws HrsmUcsDBException {

		DeptFindAllLogic deptFindAllLogic = new DeptFindAllLogic();
		DeptDAO deptDAO = new DeptDAO();
		assertEquals(deptFindAllLogic.deptExecute(), deptDAO.deptFindAll());
	}

	@Test
	// 部門が登録できない場合
	public void deptest2() throws HrsmUcsDBException {
		DeptFindAllLogic deptFindAllLogic = new DeptFindAllLogic();
		DeptDAO deptDAO = new DeptDAO();
		assertNotEquals(deptFindAllLogic.deptExecute(), deptDAO.deptFindAll());
	}

}
