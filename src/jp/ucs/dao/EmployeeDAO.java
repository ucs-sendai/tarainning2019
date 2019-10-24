package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ucs.bean.DeptBean;
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

public class EmployeeDAO extends BaseDAO {

	public List<EmployeeBean> employeeFindPart(){
		List<EmployeeBean> empList = new ArrayList<>();
		try (Connection conn = getConnection()) {
			StringBuilder sb = new StringBuilder();
			sb.append("select * ");
			sb.append("from employee inner join dept ");
			sb.append("on employee.dept_id = dept.dept_id ");
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();

			//DBから情報を取り出し、インスタンスに格納
			while (rs.next()) {
				String propertyId = rs.getString("property_id");
				String serialId = rs.getString("serial_id");
				String empName = rs.getString("emp_name");
				String ruby = rs.getString("ruby");
				String pass = rs.getString("pass");
				String entryDate = rs.getString("entry_Date");
				String deptId = rs.getString("dept_id");
				String deptName = rs.getString("dept_name");
				DeptBean dept = new DeptBean(deptId,deptName);
				EmployeeBean employeeBean = new EmployeeBean(propertyId,serialId,empName,ruby,pass,entryDate,dept);
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
		try (Connection conn = getConnection()) {
			//Connection conn = getConnection();

			//SQL文の準備
			StringBuilder sb = new StringBuilder();
			sb.append("select * ");
			sb.append("from employee inner join dept ");
			sb.append("on employee.dept_id = dept.dept_id ");
			sb.append("where ");
			sb.append("property_id = '");
			sb.append(employeeBean.getPropertyId());
			sb.append("' " );
			sb.append("and " );
			sb.append("serial_id = '");
			sb.append(employeeBean.getSerialId());
			sb.append("' " );
			sb.append("and " );
			sb.append("pass = '");
			sb.append(employeeBean.getPass());
			sb.append("' ;" );

			//SQL文の実行
			PreparedStatement pStmt = conn.prepareStatement(sb.toString());
			ResultSet rs = pStmt.executeQuery();

			//追加するためのインスタンスを生成
			EmployeeBean EmpInfo = new EmployeeBean();

			//社員情報をインスタンスに追加
			while(rs.next()) {

				String propertyId = rs.getString("property_id");
				EmpInfo.setPropertyId(propertyId);
				String serialId = rs.getString("serial_id");
				EmpInfo.setSerialId(serialId);
				String empName=rs.getString("emp_name");
				EmpInfo.setEmpName(empName);
				String ruby=rs.getString("ruby");
				EmpInfo.setRuby(ruby);
				String entryDate=rs.getString("entry_date");
				EmpInfo.setEntryDate(entryDate);
				String deptId = rs.getString("dept_id");
				String deptName = rs.getString("dept_name");
				DeptBean deptBean = new DeptBean(deptId,deptName);
				EmpInfo.setDept(deptBean);
			}

			return EmpInfo;

		}catch(SQLException e){
			throw new HrsmUcsDBException();
		}
	}
}