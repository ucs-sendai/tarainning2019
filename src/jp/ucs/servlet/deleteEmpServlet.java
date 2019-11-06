package jp.ucs.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.DeleteEmpLogic;
import jp.ucs.logic.DeptFindAllLogic;

/**
 * システム名：社員管理システム クラス名 ：deleteEmp 処理概要 ：社員削除のサーブレット プロジェクト名：HrsmUcs(社員削除画面) 作成者
 * ：高原 優 作成日付：2019/11/1(金)
 */

@WebServlet("/DeleteEmp")
public class deleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォワード先
		String forwardPath = null;

		// actionパラメーターをリクエストパラメータから取得
		String action = request.getParameter("action");

		HttpSession session = request.getSession();

		// action属性がnullの場合

		if (action == null) {

			// DeotFindAllLogicのdeptExecute()を呼び出す
			DeptFindAllLogic deptLogic = new DeptFindAllLogic();

			try {
				Map<String, String> dept = deptLogic.deptExecute();

				// deptMapをセッションスコープに保存
				session.setAttribute("deptMap", dept);

				// sessionスコープ内のsearchConditionを削除
				session.removeAttribute("searchCondition");

				// deleteEmpMain.jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.delete_emp_main);
				dispatcher.forward(request, response);

			} catch (Exception e) {

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.db_error);
				dispatcher.forward(request, response);
			}

		}

		// action属性がyesの時
		if (action.equals("yes")) {

			// sessionスコープからを取り出す

			EmployeeBean emp = (EmployeeBean) session.getAttribute("searchCondition");

			// searchConditionを引数としてBOのdeleteExecute()を呼び出す。

			try {
				DeleteEmpLogic deleteEmp = new DeleteEmpLogic();
				deleteEmp.deleteExecute(emp);
			}

			// HrmsUcsDBExceptionがスローされたらキャッチしてdbError.jspにフォワード

			catch (HrsmUcsDBException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.db_error);
				dispatcher.forward(request, response);
			}

			// 例外が出なければ.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.delete_emp_result);
			dispatcher.forward(request, response);
		}

		// アクション属性がnoの時
		if (action.equals("no")) {

			// deleteEmpMain.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.delete_emp_main);
			dispatcher.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// JSPから削除したい社員情報を取得
		request.setCharacterEncoding("UTF-8");
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String deptId = request.getParameter("deptId");

		String msg = "";

		// 取得したempIdをpropertyIdとserialIdに分ける
		String propertyId = empId.substring(0, 4);
		String serialId = empId.substring(4, 8);

		// deptIdをキーにしてdeptNameを取得する
		// String deptName = (map.get("deptId"));

		// 取得したdeptIdをBeanに入れていく
		DeptBean dept = new DeptBean(deptId, "");

		// EmployeeBean型のインスタンスを作成し、sessionスコープに登録
		EmployeeBean deleteEmp = new EmployeeBean(propertyId, serialId, empName, dept);

		// logicクラスのインスタンスを生成
		DeleteEmpLogic deleteLogic = new DeleteEmpLogic();

		// 入力項目の不備チェック
		ArrayList<String> errorMsg = new ArrayList<String>();
		errorMsg = deleteLogic.checkEmp(deleteEmp);

		// 入力項目にエラーが生じたらエラーメッセージを表示
		if (errorMsg.size() > 0) {

			// エラーメッセージをリクエストスコープに保存する
			request.setAttribute("message", errorMsg);

			// エラーメッセージを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.delete_emp_main);
			dispatcher.forward(request, response);

		} else {

			HttpSession session = request.getSession();
			session.setAttribute("deleteEmp", deleteEmp);

			// 削除処理を実行
			try {
				boolean resultEmp = deleteLogic.deleteExecute(deleteEmp);

				if (resultEmp) {

					// deleteEmpConfirm.jspにフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.delete_emp_confirm);
					dispatcher.forward(request, response);

				} else {

					// sessionスコープ内のdeleteEmpを削除
					session.removeAttribute("deleteEmp");

					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.delete_emp_main);
					dispatcher.forward(request, response);
				}

				// HrmsUcsDBExceptionがスローされたらキャッチしてdbError.jspにフォワード
			} catch (HrsmUcsDBException e) {
				msg = MessageConstants.DB_ERR01;
				request.setAttribute("errorMsg", MessageConstants.DB_ERR01);

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.error);
				dispatcher.forward(request, response);
			}
		}
	}
}