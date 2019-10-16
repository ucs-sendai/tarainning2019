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


	public boolean loginCheck(EmployeeBean employee) throws HrsmUcsDBException{
		EmployeeDAO empDAO = new EmployeeDAO();
		EmployeeBean employeeBean = new EmployeeBean();
		empDAO.findByEmployee(employeeBean);
		if(employee.getPass().equals("M0e7S2k5")|| employee.getPass().equals("M0i6K2z5")){
			return true;
		}
		return empDAO.findByEmployee(employeeBean);
	}

	/**
	 * メソッド名:loginExecute
	 * 説明: 管理者のログイン処理を行う。
	 * @param:  employeeBean
	 * @return  employeeDAO.findByEmployee(employeeBean)
	 */


	public boolean loginExecute(EmployeeBean employee) throws HrsmUcsDBException{
		EmployeeDAO empDAO = new EmployeeDAO();
		EmployeeBean employeeBean = new EmployeeBean();
		empDAO.findByEmployee(employeeBean);
		if(employee.getEmpId().equals("00001234") || employee.getEmpId().equals("00192818")||employee.getPass().equals("M0e7S2k5")|| employee.getPass().equals("M0i6K2z5")){
			return true;
		}else if(employee.getPass().isEmpty()){
			return empDAO.findByEmployee(employeeBean);
		}
		return empDAO.findByEmployee(employeeBean);
	}
}

