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
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.ChangeLogic;

/**
 * システム名：社員管理システム
 * クラス名  ：ChangeCompServlet
 * 処理概要  ：社員情報変更を行う。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付　：2019年7月8日
 */

@WebServlet("/ChangeComp")
public class ChangeCompServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeCompServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try{
			String forwardPath = null;

			//アクションパラメーターを取得
			String action = request.getParameter("action");

			//アクションパラメータで判断
			if(action.equals("yes")){
				HttpSession session = request.getSession();
				EmployeeBean employee = (EmployeeBean)session.getAttribute("afterEmp");
				ChangeLogic changeLogic = new ChangeLogic();
				changeLogic.execute(employee);
				session.setAttribute("afterEmp", employee);
				forwardPath = Constants.changeComp;
			}else{
				forwardPath = Constants.changeEmp;
			}

			//エラー処理
		}catch(HrsmUcsDBException hrex){
			hrex.printStackTrace();
			request.setAttribute("errorMsg", hrex);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(Constants.error);
			dispatcher.forward(request, response);
		}




	}
}
