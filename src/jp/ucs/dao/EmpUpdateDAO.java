package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：UpdateEmpDAO 処理概要 ：社員情報変更画面のDAO
 * プロジェクト名：HrsmUcs(社員情報変更画面) 作成者 ：高原 優 作成日付：2019/11/11(月)
 */

public class EmpUpdateDAO extends BaseDAO {

	public EmployeeBean employeeFindPart(EmployeeBean Emp) throws HrsmUcsDBException {

		// bean型のインスタンスを生成
		EmployeeBean selectEmp = new EmployeeBean();

		// DB接続
		try (Connection conn = getConnection()) {
			// sqlの準備
			// 社員検索
			StringBuilder sb = new StringBuilder();
			sb.append("select * from employee ");
			sb.append("property_id = ");
			sb.append(Emp.getPropertyId());
			sb.append(Emp.getPropertyId());
			sb.append(" ,serial_id = ");
			sb.append(Emp.getSerialId());
			sb.append(" ,emp_name = ");
			sb.append(Emp.getEmpName());
			sb.append(" ,ruby = ");
			sb.append(Emp.getRuby());
			sb.append(" ,pass = ");
			sb.append(Emp.getPass());
			sb.append(" ,entry_date = ");
			sb.append(Emp.getEntryDate());
			sb.append(" ,dept_id = ");
			sb.append(Emp.getDept().getDeptId());

			// sqlの実行
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();

			// DBから情報を取り出し、インスタンスに格納
			while (rs.next()) {
				String propertyId = rs.getString("property_id");
				String serialId = rs.getString("serial_id");
				String empName = rs.getString("emp_name");
				String ruby = rs.getString("ruby");
				String pass = rs.getString("pass");
				String entryDate = rs.getString("entry_Date");
				String deptId = rs.getString("dept_id");
				DeptBean dept = new DeptBean(deptId, "");
				selectEmp = new EmployeeBean(propertyId, serialId, empName, ruby, pass, entryDate, dept);

			}

		} catch (SQLException e) {
			throw new HrsmUcsDBException();
		}
		return selectEmp;
	}

	/**
	 * メソッド名:registerEmp 説明: 社員情報を登録する。
	 *
	 * @param: registerEmp
	 * @return true
	 */

	public EmployeeBean empUpdate(EmployeeBean EmpInfo) throws HrsmUcsDBException {

		// DB接続
		try (Connection conn = getConnection()) {
			// SQL文の準備
			// 社員情報の更新
			StringBuilder sb = new StringBuilder();
			sb.append("update employee set ");
			// sb.append("property_id = ");
			// sb.append(EmpInfo.getPropertyId());
			// sb.append(" ,serial_id = ");
			// sb.append(EmpInfo.getSerialId());
			sb.append("emp_name = ' ");
			sb.append(EmpInfo.getEmpName());
			sb.append("' ,ruby = '");
			sb.append(EmpInfo.getRuby());
			sb.append("' ,pass = '");
			sb.append(EmpInfo.getPass());
			sb.append("' ,entry_date = '");
			sb.append(EmpInfo.getEntryDate());
			sb.append("' ,dept_id = '");
			sb.append(EmpInfo.getDept().getDeptId());
			sb.append("' ");
			sb.append("where property_id = '");
			sb.append(EmpInfo.getPropertyId());
			sb.append("' and ");
			sb.append("serial_id = '");
			sb.append(EmpInfo.getSerialId());
			sb.append("' ;");

			// update文の実行
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			// データベースに登録
			pStmt.executeUpdate();

			// propertyIdとserialIdを結合したものをempIdにする
			String empId = EmpInfo.getPropertyId() + EmpInfo.getSerialId();

			EmpInfo.setEmpId(empId);

			// 登録出来たらEmpInfoを返す
			return EmpInfo;

		} catch (SQLException e) {
			throw new HrsmUcsDBException();
		}

	}
}
