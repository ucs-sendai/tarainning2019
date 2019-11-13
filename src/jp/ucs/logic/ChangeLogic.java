package jp.ucs.logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.EmpUpdateDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：ChangeLogic 処理概要 ：社員情報変更画面のlogic
 * プロジェクト名：HrsmUcs(社員情報変更画面) 作成者 ：高原 優 作成日付：2019/11/11(月)
 */

public class ChangeLogic {

	public boolean serchEmp(EmployeeBean Emp) throws HrsmUcsDBException {

		// DAOクラスの初期化
		EmpUpdateDAO EmpDao = new EmpUpdateDAO();

		// Beanのインスタンスを生成し、社員情報を入れる
		EmployeeBean employee = EmpDao.employeeFindPart(Emp);

		// 社員情報があればtrueを返す
		// 社員情報がなければfalseを返す
		return employee != null;
	}

	/**
	 * メソッド名:loginExecute 説明: 管理者のログイン処理を行う。
	 *
	 * @param: employeeBean
	 * @return employeeDAO.findByEmployee(employeeBean)
	 */

	public boolean empUpdate(EmployeeBean afterEmp) throws HrsmUcsDBException {

		// daoクラスの初期化
		EmpUpdateDAO updateDAO = new EmpUpdateDAO();

		// empIdをpropertyIdとserialIdに分割
		String propertyId = afterEmp.getEmpId().substring(0, 4);
		String serialId = afterEmp.getEmpId().substring(4, 8);

		// afterEmpの中のpropertyIdとserialIdを上書きする
		afterEmp.setPropertyId(propertyId);
		afterEmp.setSerialId(serialId);

		// Beanのインスタンスを生成し、社員情報を入れる
		EmployeeBean rsltEmpInfo = updateDAO.empUpdate(afterEmp);

		// 社員情報があればtrueを返す
		// 社員情報がnullだったらfalseを返す
		return rsltEmpInfo != null;
	}

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
		if (!(EmpList.getRuby().matches("^[\\u3040-\\u309F]+$"))) {
			arrayList.add(MessageConstants.REGEMP_ERR10);
		}

		// 部門 : 未選択
		if (EmpList.getDept().getDeptId().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR03);
		}

		// PASS : 未入力
		if (EmpList.getPass().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR04);
		}

		// PASS : 入力制限値超え
		// PASS : 入力制限値未満
		if (EmpList.getPass().length() < 8 || EmpList.getPass().length() > 16) {
			arrayList.add(MessageConstants.REGEMP_ERR08);
		}

		// 入社年月日 : 未入力
		if (EmpList.getEntryDate().equals("")) {
			arrayList.add(MessageConstants.REGEMP_ERR05);
		}

		// 入社年月日 : 規定値ではない
		if (EmpList.getEntryDate().length() != 10) {
			arrayList.add(MessageConstants.REGEMP_ERR09);
		}

		// 入社年月日 : 無効な年
		DateFormat format = new SimpleDateFormat(EmpList.getEntryDate());
		try {
			format.setLenient(false);
			format.parse(EmpList.getEntryDate());

		} catch (Exception e) {
			arrayList.add(MessageConstants.REGEMP_ERR11);
		}

		return arrayList;
	}
}