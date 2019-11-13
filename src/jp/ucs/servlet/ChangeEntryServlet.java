package jp.ucs.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
import jp.ucs.logic.ChangeLogic;
import jp.ucs.logic.DeptFindAllLogic;

/**
 * Servlet implementation class ChangeStartServlet
 */
@WebServlet("/Entry")
public class ChangeEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeEntryServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// actionパラメータの取得
		String action = request.getParameter("action");

		// sessionインスタンスの取得
		HttpSession session = request.getSession();

		// actionパラメータがnoの場合
		// if (action.equals("no")) {

		// changeEmp.jspにフォワードする
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher(Constants.change_emp);
		// dispatcher.forward(request, response);
		// }

		// actionパラメータがyesの場合
		if (action.equals("yes")) {

			try {

				// sessionスコープからインスタンスを取得
				EmployeeBean afteremp = (EmployeeBean) session.getAttribute("afterEmp");

				// afterEmpを引数にChangeLogicのempUpdateを呼ぶ
				ChangeLogic change = new ChangeLogic();
				change.empUpdate(afteremp);

				// セッションスコープにあるemployeeを取得
				// EmployeeBean emp = (EmployeeBean)
				// session.getAttribute("employee");

				// if (afteremp.getEmpId() == emp.getEmpId()) {
				// session.setAttribute("employee", afteremp);

				// changeComp.jspへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.change_comp);
				dispatcher.forward(request, response);
				// }

			} catch (HrsmUcsDBException e) {

				request.setAttribute("errorMsg", MessageConstants.DB_ERR01);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.error);
				dispatcher.forward(request, response);

			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// jspから社員情報を取得する
			request.setCharacterEncoding("UTF-8");
			// String empId = request.getParameter("empId");
			String empName = request.getParameter("name");
			String ruby = request.getParameter("ruby");
			String deptId = request.getParameter("dept");
			String pass = request.getParameter("pass");
			String entryDate = request.getParameter("date");

			// DeptFindAllLogicのインスタンスを生成
			DeptFindAllLogic deptLogic = new DeptFindAllLogic();

			// beforeEmpからempIdを取得
			HttpSession session = request.getSession();
			EmployeeBean before = (EmployeeBean) session.getAttribute("beforeEmp");
			String empId = before.getEmpId();

			// deptIdをキーにdeptNameを取得する
			String deptName = deptLogic.deptExecute().get(deptId);

			// EmployeeBean型のインスタンスを作成し、sessionスコープに登録
			DeptBean dept = new DeptBean(deptId, deptName);
			EmployeeBean after = new EmployeeBean(empId, empName, ruby, dept, pass, entryDate);

			// sessionインスタンスの取得
			// HttpSession session = request.getSession();

			// beforeEmpのインスタンスを生成
			// EmployeeBean before = (EmployeeBean)
			// session.getAttribute("beforeEmp");

			// afterEmpのインスタンスの生成
			// EmployeeBean after = (EmployeeBean)
			// session.getAttribute("afterEmp");

			// logicクラスのインスタンスを生成
			ChangeLogic change = new ChangeLogic();

			// 入力項目チェック
			// ArrayList<String> errorMsgBefore = new ArrayList<String>();
			ArrayList<String> errorMsgAfter = new ArrayList<String>();

			// errorMsgBefore = change.checkEmp(before);
			errorMsgAfter = change.checkEmp(after);

			// 入力項目にエラーが出てきたらメッセージを表示
			// if (errorMsgBefore.size() > 0 || errorMsgAfter.size() > 0) {

			if (errorMsgAfter.size() > 0) {
				// エラーメッセージをリクエストスコープに保存
				// request.setAttribute("message", errorMsgBefore);
				request.setAttribute("message", errorMsgAfter);

				// エラーメッセージを表示
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.change_emp);
				dispatcher.forward(request, response);
			}

			else {
				// 入力項目にエラーがなければ、changeComp.jspにフォワード
				request.setAttribute("afterEmp", after);

				boolean result = change.empUpdate(after);

				if (result) {

					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.change_comp);
					dispatcher.forward(request, response);
				}
			}
		} catch (HrsmUcsDBException e) {

			request.setAttribute("errorMsg", MessageConstants.DB_ERR01);
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.error);
			dispatcher.forward(request, response);
		}
	}
}
