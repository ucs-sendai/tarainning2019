package jp.ucs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.ucs.logic.SampleLogic;

public class SampleLogicTest {

	//正常系（同じ数字が返ってくるか確認）
	@Test
	public void test1() {
		SampleLogic logic = new SampleLogic();
		assertEquals(1, logic.sample(1));
	}

	//正常系（違う数字が返ってくるかの確認）
	@Test
	public void test2() {
		SampleLogic logic = new SampleLogic();
		assertNotEquals(1, logic.sample(1));
	}
	

	//異常系（NULLぽ発生）
	@Test(expected = NullPointerException.class)
	public void test3() {
		SampleLogic logic = new SampleLogic();
		logic.sample(null);
	}
}