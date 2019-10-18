package jp.ucs.logic;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.EmployeeDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：LoginLogic
 * 処理概要  ：ログイン画面のlogic
 * プロジェクト名：HrsmUcs(ログイン画面)
 * 作成者    ：高原 優
 * 作成日付：2019/07/09(火)
 */

public class LoginLogic {

	/**
	 * メソッド名:loginExecute
	 * 説明: 管理者のログイン処理を行う。
	 * @param:  employeeBean
	 * @return  employeeDAO.findByEmployee(employeeBean)
	 */

	public boolean loginExecute(EmployeeBean employee) throws HrsmUcsDBException{
		EmployeeDAO dao = new EmployeeDAO();
		if(dao.findByEmployee(employee) != null && employee.getEmpName()!= null){

			return true;
		}else{

			return false;
		}
	}
}
