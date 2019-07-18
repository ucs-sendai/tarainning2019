/**
システム名：社員管理システム
クラス名：SearchCondition
処理概要：検索条件クラス(Bean)
プロジェクト名：HrsmUcs(社員検索)
作成者    ：川田裕人
作成日付  ：2019/06/25(火)
 */
package jp.ucs.bean;

public class SearchConditionBean {

	private String empId;
	private String empName;
	private DeptBean dept;
	private LicenseBean license;

	public SearchConditionBean() {
	}

	public SearchConditionBean(String empId, String empName, DeptBean dept, LicenseBean license) {
		this.empId = empId;
		this.empName = empName;
		this.dept = dept;
		this.license = license;
	}

	/**
	 * メソッド名:getEmpId
	 * 説明: 社員IDを取得する
	 * @return empId 社員ID
	 */
	public String getEmpId() {
		return this.empId;
	}

	/**
	 * メソッド名：getEmpName
	 * 説明: 社員の名前を取得する
	 * @return empName 社員の名前
	 */
	public String getEmpName() {
		return this.empName;
	}

	/**
	 * メソッド名：getDept
	 * 説明: Deptを取得する
	 * @return dept 部門インスタンス
	 */
	public DeptBean getDept() {
		return this.dept;
	}

	/**
	 * メソッド名: getLicense
	 * 説明: Licenseを取得する
	 * @return license 資格インスタンス
	 */
	public LicenseBean getLicense() {
		return this.license;
	}

}
