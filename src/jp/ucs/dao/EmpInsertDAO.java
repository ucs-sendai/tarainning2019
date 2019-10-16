package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：EmpInsertDAO
 * 処理概要   :社員を登録する。
 * プロジェクト名：Hrsm
 * 作成者    ：小西香菜子
 * 作成日付：2019年9月30日
 */

public class EmpInsertDAO extends BaseDAO{
	public String registerEmp (EmployeeBean empbean) throws HrsmUcsDBException{

		// 社員IDの初期値を設定
		String empId = "";
		String serialId = "";


		try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

			Class.forName(DRIVER_NAME);
			// serial_idの最大値を取得
			StringBuilder sb = new StringBuilder();
			sb.append("select max(serial_id) as max from employee");
			sb.append("group by property_id having property_id = '?';");

			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			try {
				pStmt.setString(1,empbean.getEntryDate().substring(0,4));
			} catch (SQLException e) {

				e.printStackTrace();
			}

			ResultSet rs = pStmt.executeQuery();


			while(rs.next()) {
				String maxId = rs.getString("max");

				// 最大値に1加算
				int intMaxId = Integer.parseInt(maxId) +1;
				serialId = String.format("%04d",intMaxId);
			}

			//employeeテーブルに新しいレコードとして挿入
			sb.append("insert into employee(property_id,serial_id, emp_name,ruby,pass,entry_date,dept_id)");
			sb.append("values('?','?','?','?','?','?','?');");

			pStmt = conn.prepareStatement(sb.toString());

			pStmt.setString(1, empbean.getEntryDate().substring(0,4));
			pStmt.setString(2, serialId);
			pStmt.setString(3, empbean.getEmpName());
			pStmt.setString(4, empbean.getRuby());
			pStmt.setString(5, empbean.getPass());
			pStmt.setString(6, empbean.getEntryDate());
			pStmt.setString(7, empbean.getDept().getDeptId());

			int result = pStmt.executeUpdate();

			 if(result != 1){
	                return null;
	            }

			empId = empbean.getEntryDate().substring(0,4) + serialId ;
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return empId;

	}
}

