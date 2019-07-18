/**
 * システム名：社員管理システム
 * クラス名：HaveLicense
 * 処理概要：資格取得クラス
 * プロジェクト名  ：HrsmUcs(共通)
 * 作成者   ：エーチャンス
 * 作成日付：2019/06/20(木)
 */
package jp.ucs.bean;

public class HaveLicenseBean {

	private EmployeeBean employee;
	private LicenseBean license;
	private String licenseDate;

	/**
	 * ディフォルトコンストラク
	 */
	public HaveLicenseBean() {

	}

	/**
	 * 引数２つのコンストラクタ
	 * @param empId：社員ID
	 * @param licenseId：資格ID
	 * @param licenseDate：資格取得年月日
	 */
	public HaveLicenseBean(EmployeeBean employee, LicenseBean license, String licenseDate) {
		this.employee = employee;
		this.license = license;
		this.licenseDate = licenseDate;
	}

	/**
	 * メソッド名：getEmployee
	 * 説明：社員IDと社員名を返す
	 * @return empId:社員IDと社員名の戻り値
	 */
	public EmployeeBean getEmployee() {
		return this.employee;
	}

	/**
	 * メソッド名：getLicense
	 * 説明：資格IDと資格名を返す
	 * @return licenseId:資格IDと資格名の戻り値
	 */
	public LicenseBean getLicense() {
		return this.license;
	}

	/**
	 * メソッド名：getLicenseDate
	 * 説明：資格取得年月日を返す
	 * @return licenseDate:資格取得年月日の戻り値
	 */
	public String getLicenseDate() {
		return this.licenseDate;
	}

}
