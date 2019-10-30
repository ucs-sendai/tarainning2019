/**
システム名：社員管理システム
クラス名  ：Employee
処理概要  ：社員のクラス
プロジェクト名：HrsmUcs
作成者    ：スピェッウォン
作成日付  ：2019/06/20(水)
 */
package jp.ucs.bean;

import java.util.Map;

public class EmployeeBean {
	private String propertyId;
	private String serialId;
	private String empId;
	private String empName;
	private String ruby;
	private String pass;
	private String entryDate;
	private String deptId;
	private DeptBean dept;
	private Map<LicenseBean, String> licenses;

	/**
	 * 引数なしのコンストラクタ
	 */
	public EmployeeBean() {

	}

	/**
	 * 引数2つのコンストラクタ
	 *
	 * @param empId:社員ID
	 * @param pass:ログインパスワード
	 */
	public EmployeeBean(String empId, String pass) {
		this.empId = empId;
		this.pass = pass;
	}

	/**
	 * 引数3つのコンストラクタ
	 *
	 * @param empId:社員ID
	 * @param empName:社員の名前
	 * @param dept:部門IDと部門名
	 */
	public EmployeeBean(String empId, String empName, DeptBean dept) {
		this.empId = empId;
		this.empName = empName;
		this.dept = dept;
	}

	/**
	 * 引数5つのコンストラクタ
	 *
	 * @param empId:社員ID
	 * @param empName:社員の名前
	 *
	 *
	 * @param deptId:部門ID
	 */
	public EmployeeBean(String empId, String empName, String deptId, String pass, String entryDate) {
		this.empId = empId;
		this.empName = empName;
		this.deptId = deptId;
		this.pass = pass;
		this.entryDate = entryDate;
	}

	/**
	 * 引数6つのコンストラクタ
	 *
	 * @param empId:社員ID
	 * @param empName:社員の名前
	 * @param ruby:社員の名前のふり
	 * @param pass:ログインパスワード
	 * @param entryDate:社員の入社年月日
	 * @param dept:部門IDと部門名
	 */

	public EmployeeBean(String empId, String empName, String ruby, String pass, String entryDate, DeptBean dept) {
		this.empId = empId;
		this.empName = empName;
		this.ruby = ruby;
		this.pass = pass;
		this.entryDate = entryDate;
		this.dept = dept;
	}

	public EmployeeBean(String propertyId, String serialId, String empName, String ruby, String pass,
			String entryDate) {
		this.propertyId = propertyId;
		this.serialId = serialId;
		this.empName = empName;
		this.ruby = ruby;
		this.pass = pass;
		this.entryDate = entryDate;

	}

	/**
	 * メソッド名:getEmpId 説明:社員IDを返す
	 *
	 * @return empId
	 */
	public String getEmpId() {
		return this.empId;
	}

	/**
	 * メソッド名:getEmpName 説明:社員の名前を返す
	 *
	 * @return empName
	 */
	public String getEmpName() {
		return this.empName;
	}

	/**
	 * メソッド名:getRuby 説明:社員の名前のふりがなを返す
	 *
	 * @return ruby
	 */
	public String getRuby() {
		return this.ruby;
	}

	/**
	 * メソッド名:getPass 説明:ログインパスワードを返す
	 *
	 * @return pass
	 */
	public String getPass() {
		return this.pass;
	}

	/**
	 * メソッド名:getEntryDate 説明:社員の入社年月日を返す
	 *
	 * @return entryDate
	 */
	public String getEntryDate() {
		return this.entryDate;
	}

	/**
	 * メソッド名:getDept 説明:部門IDと部門名を返す
	 *
	 * @return dept
	 */
	public DeptBean getDept() {
		return this.dept;
	}

	/**
	 * メソッド名:setEmpName 説明:社員の名前を設定する
	 *
	 * @param name
	 *            セットする empName:設定された社員の名前
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * メソッド名:setRuby 説明:社員の名前のふりがなを設定する
	 *
	 * @param name
	 *            セットする ruby:設定された社員の名前のふりがな
	 */
	public void setRuby(String ruby) {
		this.ruby = ruby;
	}

	/**
	 * メソッド名:setEntryDate 説明:社員の入社年月日を設定する
	 *
	 * @param name
	 *            セットする entryDate:設定された社員の入社年月日
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * メソッド名:setDept 説明:部門IDと部門名を設定する
	 *
	 * @param name
	 *            セットする dept:設定された部門IDと部門名
	 */
	public void setDept(DeptBean dept) {
		this.dept = dept;
	}

	/**
	 * メソッド名:getLicenses 説明:資格IDと資格名を返す
	 *
	 * @return licenses
	 */
	public Map<LicenseBean, String> getLicenses() {
		return licenses;
	}

	/**
	 * メソッド名:setLicenses 説明:資格IDと資格名を設定する
	 *
	 * @param name
	 *            セットする licenses:設定された資格IDと資格名
	 */
	public void setLicenses(Map<LicenseBean, String> licenses) {
		this.licenses = licenses;
	}

	/**
	 * メソッド名:setPass 説明:パスワードを設定する
	 *
	 * @param pass
	 *            セットする pass:設定されたパスワード
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

}
