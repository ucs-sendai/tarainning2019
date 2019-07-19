/**
システム名：社員管理システム
クラス名  ：HrsmUcsDBException
処理概要  ：オリジナル例外を定義する
プロジェクト名：HrsmUcs(ウォン班)
作成者    ：スピェッウォン
作成日付  ：2019/06/25(火)
 */

package jp.ucs.exception;

public class HrsmUcsDBException extends Exception {
	/**
	 * デフォルトコンストラクタ
	 */
	public HrsmUcsDBException() {

	}

	/**
	 * 引数1つのコンストラクタ
	 * @param errorMsg 入力のエラーメッセージ
	 */
	public HrsmUcsDBException(String errorMsg) {
		super(errorMsg);
	}

}
