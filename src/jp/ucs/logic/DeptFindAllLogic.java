package jp.ucs.logic;

import java.util.Map;

import jp.ucs.dao.DeptDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：DeptFindAllLogic
 * 処理概要   :社員の部門を検索する。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年7月8日
 */

public class DeptFindAllLogic {
		public Map<String, String> deptExecute() throws HrsmUcsDBException{
	        DeptDAO deptDAO = new DeptDAO();

	        return deptDAO.deptFindAll();
	    }
	}

