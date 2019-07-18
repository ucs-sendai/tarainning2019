package jp.ucs.logic;

import jp.ucs.dao.EmployeeDAO;
/**
 * システム名：社員管理システム
 * クラス名  ：LoginLogoc
 * 処理概要  ：ログイン画面のlogic
 * プロジェクト名：HrsmUcs(ログイン画面)
 * 作成者    ：高原 優
 * 作成日付：2019/07/09(火)
 */


public class LoginLogic {
	public boolean loginCheck(){
		EmployeeDAO empdao = new EmployeeDAO();
		if(empdao.findByEmployee == null){
			return false;
		}else{
			return true;
		}

	}


}
