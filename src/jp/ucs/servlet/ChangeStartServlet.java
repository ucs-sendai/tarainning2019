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
import jp.ucs.logic.DeptFindAllLogic;

/**
 * Servlet implementation class ChangeStartServlet
 */
@WebServlet("/ChangeStart")
public class ChangeStartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeStartServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		// セッションスコープからインスタンスを削除
		// request.removeAttribute("employee");

		// sessionインスタンスの取得
		HttpSession session = request.getSession();

		// セッションスコープからインスタンスを取得
		EmployeeBean beforeEmp = (EmployeeBean) session.getAttribute("employee");

		// request.setCharacterEncoding("UTF-8");
		// String pass = request.getParameter("pass");

		// beforeEmp.setPass(pass);

		// propertyIdとserialIdをempIdにする
		beforeEmp.setEmpId(beforeEmp.getPropertyId() + beforeEmp.getSerialId());

		if (beforeEmp != null) {

			// セッションスコープにインスタンスを保存
			session.setAttribute("beforeEmp", beforeEmp);

			try {
				// DeptFindAllLogicのdeptExecute()を呼ぶ
				DeptFindAllLogic deptFind = new DeptFindAllLogic();
				session.setAttribute("deptMap", deptFind.deptExecute());

				// changeEmp.jspへフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.change_emp);
				dispatcher.forward(request, response);

				// エラーが出たらエラー画面に遷移

			} catch (HrsmUcsDBException e) {
				request.setAttribute("errorMsg", MessageConstants.DB_ERR01);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.error);
				dispatcher.forward(request, response);
			}
		}
	}
}
