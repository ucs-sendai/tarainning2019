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

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.Constants;
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.DeptFindAllLogic;
import jp.ucs.logic.RegisterLogic;

/**
 * システム名：社員管理システム クラス名 ：RegisterEmp 処理概要 ：社員登録のサーブレット プロジェクト名：HrsmUcs(社員登録画面)
 * 作成者 ：高原 優 作成日付：2019/10/24(木)
 */

@WebServlet("/RegisterEmp")
public class RegisterEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// フォワード先
		String forwardPath = null;

		// actionの値をリクエストパラメータから取得
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

				// sessionスコープ内のregisterEmpを削除
				session.removeAttribute("registerEmp");

				// registerForm.jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_form);
				dispatcher.forward(request, response);

			} catch (HrsmUcsDBException e) {
				e.printStackTrace();
			}

		}

		// action属性がyesの場合
		if (action.equals("yes")) {

			// セッションスコープからregisterEmpを取り出す

			EmployeeBean emp = (EmployeeBean) session.getAttribute("registerEmp");

			// registerEmpを引数としてBOのregisterExecute()を呼び出す。

			try {
				RegisterLogic regLogic = new RegisterLogic();
				regLogic.registerExecute(emp);

				// HrmsUcsDBExceptionがスローされたらキャッチしてdbError.jspにフォワード
			} catch (HrsmUcsDBException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_form);
				dispatcher.forward(request, response);
			}

			// 例外が出なければregisterFin.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_form);
			dispatcher.forward(request, response);

		}

		if (action.equals("no")) {
			// registerForm.jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_form);
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// JSPから登録したい社員情報を取得
		request.setCharacterEncoding("UTF-8");
		String empName = request.getParameter("empName");
		String ruby = request.getParameter("ruby");
		String deptId = request.getParameter("dept");
		String pass = request.getParameter("pass");
		String entryDate = request.getParameter("entryDate");

		// メッセージの初期化
		String msg = "";
		String msg1 = "";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";
		String msg5 = "";
		String path = "";

		// 入力チェック(空欄と文字数の制限以内の確認)
		if (empName.equals("") || empName.length() > 30) {
			msg1 = MessageConstants.REGEMP_ERR01;
			request.setAttribute("errorMsg1", msg1);
		}

		if (ruby.equals("") || ruby.length() > 40) {
			msg2 = MessageConstants.REGEMP_ERR02;
			request.setAttribute("errorMsg2", msg2);
		}

		if (deptId.equals("null")) {
			msg3 = MessageConstants.REGEMP_ERR03;
			request.setAttribute("errorMsg3", msg3);
		}

		if (pass.equals("") || pass.length() > 16) {
			msg4 = MessageConstants.REGEMP_ERR04;
			request.setAttribute("errorMsg4", msg4);
		}

		if (entryDate.equals("") || entryDate.length() != 10) {
			msg5 = MessageConstants.REGEMP_ERR05;
			request.setAttribute("errorMsg5", msg5);
		}

		// 入力項目に不備が見つかればエラーを表示
		if (msg1 != null || msg2 != null || msg3 != null || msg4 != null || msg5 != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_form);
			dispatcher.forward(request, response);

		}

		// EmployeeBean型のインスタンスを作成し、sessionスコープに登録
		DeptBean dept = new DeptBean(deptId, "");

		EmployeeBean registerEmp = new EmployeeBean(empName, ruby, dept, pass, entryDate);

		HttpSession session = request.getSession();
		session.setAttribute("registerEmp", registerEmp);

		// logicクラスのインスタンスを生成
		RegisterLogic logic = new RegisterLogic();

		// 登録処理を実行
		try {
			boolean result = logic.registerExecute(registerEmp);

			if (true && msg1 == null || msg2 == null || msg3 == null || msg4 == null || msg5 == null) {

				// registerFin.jspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_fin);
				dispatcher.forward(request, response);

			}
			// DBエラーが生じた場合、エラー画面にフォワード

		} catch (HrsmUcsDBException e) {
			msg = MessageConstants.DB_ERR01;
			request.setAttribute("errorMsg", msg);
			path = Constants.error;

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.error);
			dispatcher.forward(request, response);

		}
	}
}