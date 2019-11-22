package jp.ucs.logic;

import java.text.DateFormat;
import java.util.ArrayList;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.EmpInsertDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：RegisterLogic 処理概要 ：社員登録画面のlogic プロジェクト名：HrsmUcs(社員登録画面)
 * 作成者 ：高原 優 作成日付：2019/10/24(月)
 */

public class RegisterLogic {

	public boolean registerExecute(EmployeeBean registerEmp) throws HrsmUcsDBException {

		// daoクラスの生成
		EmpInsertDAO empInsert = new EmpInsertDAO();

		// employeeテーブルの一般社員のserialIdのmax値を取得
		String serial = empInsert.employeeFindPart();

		// serialID カウントアップ
		int id = Integer.parseInt(serial) + 1;
		String serialId = String.format("%04d", id);

		// propertyID 年度する
		String propertyId = registerEmp.getEntryDate().substring(0, 4);

		// registerEmpの中のpropertyIdとserialIdを上書きする
		registerEmp.setPropertyId(propertyId);
		registerEmp.setSerialId(serialId);

		// beanのインスタンスを生成して、値を代入
		EmployeeBean insertEmp = empInsert.registerEmp(registerEmp);

		// EmpInsertDAOの結果を判定
		return insertEmp.getEmpId() != null;
	}

	// 入力チェック
	public ArrayList<String> checkEmp(EmployeeBean EmpList) {

		ArrayList<String> arrayList = new ArrayList<String>();

		// 受け取った値の入力チェック
		// 名前が未入力
		if (EmpList.getEmpName().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR01);
		}

		// 名前:入力制限値超え
		if (EmpList.getEmpName().length() > 30) {
			arrayList.add(MessageConstants.REGEMP_ERR06);
		}

		// ふりがな:未入力
		if (EmpList.getRuby().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR02);
		}

		// ふりがな:入力制限値超え
		if (EmpList.getRuby().length() > 40) {
			arrayList.add(MessageConstants.REGEMP_ERR07);
		}

		// ふりがな:ひらがな判定
		if (!(EmpList.getRuby().matches("^[\\u3040-\\u309F]+$")) && !(EmpList.getRuby().equals(""))) {
			arrayList.add(MessageConstants.REGEMP_ERR10);
		}

		// 部門 : 未選択
		if (EmpList.getDept().getDeptId().equals("null")) {
			arrayList.add(MessageConstants.REGEMP_ERR03);
		}

		// PASS : 未入力
		if (EmpList.getPass().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR04);
		}

		// PASS : 入力制限値超え
		// PASS : 入力制限値未満
		else if (EmpList.getPass().length() < 8 || EmpList.getPass().length() > 16) {
			arrayList.add(MessageConstants.REGEMP_ERR08);
		}

		// 入社年月日 : 未入力
		if (EmpList.getEntryDate().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR05);
		}

		// 入社年月日 : 規定値ではない
		else if (EmpList.getEntryDate().length() != 10) {
			arrayList.add(MessageConstants.REGEMP_ERR09);
		}

		return arrayList;
	}

	public boolean checkDate(String strDate) {

		DateFormat format = DateFormat.getDateInstance();
		format.setLenient(false);

		try {
			format.parse(strDate);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}