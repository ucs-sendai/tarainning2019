package jp.ucs.logic;

import java.util.List;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.dao.EmpListDAO;
import jp.ucs.exception.HrsmUcsDBException;

public class EmpListLogic {
	/**
	 * 処理概要：社員リストの取得をDAOに移譲する
	 * @return
	 * @throws HrsmUcsDBException
	 */
	    public List<EmployeeBean> execute() throws HrsmUcsDBException{
	        EmpListDAO empListDAO = new EmpListDAO();
	        List<EmployeeBean> employeeList = empListDAO.findAllEmp();

	        return employeeList;
	    }
	}

