package jp.ucs.logic;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.EmpInsertDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：RegisterLogic 処理概要 :社員の登録処理をする。 プロジェクト名：Hrsm 作成者 ：小西香菜子
 * 作成日付：2019年9月30日
 */

public class RegisterLogic {

	public boolean registerExecute(EmployeeBean empbean) throws HrsmUcsDBException {

		// select
		EmpInsertDAO empinDAO = new EmpInsertDAO();
		String maxId = empinDAO.searchEmp(empbean);

		// 最大値に1加算
		int intMaxId = Integer.parseInt(maxId) + 1;
		String serialId = String.format("%04d", intMaxId);

		// propertyId,serialIdをbeanに追加
		String propertyId = empbean.getEmpId().substring(0, 4);
		EmployeeBean employeeBean = new EmployeeBean(propertyId, serialId);

		// 追加
		EmpInsertDAO empIn = new EmpInsertDAO();
		if (empIn.registerEmp(employeeBean)) {
			return true;
		}

		return false;
	}

}
