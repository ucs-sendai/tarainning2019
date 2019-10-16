package jp.ucs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;

public class EmpListDAO extends BaseDAO{

	    public List<EmployeeBean>findAllEmp() throws HrsmUcsDBException{
	        List<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();

	        //データベースに接続
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

	            //SELECT文を準備
	            StringBuilder sb = new StringBuilder();
	            sb.append("select property_id, serial_id, emp_name, dept.dept_id, dept_name ");
	            sb.append("from employee join dept ");
	            sb.append("on employee.dept_id = dept.dept_id ");
	            sb.append("where not property_id = '0000'");
	            sb.append("order by property_id,serial_id;");

	            PreparedStatement pStmt = conn.prepareStatement(sb.toString());

	            //SELECT文を実行し、結果表を取得
	            ResultSet rs = pStmt.executeQuery();

	            //結果表に格納されたレコードの内容をEmployeeインスタンスに設定し、ArrayListインスタンスに追加
	            while(rs.next()){
	                String propertyId = rs.getString("property_id");
	                String serialId = rs.getString("serial_id");
	                int serialInt = Integer.parseInt(serialId.trim());
	                serialId = String.format("%04d", serialInt);
	                String empId = propertyId + serialId;
	                String empName = rs.getString("emp_Name");
	                String deptId = rs.getString("dept_id");
	                String deptName = rs.getString("dept_name");

	                DeptBean dept = new DeptBean(deptId, deptName);
	                EmployeeBean employee = new EmployeeBean(empId, empName, dept);
	                employeeList.add(employee);
	            }
	          //SQLExceptionが発生したら、catchしオリジナル例外を発生させる
	        }catch(SQLException e){
	            e.printStackTrace();
	            throw new HrsmUcsDBException(MessageConstants.DB_ERR01);
	        }

	        return employeeList;
	    }
	}