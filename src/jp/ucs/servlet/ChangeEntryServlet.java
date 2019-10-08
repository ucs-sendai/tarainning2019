package jp.ucs.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.Constants;
import jp.ucs.logic.ChangeLogic;

/**
 * システム名：社員管理システム
 * クラス名  ：ChangeEntry
 * 処理概要  ：社員情報を変更する。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年7月8日
 */

@WebServlet("/ChangeEntry")
public class ChangeEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ChangeEntryServlet() {
		super();

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		String forwardPath = Constants.changeCheck;

		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String ruby = request.getParameter("ruby");
		String pass = request.getParameter("pass");
		String entryDate = request.getParameter("entryDate");
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		DeptBean dept = new DeptBean(deptId , deptName);

		//セッションスコープから値を取得
		HttpSession session = request.getSession();
		EmployeeBean beforeEmp = (EmployeeBean) session.getAttribute("beforeEmp");
		Map<String, String> deptMap = (Map<String, String>) session.getAttribute("deptMap");

		//変更後の情報をもつafterEmpインスタンスを生成
		EmployeeBean afterEmp = new EmployeeBean(empId,empName,ruby,pass,entryDate,dept);

		//エラーメッセージを生成
		ChangeLogic changeLogic = new ChangeLogic();
		Map<String, String> errorMsgMap = new HashMap<>();

		//メソッド呼び出し
		changeLogic.errorCheck(beforeEmp, afterEmp);

		//不備の有無を判断
		if(errorMsgMap .equals(null)){
			forwardPath = Constants.changeCheck;
		}else{
			forwardPath = Constants.changeEmp;
		}

		//セッションスコープに保存
		session.setAttribute("afterEmp", afterEmp);
		session.setAttribute("errorMsgMap",errorMsgMap);

		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.changeComp);
		dispatcher.forward(request, response);


	}

}
