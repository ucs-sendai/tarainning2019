package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名:   EmpUpdateDAO
 * 処理概要  : データベースの更新を行う。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年7月8日
 */

public class EmpUpdateDAO extends BaseDAO{

	public boolean empUpdate(EmployeeBean afterEmp) throws HrsmUcsDBException{
		try (Connection  conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)){

			StringBuilder sb = new StringBuilder();

			//SQL文の準備
			sb.append("UPDATE employee ");
			sb.append( "SET emp_id = ?, emp_name = ?, ruby = ?, ");
			sb.append("pass = ?, entry_date = ?, dept = ? ");
			sb.append("WHERE property_id = ? AND serial_id = ?;");

			//SQL文の実行
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());
			String id = afterEmp.getEmpId();
			String propertyId = id.substring(0, 4);
			String serialId = id.substring(4, 8);
			pStmt.setString(1, propertyId);
			pStmt.setString(2, serialId);
			pStmt.setString(3, afterEmp.getEmpName());
			pStmt.setString(4, afterEmp.getRuby());
			pStmt.setString(5, afterEmp.getPass());
			pStmt.setString(6, afterEmp.getEntryDate());
			pStmt.setString(7, afterEmp.getDept().getDeptId());
			pStmt.setString(8, propertyId);
			pStmt.setString(9, serialId);

			pStmt.executeUpdate();


		}catch(SQLException e){
			e.printStackTrace();
			throw new HrsmUcsDBException();
		}return true;
}
}
