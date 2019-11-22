package jp.ucs.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.bean.LicenseBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.insertLicenseDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名：RegisterLicenseLogic 処理概要 ：資格登録画面のlogic
 * プロジェクト名：HrsmUcs(資格登録画面) 作成者 ：高原 優 作成日付：2019/11/19(火)
 */

public class RegisterLicenseLogic {

	public Map<String, String> employeeFindPart(EmployeeBean InfoEmp) throws HrsmUcsDBException {

		// LicenseBean型のリストを作成
		// ArrayList<LicenseBean> license = new ArrayList<LicenseBean>();

		// empIdをpropertyIdとserialIdに分割
		InfoEmp.setPropertyId(InfoEmp.getEmpId().substring(0, 4));
		InfoEmp.setSerialId(InfoEmp.getEmpId().substring(4, 8));

		// daoクラスの生成
		insertLicenseDAO licenseDAO = new insertLicenseDAO();

		// Mapを生成して、値を代入
		Map<String, String> licenseMap = licenseDAO.licenseFindPart(InfoEmp);

		// Map型の変数を返す
		return licenseMap;

	}

	public boolean registerLicenseExecute(LicenseBean license) throws HrsmUcsDBException {

		// daoクラスの生成
		insertLicenseDAO licenseInsert = new insertLicenseDAO();

		// beanのインスタンスを生成して、値を代入
		LicenseBean licenseBean = licenseInsert.registerLicense(license);

		// EmpInsertDAOの結果を判定
		return licenseBean != null;
	}

	public ArrayList<String> checkLicense(LicenseBean licenseList) {

		// リスト型の作成
		ArrayList<String> LicenseCheck = new ArrayList<String>();

		// 受け取った値の入力チェック
		// 資格の未選択
		if (licenseList.getLicenseId().equals("")) {
			LicenseCheck.add(MessageConstants.LICENS_ERR12);
		}

		// 日付が未入力
		if (licenseList.getLicenseDate().equals("")) {
			LicenseCheck.add(MessageConstants.REGEMP_ERR05);
		}

		// 日付が異常値①
		if (licenseList.getLicenseDate().length() != 10) {
			LicenseCheck.add(MessageConstants.REGEMP_ERR09);
		}

		// 日付が異常値②
		DateFormat format = new SimpleDateFormat(licenseList.getLicenseDate());
		try {
			format.setLenient(false);
			format.parse(licenseList.getLicenseDate());

		} catch (Exception e) {
			LicenseCheck.add(MessageConstants.REGEMP_ERR11);
		}
		return LicenseCheck;

	}

}