package jp.ucs.logic;

import java.util.ArrayList;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.DeleteEmpDAO;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：DeleteEmpLogic 処理概要 ：社員削除画面のlogic
 * プロジェクト名：HrsmUcs(社員削除画面) 作成者 ：高原 優 作成日付：2019/11/1(金)
 */

public class DeleteEmpLogic {

	public boolean deleteExecute(EmployeeBean empDelete) throws HrsmUcsDBException {

		// bean型のリストを生成
		EmployeeBean deleteEmp = new EmployeeBean();

		// daoクラスの生成
		DeleteEmpDAO deletedao = new DeleteEmpDAO();

		// empDeleteのpropertyIdとserialIdをEmpIdにする
		empDelete.setEmpId(empDelete.getPropertyId() + empDelete.getSerialId());

		// DeptFindAlllogicクラスの生成
		DeptFindAllLogic deptLogic = new DeptFindAllLogic();
		deptLogic.deptExecute();

		// deptNameの取得
		String deptName = deptLogic.deptExecute().get(empDelete.getDept().getDeptId());

		DeptBean dept = new DeptBean();
		dept.setDeptName(deptName);

		dept.setDeptId(empDelete.getDept().getDeptId());

		empDelete.setDept(dept);

		// employeebeanのインスタンスを生成して、値を代入
		deleteEmp = deletedao.deleteEmp(empDelete);

		// DeleteEmptDAOの結果を判定
		return deleteEmp != null;

	}

	public ArrayList<String> checkEmp(EmployeeBean EmpList) {

		// リストの生成
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

		// 部門 : 未選択
		if (EmpList.getDept().getDeptId().equals("null")) {
			arrayList.add(MessageConstants.REGEMP_ERR03);
		}

		return arrayList;

	}

}