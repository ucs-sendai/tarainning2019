package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.Constants;
import jp.ucs.constants.MessageConstants;
import jp.ucs.logic.LoginLogic;

/**
 * システム名：社員管理システム
 * クラス名  ：EntryMenu
 * 処理概要  ：ログイン画面をコントロールする
 * プロジェクト名：HrsmUcs(ログイン画面)
 * 作成者    ：高原 優
 * 作成日付：2019/07/08(月)
 */

@WebServlet("/EntryMenu")
public class EntryMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EntryMenuServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(Constants.admin);
		dispatcher.forward(request,response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//社員IDとパスワードを取得
		request.setCharacterEncoding("UTF-8");
		String empId = request.getParameter("emp_id");
		String pass = request.getParameter("pass");
		String msg = "";
		String forwardPath = null;

		//入力項目の不備を処理
		if (empId.equals("") || pass.equals("") || empId.length() != 8) {
			msg = MessageConstants.LOGIN_ERR;
			request.setAttribute("errorMsg",msg);
			forwardPath = Constants.login;
		}else{
			EmployeeBean employeeBean = new EmployeeBean(empId, pass);
			LoginLogic loginLogic = new LoginLogic();

			//ログイン処理
			//成功したらスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("employee",employeeBean);

			//管理者か一般社員か判断する
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(Constants.admin);
			dispatcher.forward(request,response);
		}
	}
}