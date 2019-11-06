package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：DeleteEmpDAO 処理概要 ：社員削除画面のDAO プロジェクト名：HrsmUcs(社員削除画面)
 * 作成者 ：高原 優 作成日付：2019/11/1(金)
 */

public class DeleteEmpDAO extends BaseDAO {

	/**
	 *
	 * // 削除する社員の選択結果を保存する public EmployeeBean employeeFindPart(EmployeeBean
	 * SelectEmp) throws HrsmUcsDBException {
	 *
	 * // Bean型のリストを作成する //List<EmployeeBean> empList = new ArrayList<>();
	 *
	 * // DBに接続する try (Connection conn = getConnection()) {
	 *
	 * // 削除する社員の選択 StringBuilder sb = new StringBuilder(); sb.append(
	 * "select * "); sb.append("from employee "); sb.append("where ");
	 * sb.append("property_id = "); sb.append(SelectEmp.getPropertyId());
	 * sb.append(", and"); sb.append("serial_id = ");
	 * sb.append(SelectEmp.getSerialId()); sb.append(", and "); sb.append(
	 * "dept_id = "); sb.append(SelectEmp.getDept().getDeptId()); sb.append("; "
	 * );
	 *
	 * // select文の実行 PreparedStatement pstmt =
	 * conn.prepareStatement(sb.toString()); ResultSet rs =
	 * pstmt.executeQuery();
	 *
	 * // DBから情報を取り出し、インスタンスに格納 while (rs.next()) { String propertyId =
	 * rs.getString("property_id"); String serialId = rs.getString("serial_id");
	 * String empName = rs.getString("emp_name"); String ruby =
	 * rs.getString("ruby"); String pass = rs.getString("pass"); String
	 * entryDate = rs.getString("entry_Date"); String deptId =
	 * rs.getString("dept_id"); String deptName = rs.getString("dept_name");
	 * DeptBean dept = new DeptBean(deptId, deptName); EmployeeBean employeeBean
	 * = new EmployeeBean(propertyId, serialId, empName, ruby, pass, entryDate,
	 * dept); empList.add(employeeBean); } } catch (SQLException e) {
	 * e.printStackTrace(); return null; } return empList; }
	 *
	 */

	// 社員を削除する

	public EmployeeBean deleteEmp(EmployeeBean EmpInfo) throws HrsmUcsDBException {

		// DB接続
		try (Connection conn = getConnection()) {

			// SQL文の準備
			// employeeテーブルの社員情報削除
			StringBuilder sb = new StringBuilder();
			sb.append("delete ");
			sb.append("from ");
			sb.append("employee ");
			sb.append("where ");
			sb.append("property_id = '");
			sb.append(EmpInfo.getPropertyId());
			sb.append("' and ");
			sb.append("serial_id = '");
			sb.append(EmpInfo.getSerialId());
			sb.append("' and ");
			sb.append("dept_id = '");
			sb.append(EmpInfo.getDept().getDeptId());
			sb.append("' ; ");

			// delete文の実行
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			// データベースの更新
			pStmt.executeUpdate();

			// 登録出来たらEmpInfoを返す
			return EmpInfo;
		}

		// DB接続エラーがでたら、HrsmUcsDBExceptionをスローする
		catch (SQLException e) {
			throw new HrsmUcsDBException();
		}
	}

}
