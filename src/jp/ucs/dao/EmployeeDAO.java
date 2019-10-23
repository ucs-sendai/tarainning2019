package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.exception.HrsmUcsDBException;

/**
 * システム名：社員管理システム
 * クラス名  ：EmployeeDAO
 * 処理概要  ：ログイン画面のDAO
 * プロジェクト名：HrsmUcs(ログイン画面)
 * 作成者    ：高原 優
 * 作成日付：2019/07/10(水)
 */

public class EmployeeDAO extends BaseDAO{

	public List<EmployeeBean> employeeFindAll(){
		List<EmployeeBean> empList = new ArrayList<>();

		try (Connection conn = getConnection()) {

			StringBuilder sb = new StringBuilder();
			sb.append("select * ");
			sb.append("from employee;");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();


			//DBから情報を取り出し、インスタンスに格納
			while (rs.next()) {
				String propertyId = rs.getString("property_id");
				String serialId = rs.getString("serial_id");
				//String empId = rs.getString("emp_id");
				String empName = rs.getString("emp_name");
				String ruby = rs.getString("ruby");
				String pass = rs.getString("pass");
				String entryDate = rs.getString("entry_date");
				String deptId = rs.getString("dept_id");
				//String deptName = rs.getString("dept_name");
				//DeptBean dept = new DeptBean(deptId,deptName);
				EmployeeBean employeeBean = new EmployeeBean(propertyId,serialId, empName, ruby, pass, entryDate);
				empList.add(employeeBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}return empList;
	}

	/**
	 * メソッド名:findByEmployee
	 * 説明: 登録されている社員情報を検索する。
	 * @param:  employeeBean
	 * @return employeeDAO.findByEmployee(employeeBean)
	 */

	public EmployeeBean findByEmployee(EmployeeBean employeeBean) throws HrsmUcsDBException{


		EmployeeBean empbean = new EmployeeBean();
		try (Connection conn = getConnection()) {
			//SQL文の準備
			StringBuilder sb = new StringBuilder();
			sb.append("select property_id ,serial_id,emp_name,ruby,pass,entry_date ");
			sb.append("from employee inner join dept " );
			sb.append("on employee.dept_id = dept.dept_id " );
			sb.append("where property_id = '" );
			sb.append( employeeBean.getEmpId().substring(0,4));
			sb.append("' and serial_id = '" );
			sb.append( employeeBean.getEmpId().substring(4,8));
			sb.append(" 'and pass = '" );
			sb.append(employeeBean.getPass());
			sb.append("';");

			//SQL文の実行

			PreparedStatement pStmt = conn.prepareStatement(sb.toString());

			ResultSet rs = pStmt.executeQuery();

			//社員情報をインスタンスに追加
			if (rs.next()) {
				String empName=rs.getString("emp_name");
				empbean.setEmpName(empName);
				String ruby=rs.getString("ruby");
				empbean.setRuby(ruby);
				String entryDate=rs.getString("entry_date");
				empbean.setEntryDate(entryDate);

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return empbean;
	}
}
