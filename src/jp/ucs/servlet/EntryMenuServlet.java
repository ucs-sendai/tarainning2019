package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.logic.LoginLogic;
import model.LoginLogicloginCheck;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	};

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String employeeId = request.getParameter("employeeId");
		String pass = request.getParameter("pass");

		String forwardPath = null;
		LoginLogic login = new LoginLogic();

		if(login.loginCheck(employeeId,pass)){
			//ログイン後に移動
			//ログインユーザーが管理者か一般かを判断
			if(employeeId.substring(0,4).equals("0000")){
				//管理者

			}else {
				//一般

			}

		} else {
			//エラーメッセージ
		}



		//ログインユーザーが管理者か一般かを判断
		if(employeeId.substring(0,4).equals("0000")){

			Login login = new Login(employeeId,pass);

			//スコープに登録
			if(logincheck.execute() == true){
				HttpSession session = request.getSession();
				session.setAttribute("employee",employee);

				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminMenu.jsp");
				dispatcher.forward(request,response);

			}
		}else if{
			//ログインユーザーを設定
			Employee employee = new Employee(empId,pass);

			//セッションスコープにログインユーザーを登録
			if(logincheck.execute() == true){
				HttpSession session = request.getSession();
				session.setAttribute("employee",employee);

				//フォワード先を指定
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/generalMenu.jsp");
				dispatcher.forward(request,response);

				}
			}

			}

}
