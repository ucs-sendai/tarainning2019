package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jp.ucs.dao.DeleteEmpDAO;
import jp.ucs.logic.DeleteEmpLogic;

public class DeleteEmpLogicTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	//削除されている場合
	public void deletetest1() {
		DeleteEmpLogic deleteEmpLogic = new DeleteEmpLogic();
		DeleteEmpDAO deleteEmpDAO = new DeleteEmpDAO();
		assertNotSame(deleteEmpLogic,deleteEmpDAO);
	}

	//削除されていない場合
	public void deletetest2(){
		DeleteEmpLogic deleteEmpLogic = new DeleteEmpLogic();
		DeleteEmpDAO deleteEmpDAO = new DeleteEmpDAO();
		assertSame(deleteEmpLogic,deleteEmpDAO);

	}
}
