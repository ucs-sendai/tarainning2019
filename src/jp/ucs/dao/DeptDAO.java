package jp.ucs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jp.ucs.exception.HrsmUcsDBException;
/**
 * システム名：社員管理システム
 * クラス名:   DeptDAO
 * 処理概要  : 部門を格納する。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年7月8日
 */
public class DeptDAO extends BaseDAO  {

        	 public Map<String, String> deptFindAll() throws HrsmUcsDBException {

        	        Map<String, String> dept = new HashMap<>();


        	        try (Connection conn = getConnection()) {

        	        	StringBuilder sb = new StringBuilder();
        	            sb.append("SELECT dept_id,dept_name  FROM DEPT");
        	            PreparedStatement pStmt = conn.prepareStatement(sb.toString());


        	            ResultSet rs = pStmt.executeQuery();

        	            while (rs.next()) {
        	                String deptId = rs.getString("dept_id");
        	                String deptName = rs.getString("dept_name");
        	                dept.put(deptId, deptName);
        	            }

        	        } catch (SQLException e) {
        	            e.printStackTrace();
        	            throw new HrsmUcsDBException();

        	        }
        	        return dept;
        	    }
        	}














