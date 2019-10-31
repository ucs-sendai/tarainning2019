package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.RegisterLogic;

/**
 * システム名：社員管理システム クラス名 ：EmpInsertDAO 処理概要 :社員を登録する。 プロジェクト名：Hrsm 作成者 ：小西香菜子
 * 作成日付：2019年9月30日
 */

public class EmpInsertDAO extends BaseDAO {

	/**
	 * メソッド名:searchEmp 説明: 登録されている社員情報を検索する。
	 *
	 * @param: employeeBean
	 * @return empId
	 */

	public String searchEmp(EmployeeBean empbean) throws HrsmUcsDBException {

		// serialIDの初期値を設定
		String empId = "";
		String serialId = "";
		String maxId = "";

		try (Connection conn = getConnection()) {

			// serial_idの最大値を取得
			StringBuilder sb = new StringBuilder();
			sb.append("select max(serial_id) as max from employee");
			sb.append("where property_id = '");
			sb.append(empbean.getEntryDate().substring(0, 4));
			sb.append("';");

			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				maxId = rs.getString("max");

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return maxId;

	}

	/**
	 * メソッド名:registerEmp 説明: 社員情報を追加する。
	 *
	 * @param: employeeBean
	 * @return empId
	 */

	public boolean registerEmp(EmployeeBean empbean) throws HrsmUcsDBException {

		RegisterLogic regist = new RegisterLogic();
		regist.registerExecute(empbean);

		try (Connection conn = getConnection()) {

			StringBuilder sb = new StringBuilder();

			// employeeテーブルに新しいレコードとして挿入
			sb.append("insert into employee(property_id,serial_id, emp_name,ruby,pass,entry_date,dept_id)");
			sb.append("values('");
			sb.append(empbean.getEntryDate().substring(0, 4));
			sb.append(" ','");
			sb.append(regist.registerExecute(empbean));
			sb.append(" ','");
			sb.append(empbean.getEmpName());
			sb.append(" ','");
			sb.append(empbean.getRuby());
			sb.append(" ','");
			sb.append(empbean.getPass());
			sb.append(" ','");
			sb.append(empbean.getEntryDate());
			sb.append(" ','");
			sb.append(empbean.getDept().getDeptId());
			sb.append(" ');");

			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			pStmt = conn.prepareStatement(sb.toString());

			int result = pStmt.executeUpdate();

			if (result != 1) {
				return result == 1;

			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new HrsmUcsDBException();
		}
		return true;

	}
}
