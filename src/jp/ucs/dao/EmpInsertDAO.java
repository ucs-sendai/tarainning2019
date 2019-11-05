package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム クラス名 ：EmpInsertDAO 処理概要 ：社員登録画面のDAO プロジェクト名：HrsmUcs(社員登録画面)
 * 作成者 ：高原 優 作成日付：2019/10/21(月)
 */

public class EmpInsertDAO extends BaseDAO {

	public String employeeFindPart() throws HrsmUcsDBException {

		// serialIdを入れる変数を用意
		String serialId = null;

		try (Connection conn = getConnection()) {

			// serialIdのmax値を取得
			StringBuilder sb = new StringBuilder();
			sb.append("select max(serial_id) as serial_id ");
			sb.append("from employee  ");
			sb.append("where ");
			sb.append("property_id not in ('0000'); ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();

			// DBから情報を取り出す
			while (rs.next()) {
				serialId = rs.getString("serial_id");
			}
		} catch (SQLException e) {
			throw new HrsmUcsDBException();
		}
		return serialId;
	}

	/**
	 * メソッド名:registerEmp 説明: 社員情報を登録する。
	 *
	 * @param: registerEmp
	 * @return true
	 */

	public EmployeeBean registerEmp(EmployeeBean EmpInfo) throws HrsmUcsDBException {
		// DB接続
		try (Connection conn = getConnection()) {
			// SQL文の準備
			// employeeテーブルに社員情報を追加
			StringBuilder sb = new StringBuilder();
			sb.append("insert into ");
			sb.append("employee ( ");
			sb.append("property_id,serial_id,emp_name,ruby,pass,entry_date,dept_id ) ");
			sb.append("values( ");
			sb.append("'");
			sb.append(EmpInfo.getPropertyId());
			sb.append("' , ");
			// sb.append(", ");
			sb.append("'");
			sb.append(EmpInfo.getSerialId());
			sb.append("' , ");
			// sb.append(", ");
			sb.append("'");
			sb.append(EmpInfo.getEmpName());
			sb.append("' , ");
			// sb.append(", ");
			sb.append("'");
			sb.append(EmpInfo.getRuby());
			sb.append("' , ");
			// sb.append(", ");
			sb.append("'");
			sb.append(EmpInfo.getPass());
			sb.append("' , ");
			// sb.append(", ");
			sb.append("'");
			sb.append(EmpInfo.getEntryDate());
			sb.append("' , ");
			// sb.append(", ");
			sb.append("'");
			sb.append(EmpInfo.getDept().getDeptId());
			sb.append("'); ");

			// insert文の実行
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			// データベースに登録
			pStmt.executeUpdate();

			// propertyIdとserialIdを結合したものをempIdにする
			String empId = EmpInfo.getPropertyId() + EmpInfo.getSerialId();

			EmpInfo.setEmpId(empId);

			// 登録出来たらEmpInfoを返す
			return EmpInfo;
		}

		// DB接続エラーがでたら、HrsmUcsDBExceptionをスローする
		catch (SQLException e) {
			throw new HrsmUcsDBException();
		}
	}
}
