/**
システム名：社員管理システム
クラス名：License
処理概要：資格クラス
プロジェクト名：HrsmUcs(共通)
作成者    ：スピェッウォン
作成日付  ：2019/06/20(水)
 */
package jp.ucs.bean;

public class LicenseBean {
	private String licenseId;
	private String licenseName;
	private String propertyId;
	private String serialId;
	private String empId;
	private String empName;
	private String licenseDate;
	private EmployeeBean emp;

	/**
	 * 引数なしのコンストラクタ
	 */
	public LicenseBean() {

	}

	/**
	 * 引数2つのコンストラクタ
	 *
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(String licenseId, String licenseName) {
		this.licenseId = licenseId;
		this.licenseName = licenseName;
	}

	/**
	 * 引数3つのコンストラクタ
	 *
	 * @param emp
	 *            : 社員情報
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(EmployeeBean emp, String licenseId, String licenseName) {
		this.emp = emp;
		this.licenseId = licenseId;
		this.licenseName = licenseName;
	}

	/**
	 * 引数4つのコンストラクタ
	 *
	 * @param empId
	 *            : 社員IDの上4桁
	 * @param empName
	 *            : 社員名
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(String empId, String empName, String licenseId, String licenseName, String licenseDate) {
		this.empId = empId;
		this.empName = empName;
		this.licenseId = licenseId;
		this.licenseName = licenseName;
		this.licenseDate = licenseDate;
	}

	/**
	 * 引数5つのコンストラクタ
	 *
	 * @param propertyId
	 *            : 社員IDの上4桁
	 * @param serialId
	 *            : 社員IDの下4桁
	 * @param empName
	 *            : 社員名
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(String propertyId, String serialId, String empName, String licenseId, String licenseName,
			String licenseDate) {
		this.propertyId = propertyId;
		this.serialId = serialId;
		this.empName = empName;
		this.licenseId = licenseId;
		this.licenseName = licenseName;
		this.licenseDate = licenseDate;
	}

	/**
	 * メソッド名:getLicenseId 説明:資格IDを返す
	 *
	 * @return licenseId
	 */
	public String getLicenseId() {
		return this.licenseId;
	}

	/**
	 * メソッド名:getLicenseName 説明:資格名を返す
	 *
	 * @return licenseName
	 */
	public String getLicenseName() {
		return this.licenseName;
	}

	/**
	 * メソッド名:getLicenseDate 説明:資格取得日を返す
	 *
	 * @return licenseName
	 */
	public String getLicenseDate() {
		return this.licenseDate;
	}

	/**
	 * メソッド名:empId 説明:社員Idを返す
	 *
	 * @return empId
	 */
	public String getEmpId() {
		return this.empId;
	}

	/**
	 * メソッド名:propertyId 説明:社員Idの上4桁を返す
	 *
	 * @return propertyId
	 */
	public String getPropertyId() {
		return this.propertyId;
	}

	/**
	 * メソッド名:serialId 説明:社員Idの下4桁を返す
	 *
	 * @return propertyId
	 */
	public String getSerialId() {
		return this.serialId;
	}

	/**
	 * メソッド名:getName 説明:資格名を返す
	 *
	 * @return empName
	 */
	public String getEmpName() {
		return this.empName;
	}

	/**
	 * メソッド名:getEmp 説明:資格名を返す
	 *
	 * @return empName
	 */
	public EmployeeBean getEmp() {
		return this.emp;
	}

	/**
	 * メソッド名:setEmpId 説明:社員のIdを設定する
	 *
	 * @param empId
	 *            セットする empId:設定された社員のID
	 */
	public void setEmpId(String propertyId) {
		this.empId = empId;
	}

	/**
	 * メソッド名:setPropertyId 説明:社員の名前を設定する
	 *
	 * @param propertyId
	 *            セットする propertyId:設定された社員のID
	 */
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	/**
	 * メソッド名:setSerialId 説明:社員の名前を設定する
	 *
	 * @param serialId
	 *            セットする seralId:設定された社員のID
	 */
	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	/**
	 * メソッド名:setEmpName 説明:社員の名前を設定する
	 *
	 * @param empName
	 *            セットする empName:設定された社員の名前
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * メソッド名:setLicenseId 説明:資格のIDを設定する
	 *
	 * @param licenseId
	 *            セットする licenseId:設定された資格のID
	 */
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	/**
	 * メソッド名:setLicenseName 説明:資格の名前を設定する
	 *
	 * @param licenseName
	 *            セットする licenseName:設定された資格の名前
	 */
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	/**
	 * メソッド名:setLicenseDate 説明:資格取得日を設定する
	 *
	 * @param licenseDate
	 *            セットする licenseDate:設定された資格の名前
	 */
	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	/**
	 * メソッド名:setEmp
	 *
	 * @param licenseDate
	 *            セットする licenseDate:設定された資格の名前
	 */
	public void setEmp(EmployeeBean emp) {
		this.emp = emp;
	}

}
