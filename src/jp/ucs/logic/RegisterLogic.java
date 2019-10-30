package jp.ucs.logic;

import jp.ucs.bean.EmployeeBean;
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
}