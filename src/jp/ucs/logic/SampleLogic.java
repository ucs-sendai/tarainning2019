package jp.ucs.logic;

public class SampleLogic {

	//入力された整数と同じ整数を返却する
	public int sample(Integer num) {

		try {

			return num * 1;

		} catch (NullPointerException e) {

			throw new NullPointerException();
		}

	}

}
