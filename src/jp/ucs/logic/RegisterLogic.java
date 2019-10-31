package jp.ucs.logic;

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
	public ArrayList checkEmp(EmployeeBean EmpList) {

		ArrayList<String> arrayList = new ArrayList<String>();

		// 受け取った値の入力チェック
		if (EmpList.getEmpName().equals("") || EmpList.getEmpName().length() > 30) {
			arrayList.add(MessageConstants.REGEMP_ERR01);
		}

		if ((EmpList.getRuby().equals("") || EmpList.getRuby().length() > 40)) {
			arrayList.add(MessageConstants.REGEMP_ERR02);
		}

		if (EmpList.getDept().getDeptId().equals("null")) {
			arrayList.add(MessageConstants.REGEMP_ERR03);
		}

		if (EmpList.getPass().equals("") || EmpList.getPass().length() > 16 || EmpList.getPass().length() < 8) {
			arrayList.add(MessageConstants.REGEMP_ERR04);
		}

		if (EmpList.getEntryDate().equals("") || EmpList.getEntryDate().length() != 10) {
			arrayList.add(MessageConstants.REGEMP_ERR05);
		}

		return arrayList;
	}
}