package jp.ucs.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import jp.ucs.bean.DeptBean;
import jp.ucs.bean.EmployeeBean;
import jp.ucs.constants.Constants;
import jp.ucs.constants.MessageConstants;
import jp.ucs.dao.EmpInsertDAO;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.DeptFindAllLogic;
import jp.ucs.logic.RegisterLogic;

@WebServlet("/RegisterEmp")
public class RegisterEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterEmpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String forwardPath = Constants.register_form;

		// action値がnull（メニューまたは登録完了画面から遷移した場合）
		if (action == null) {

			// 部門リストを取得してセッションスコープに保存

			try {

				DeptFindAllLogic dfal = new DeptFindAllLogic();
				Map<String, String> deptMap = dfal.deptExecute();
				session.setAttribute("deptMap", deptMap);

			} catch (HrsmUcsDBException e) {

				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);

			}

			// セッションスコープからregisterEmpを削除
			session.removeAttribute("registerEmp");

			// 登録フォーム画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

			// action値がno（登録確認画面で「いいえ」が押されて遷移した場合）
		} else if (action.equals("no")) {

			// 登録フォーム画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

			// action値がyes（登録確認画面で「はい」が押されて遷移した場合）
		} else if (action.equals("yes")) {

			// セッションスコープからregisterEmpを取り出す
			EmployeeBean registerEmp = (EmployeeBean) session.getAttribute("registerEmp");
			RegisterLogic regLog = new RegisterLogic();
			EmpInsertDAO empIn = new EmpInsertDAO();
			EmployeeBean empbean = new EmployeeBean();
			String empId = "";

			empId = empbean.getEmpId();

			try {

				regLog.registerExecute(registerEmp);

				// 社員IDをリクエストスコープに保存し登録完了画面にフォワード

				request.setAttribute("empId", empId);
				forwardPath = Constants.register_fin;
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);

			} catch (HrsmUcsDBException e) {

				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				forwardPath = Constants.error;
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);

			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String forwardPath = Constants.reg_confirm;

		// 変数の宣言、パラメータを取得
		String empId = null;
		String empName = request.getParameter("empName");
		String ruby = request.getParameter("ruby");
		String deptId = request.getParameter("dept");
		String pass = request.getParameter("pass");
		String entryDateStr = request.getParameter("entryDate");
		String entryDate = Normalizer.normalize(entryDateStr, Normalizer.Form.NFKC);
		DeptBean dept = new DeptBean();

		// エラーメッセージリスト
		List<String> errorMsgList = new ArrayList<>();

		// エラーメッセージを初期化
		String errorMsg1 = "";
		String errorMsg2 = "";
		String errorMsg3 = "";
		String errorMsg4 = "";
		String errorMsg5 = "";

		// 名前の入力チェック

		byte[] bytes = empName.getBytes();
		if (empName == null || empName.length() == 0) {

			errorMsg1 = MessageConstants.REGEMP_ERR01;
			errorMsgList.add(errorMsg1);
			request.setAttribute("errorMsgList", errorMsgList);
			empName = null;
		}
		if (empName.length() != bytes.length) {
			// 全角で30字以上か
			if (empName.length() > 30) {
				errorMsg1 = MessageConstants.REGEMP_ERR06;
				errorMsgList.add(errorMsg1);
				request.setAttribute("errorMsgList", errorMsgList);
				empName = null;
			}
			// 半角で60字以上か
			if (empName.length() > 60) {
				errorMsg1 = MessageConstants.REGEMP_ERR06;
				errorMsgList.add(errorMsg1);
				request.setAttribute("errorMsgList", errorMsgList);
				empName = null;
			}

			// ふりがなの入力チェック
			if (ruby == null || ruby.length() == 0) {

				errorMsg2 = MessageConstants.REGEMP_ERR02;
				errorMsgList.add(errorMsg2);
				request.setAttribute("errorMsgList", errorMsgList);

			}
			if (!ruby.matches("^[\\u3040-\\u309F]+$")) {

				errorMsg2 = MessageConstants.REGEMP_ERR10;
				errorMsgList.add(errorMsg2);
				request.setAttribute("errorMsgList", errorMsgList);

			}
			if (ruby.length() > 80) {

				errorMsg2 = MessageConstants.REGEMP_ERR07;
				errorMsgList.add(errorMsg2);
				request.setAttribute("errorMsgList", errorMsgList);
				ruby = null;

			}

			// 部門の入力チェック
			if (deptId == null || deptId.length() == 0) {

				errorMsg3 = MessageConstants.REGEMP_ERR03;
				errorMsgList.add(errorMsg3);
				request.setAttribute("errorMsgList", errorMsgList);

				// 部門が選択されていればDept型インスタンスを生成
			} else {

				Map<String, String> deptMap = (Map<String, String>) session.getAttribute("deptMap");

			}

			// パスワードの入力チェック
			if (pass == null || pass.length() == 0) {

				errorMsg4 = MessageConstants.REGEMP_ERR04;
				errorMsgList.add(errorMsg4);
				request.setAttribute("errorMsgList", errorMsgList);

			} else if (pass.length() < 8 || pass.length() > 16) {

				errorMsg4 = MessageConstants.REGEMP_ERR08;
				errorMsgList.add(errorMsg4);
				request.setAttribute("errorMsgList", errorMsgList);
				pass = null;

			}

			// 入社年月日の入力チェック
			if (entryDate == null || entryDate.length() == 0) {

				errorMsg5 = MessageConstants.REGEMP_ERR05;
				errorMsgList.add(errorMsg5);
				request.setAttribute("errorMsgList", errorMsgList);

			} else if (entryDate.length() != 10) {

				errorMsg5 = MessageConstants.REGEMP_ERR09;
				errorMsgList.add(errorMsg5);
				request.setAttribute("errorMsgList", errorMsgList);
				entryDate = null;

			} else {

				try {

					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					df.setLenient(false);
					df.parse(entryDate);

					// フォーマット誤り
				} catch (ParseException | java.text.ParseException e) {

					errorMsg5 = MessageConstants.REGEMP_ERR11;
					errorMsgList.add(errorMsg5);
					request.setAttribute("errorMsg5", errorMsg5);

				}

				// Employee型のインスタンスを生成、セッションスコープに保存
				EmployeeBean registerEmp = new EmployeeBean(empId, empName, ruby, pass, entryDate, dept);
				session.setAttribute("registerEmp", registerEmp);

				if (errorMsgList.isEmpty()) {

					RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
					dispatcher.forward(request, response);

				} else {

					forwardPath = Constants.reg_confirm;
					RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
					dispatcher.forward(request, response);

				}

			}
		}
	}
}
