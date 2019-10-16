package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.dao.EmpListDAO;
import jp.ucs.logic.EmpListLogic;

public class EmpListLogicTest {

	@Test
	public void emplisttest1() {
		EmpListLogic empliLogic = new EmpListLogic();
		EmpListDAO empliDAO = new EmpListDAO();
		assertNotEquals(empliLogic,empliDAO);
	}

	public void emplisttest2() {
		EmpListLogic empliLogic = new EmpListLogic();
		EmpListDAO empliDAO = new EmpListDAO();
		assertNotSame(empliLogic,empliDAO);
	}
}
