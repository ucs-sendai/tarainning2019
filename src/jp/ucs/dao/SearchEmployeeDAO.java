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
import jp.ucs.bean.SearchConditionBean;
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;


	public class SearchEmployeeDAO extends BaseDAO {

	    public List<EmployeeBean> searchEmp(SearchConditionBean searchCondition) throws HrsmUcsDBException {
	        List<EmployeeBean> searchResultList = new ArrayList<>();
	        // データベースへ接続
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_ID, PWD)) {

	        	StringBuilder sb = new StringBuilder();

	        	sb.append("Select distinct concat(employee.property_id,employee.serial_id) as emp_id ,emp_name, ruby ,pass ,entry_date,dept.dept_id, dept.dept_name");
	    	    sb.append("from employee left join dept ");
	    	    sb.append("on employee.dept_id = dept.dept_id ");
	    	    sb.append("left join have_license");
	    	    sb.append("on employee.property_id = have_license.property_id  and employee.serial_id = have_license.serial_id ");
	    	    sb.append("left join license on have_license.license_id = license.license_id WHERE 1=1;");
	            // 検索条件の有無に応じて
	            if (searchCondition.getEmpId() != "") {
	                sb.append ("and concat(employee.property_id,employee.serial_id) like'" );
	                sb.append(searchCondition.getEmpId() + "%'");
	            }
	            if (searchCondition.getEmpName() != "") {
	            	sb.append("and emp_name LIKE'%" );
;
;	            }
	            if (searchCondition.getDept().getDeptName() != null) {
	            	sb.append("and dept_name = '" );

	            }
	            if (searchCondition.getLicense().getLicenseName() != null) {
	                sb.append("and license_name =  searchCondition.getLicense().getLicenseName();");
	            }
	            sb.append("order by concat(employee.property_id,employee.serial_id)");
	            PreparedStatement pstmt = conn.prepareStatement(sb.toString());
	            ResultSet rs = pstmt.executeQuery();

	            // 検索結果をemployeeに格納しリストに追加
	            while (rs.next()) {
	                String empId = rs.getString("emp_id");
	                String empName = rs.getString("emp_name");
	                String ruby = rs.getString("ruby");
	                String pass = rs.getString("pass");
	                String entryDate = rs.getString("entry_date");
	                String deptId = rs.getString("dept_id");
	                String deptName = rs.getString("dept_name");
	                DeptBean dept = new DeptBean(deptId, deptName);
	                EmployeeBean employee = new EmployeeBean(empId, empName, ruby, pass, entryDate, dept);
	                searchResultList.add(employee);

	            }
	            // DBエラーの場合
	        } catch (SQLException e) {
	            throw new HrsmUcsDBException(MessageConstants.DB_ERR01);
	        }
	        return searchResultList;
	    }
	}


