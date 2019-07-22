package jp.ucs.logic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.EmpUpdateDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：ChangeLogic
 * 処理概要  :入力項目の不備を検査し、データベースの更新を行う。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年7月8日
 */

public class ChangeLogic {

	/**
	 * メソッド名:changeExecute
	 * 説明:データベースの更新を行う。
	 * @param: afterEmp
	 * @return empUpdateDAO.empUpdate()
	 */

	public boolean changeExecute(EmployeeBean afterEmp)
			throws HrsmUcsDBException{
		EmpUpdateDAO empUpdateDAO =new EmpUpdateDAO();
		return empUpdateDAO.empUpdate(afterEmp);
	}


	/**
	 * メソッド名:errorCheck
	 * 説明:入力項目の不備を検査する。
	 * @param: afterEmp
	 * @return errorMsgMap
	 */


	public Map<String, String> errorCheck(EmployeeBean beforeEmp , EmployeeBean afterEmp){
		Map<String, String> errorMsgMap = new HashMap<>();
		String message = null;
		int len = afterEmp.getEmpName().length();
		byte[] bytes = afterEmp.getEmpName().getBytes();

		//入力項目の不備を処理（エラーメッセージ表示）

		//名前の不備
		if(afterEmp.getEmpName() == null || afterEmp.getEmpName().length() == 0){

			message = MessageConstants.REGEMP_ERR01;
			errorMsgMap.put("name", message);
			afterEmp.setEmpName(beforeEmp.getEmpName());
			return errorMsgMap;

		}else if(len != bytes.length){

			if (len > 30) {

				message = MessageConstants.REGEMP_ERR06;
				errorMsgMap.put("name", message);
				afterEmp.setEmpName(beforeEmp.getEmpName());
				return errorMsgMap;
			}

			//ふりがなの不備
			if(afterEmp.getRuby() == null || afterEmp.getRuby().length() == 0){
				message = MessageConstants.REGEMP_ERR02;
				errorMsgMap.put("ruby", message);
				afterEmp.setRuby(beforeEmp.getRuby());
				return errorMsgMap;

			}

			if(afterEmp.getRuby().length() > 40){
				message = MessageConstants.REGEMP_ERR07;
				errorMsgMap.put("ruby", message);
				afterEmp.setRuby(beforeEmp.getRuby());
				return errorMsgMap;

			}

			//パスワードの不備
			if(afterEmp.getPass() == null || afterEmp.getPass().length() == 0){
				message = MessageConstants.REGEMP_ERR04;
				errorMsgMap.put("pass", message);
				afterEmp.setPass(beforeEmp.getPass());
				return errorMsgMap;
			}

			if(afterEmp.getPass().length()<8 || afterEmp.getPass().length()>16){
				message = MessageConstants.REGEMP_ERR08;
				errorMsgMap.put("pass", message);
				afterEmp.setPass(beforeEmp.getPass());
				return errorMsgMap;
			}

			//部門の不備
			if(afterEmp.getDept() == null){
				message = MessageConstants.REGEMP_ERR03;
				errorMsgMap.put("dept", message);
				afterEmp.setDept(beforeEmp.getDept());
				return errorMsgMap;
			}

			if(afterEmp.getEntryDate() == null || afterEmp.getEntryDate().length() == 0){
				message = MessageConstants.REGEMP_ERR05;
				errorMsgMap.put("entrydate", message);
				afterEmp.setEntryDate(beforeEmp.getEntryDate());
				return errorMsgMap;
			}

			//入社年月日の不備
			if(afterEmp.getEntryDate().length() != 10){
				message =MessageConstants.REGEMP_ERR09;
				errorMsgMap.put("entrydate", message);
				afterEmp.setEntryDate(beforeEmp.getEntryDate());
				return errorMsgMap;
			}

			//入社年月日を取得

			try {

				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				df.setLenient(false);
				df.parse(afterEmp.getEntryDate());
			} catch(ParseException e) {
				message = MessageConstants.REGEMP_ERR11;
				errorMsgMap.put("date", message);
				afterEmp.setEntryDate(beforeEmp.getEntryDate());
				return errorMsgMap;
			}

		}return errorMsgMap;
	}
}



