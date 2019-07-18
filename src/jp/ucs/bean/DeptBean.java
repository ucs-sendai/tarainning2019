/**
 * システム名：社員管理システム
 * クラス名：Dept
 * 処理概要：部門クラス
 * プロジェクト名  ：HrsmUcs(共通)
 * 作成者   ：エーチャンス
 * 作成日付：2019/06/20(木)
 */
package jp.ucs.bean;

public class DeptBean {
	private String deptId;
	private String deptName;

	/**
	 * ディフォルトコンストラクタ
	 */
	public DeptBean() {

	}

	/**
	 * 引数２つのコンストラクタ
	 * @param deptId：部門ID
	 * @param deptName：部門名
	 */
	public DeptBean(String deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}

	/**
	 * メソッド名：getDeptId
	 * 説明：部門IDを返す
	 * @return deptId：部門IDの戻り値
	 */
	public String getDeptId() {
		return this.deptId;
	}

	/**
	 * メソッド名：getDeptName
	 * 説明：部門名を返す
	 * @return deptName：部門名の戻り値
	 */
	public String getDeptName() {
		return this.deptName;
	}
}
