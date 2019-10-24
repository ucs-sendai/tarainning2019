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
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.LoginLogic;

/**
 * システム名：社員管理システム
 * クラス名  ：EntryMenu
 * 処理概要  ：ログイン処理
 * プロジェクト名：HrsmUcs(ログイン画面)
 * 作成者    ：高原 優
 * 作成日付：2019/07/08(月)
 */

@WebServlet("/EntryMenu")
public class EntryMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//topリンクから管理者か一般のメニューに戻る
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");

		if(employee.getPropertyId().equals("0000")){
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(Constants.admin);
			dispatcher.forward(request,response);
		}else{
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(Constants.general);
			dispatcher.forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//JSPからIDとパスワードを受け取る
		request.setCharacterEncoding("UTF-8");
		String empId = request.getParameter("emp_id");
		String pass = request.getParameter("pass");

		String msg = "";
		String path = "";

		if (empId.equals("") || pass.equals("") || empId.length() != 8) {
			msg = MessageConstants.LOGIN_ERR;
			request.setAttribute("errorMsg",msg);
			path = Constants.login;

		}

		String propertyId = empId.substring(0,4);
		String serialId = empId.substring(4,8);

		//EmployeeBean型のインスタンスを作成し、それを引数にLoginLogicを実行(BO)
		EmployeeBean employee = new EmployeeBean(propertyId,serialId,pass);

		//Logicクラスのインスタンスの生成
		LoginLogic loginLogic = new LoginLogic();

		try{
			boolean result = loginLogic.loginCheck(employee);

			//ログイン認証が成功したしたとき、sessionスコープに社員情報を加える
			if(result){
				HttpSession session = request.getSession();
				session.setAttribute("employee", employee);
				//propertyが"0000"の場合、管理者メニューにフォワード
				if(propertyId.equals("0000")){
					path = Constants.admin;

					RequestDispatcher dispatcher =
							request.getRequestDispatcher(path);
					dispatcher.forward(request,response);
				}

				//resultがtrueでpropertyIdが"0000"でない場合、一般メニューにフォワード
			}else if( !(propertyId).equals("0000")){
				path = Constants.general;

				RequestDispatcher dispatcher =
						request.getRequestDispatcher(path);
				dispatcher.forward(request,response);

				//ログイン認証が成功しなかった場合、login.jspにフォワード

			}else{
				msg = MessageConstants.LOGIN_ERR;
				request.setAttribute("errorMsg",msg);
				path = Constants.login;

				RequestDispatcher dispatcher =
						request.getRequestDispatcher(path);
				dispatcher.forward(request,response);
			}

			//DBエラーが生じた場合、エラー画面にフォワード
		} catch(HrsmUcsDBException e) {
			msg = MessageConstants.DB_ERR01;
			request.setAttribute("errorMsg",msg);
			path =Constants.error;

			RequestDispatcher dispatcher =
					request.getRequestDispatcher(path);
			dispatcher.forward(request,response);
		}

	}
}