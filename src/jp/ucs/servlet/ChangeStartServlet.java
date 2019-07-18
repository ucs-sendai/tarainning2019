package jp.ucs.servlet;

import java.io.IOException;
import java.util.Map;

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
import jp.ucs.logic.DeptFindAllLogic;

/**
 * システム名：社員管理システム
 * クラス名  ：ChangeStartServlet
 * 処理概要  ：社員情報変更を行う。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付  ：2019年7月8日
 */

@WebServlet("/ChangeStart")
public class ChangeStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeStartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//初回動作時にセッションスコープを作成
		HttpSession session = request.getSession();

		//セッションスコープの中身を初期化

		session.removeAttribute("beforeEmp");
		session.removeAttribute("afterEmp");
		session.removeAttribute("errorMsgMap");
		session.removeAttribute("deptMap");

		//セッションスコープから値を取得
		EmployeeBean employee =(EmployeeBean)session.getAttribute("beforeEmp");

		//セッションスコープに登録
		session.setAttribute("beforeEmp", employee);

		//メソッド呼び出し
		DeptFindAllLogic deptFindAllLogic = new DeptFindAllLogic();

		//登録とエラー処理
		try{
			Map<String, String> deptMap = deptFindAllLogic.deptExecute();
			session.setAttribute("deptMap", deptMap);
		}catch (HrsmUcsDBException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());

			RequestDispatcher dispatcher =
					request.getRequestDispatcher(Constants.error);
			dispatcher.forward(request, response);
		}
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.changeEmp);
		dispatcher.forward(request, response);

	}


}
