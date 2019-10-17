/**
システム名：社員管理システム
クラス名：License
処理概要：資格クラス
プロジェクト名：HrsmUcs(共通)
作成者    ：スピェッウォン
作成日付  ：2019/06/20(水)
 */
package jp.ucs.bean;

import java.util.List;

public class LicenseBean {
	private String licenseId;
	private String licenseName;
	private String id;
	private String dept;
	private String name;
	private List license;

	/**
	 * 引数なしのコンストラクタ
	 */
	public LicenseBean() {

	}

	/**
	 *引数2つのコンストラクタ
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(String licenseId, String licenseName) {
		this.licenseId = licenseId;
		this.licenseName = licenseName;
	}

	/**
	 *引数2つのコンストラクタ
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(String licenseId, List license) {
		this.licenseId = licenseId;
		this.license = license;
	}

	/**
	 *引数4つのコンストラクタ
	 * @param licenseId:資格のID
	 * @param licenseName:資格名
	 */
	public LicenseBean(String id,String name,String dept, List license) {
		this.id= id;
		this.name = name;
		this.dept = dept;
		this.license = license;
	}

	/**
	 *メソッド名:getLicenseId
	 * 説明:資格IDを返す
	 *  @return licenseId
	 */
	public String getLicenseId() {
		return this.licenseId;
	}

	/**
	 *メソッド名:getLicenseName
	 * 説明:資格名を返す
	 *  @return licenseName
	 */
	public String getLicenseName() {
		return this.licenseName;
	}

	/**
	 *メソッド名:getid
	 * 説明:資格名を返す
	 *  @return id
	 */
	public String getid() {
		return this.id;
	}

	/**
	 *メソッド名:getdept
	 * 説明:資格名を返す
	 *  @return dept
	 */
	public String getdept() {
		return this.dept;
	}

}
