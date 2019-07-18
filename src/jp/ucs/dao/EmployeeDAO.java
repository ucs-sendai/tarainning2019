package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jp.ucs.logic.LoginLogic;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.BaseDAO;

/**
 * システム名：社員管理システム
 * クラス名  ：EmployeeDAO
 * 処理概要  ：ログイン画面のDAO
 * プロジェクト名：HrsmUcs(ログイン画面)
 * 作成者    ：高原 優
 * 作成日付：2019/07/10(水)
 */

public class EmployeeDAO extends BaseDAO{
	//データベース接続に関する情報
	super.DB_URL();
	super.DB_ID();
	super.PWD();

	public List<EmployeeBean> findAll(){
		List<EmployeeBean>employeeList = new ArrayList<>();
		//データベース接続
		try(Connection getConnection()){
			//select文の準備
			StringBuilder sb = new StringBuilder();
			sb.append("select property_id + serial_id,emp_name,ruby,pass,entry_date");
			sb.append("dept_id,dept_name");
			sb.append("from employee innner join dept on employee.dept_id = dept.dept_id");
			//sb.append("employeeId = property_id + serial_id");
			//sb.append("pass = pass");

			//sb.append("where propery_id + serial_id,emp_name,ryby,pass");
			//sb.append("entry_date,dept_id,dept_name");

			//SELECT文を実行
			PreparedStatement pStmt = conn.prepareStatement(sb,toString());
			ResultSet rs = pStmt.executeQuery();

			//dept情報をDeptBeanインスタンスに格納
			DeptBean deptbean = new DeptBean("emp_id","emp_name");

			//SELECT文の結果をArrayListに追加

			while(rs.next()){
				String id = rs.getString("property_id" + "serial_id");
				String name = rs.getString("emp_name");
				String ruby = rs.getString("ruby");
				String pass = rs.getString("pass");
				String date = rs.getString("entry_date");
				String dept = rs.getString("dept_id");
				EmployeeBean emp = new EmployeeBean(id,name,ruby,pass,date,deptbean);
				employeeList.add(emp);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return employeeList;
	}
}
}
}

public static employee findByEmployee(employee){
	for(employeeList ){
		
	}
}