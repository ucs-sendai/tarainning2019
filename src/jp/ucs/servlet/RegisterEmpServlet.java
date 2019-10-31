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

	public List<String> checkInputData(EmployeeBean empbean) {

		// エラーメッセージリスト
		List<String> errorMsgList = new ArrayList<>();

		// 名前の入力チェック

		byte[] bytes = empbean.getEmpName().getBytes();
		if (empbean.getEmpName() == null) {

			errorMsgList.add(MessageConstants.REGEMP_ERR01);

		}
		if (empbean.getEmpName().length() != bytes.length) {

			// 60字以上か
			if (empbean.getEmpName().length() != bytes.length || empbean.getEmpName().length() > 60) {
				errorMsgList.add(MessageConstants.REGEMP_ERR06);
			}

			// ふりがなの入力チェック
			if (empbean.getRuby() == null || empbean.getRuby().length() == 0) {

				errorMsgList.add(MessageConstants.REGEMP_ERR02);

			}
			if (!empbean.getRuby().matches("^[\\u3040-\\u309F]+$")) {

				errorMsgList.add(MessageConstants.REGEMP_ERR10);

			}
			if (empbean.getRuby().length() > 80) {

				errorMsgList.add(MessageConstants.REGEMP_ERR07);
			}

			// 部門の入力チェック
			if (empbean.getDept().getDeptId() == null || empbean.getDept().getDeptId().length() == 0) {

				errorMsgList.add(MessageConstants.REGEMP_ERR03);

				// パスワードの入力チェック
				if (empbean.getPass() == null || empbean.getPass().length() == 0) {

					errorMsgList.add(MessageConstants.REGEMP_ERR04);

				} else if (empbean.getPass().length() < 8 || empbean.getPass().length() > 16) {

					errorMsgList.add(MessageConstants.REGEMP_ERR08);

				}

				// 入社年月日の入力チェック
				if (empbean.getEntryDate() == null || empbean.getEntryDate().length() == 0) {

					errorMsgList.add(MessageConstants.REGEMP_ERR05);

				}
				if (empbean.getEntryDate().length() != 10) {

					errorMsgList.add(MessageConstants.REGEMP_ERR09);

				} else {

				}
				try {

					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					df.setLenient(false);
					df.parse(empbean.getEntryDate());

					// フォーマット誤り
				} catch (ParseException | java.text.ParseException e) {

					errorMsgList.add(MessageConstants.REGEMP_ERR11);

				}
			}
		}
		return errorMsgList;
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

		// エラーメッセージリスト
		List<String> errorMsgList = new ArrayList<>();

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

		// Employee型のインスタンスを生成、セッションスコープに保存
		EmployeeBean registerEmp = new EmployeeBean(empId, empName, ruby, pass, entryDate, dept);
		session.setAttribute("registerEmp", registerEmp);

		RegisterLogic registerLogic = new RegisterLogic();
		errorMsgList = registerLogic.checkInputData(registerEmp);

		if (errorMsgList.isEmpty()) {

			forwardPath = Constants.reg_confirm;
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

		} else {

			forwardPath = Constants.register_form;
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);

		}

	}

}
