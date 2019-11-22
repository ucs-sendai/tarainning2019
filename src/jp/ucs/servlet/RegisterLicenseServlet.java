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

import jp.ucs.bean.EmployeeBean;
import jp.ucs.bean.LicenseBean;
import jp.ucs.constants.Constants;
import jp.ucs.constants.MessageConstants;
import jp.ucs.exception.HrsmUcsDBException;
import jp.ucs.logic.RegisterLicenseLogic;
import jp.ucs.logic.licenseLogic;

@WebServlet("/RegisterLicense")
public class RegisterLicenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterLicenseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// actionの値をリクエストパラメータから取得
		String action = request.getParameter("action");

		// sessionスコープからインスタンスを取得
		HttpSession session = request.getSession();
		EmployeeBean EmpInfo = (EmployeeBean) session.getAttribute("employee");

		// propertyIdとserialIdをEmpIdにする
		EmpInfo.setEmpId(EmpInfo.getPropertyId() + EmpInfo.getSerialId());

		// LicenseBean license = (LicenseBean) session.getAttribute("employee");

		// actionの値がnullの時の処理
		if (action == null) {

			// sessionスコープにインスタンスを保存
			session.setAttribute("EmpInfo", EmpInfo);

			// RegisterLicenseLogicのRegisterLicenseLogicを呼び出す
			RegisterLicenseLogic licenseLogic = new RegisterLicenseLogic();

			try {
				Map<String, String> licenseMap = licenseLogic.employeeFindPart(EmpInfo);

				// licenseMapをsessionスコープに入れる
				session.setAttribute("licenseMap", licenseMap);

				// registerLicenseにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_license);
				dispatcher.forward(request, response);

				// エラーが発生したらエラー画面に遷移
			} catch (HrsmUcsDBException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.db_error);
				dispatcher.forward(request, response);
			}
		}

		if (action.equals("yes")) {
			try {
				// sessionスコープから値を取り出す
				LicenseBean licenseBean = (LicenseBean) session.getAttribute("license");

				// licenseを引数にlogicクラスのregisterLicenseExecuteを呼び出す
				RegisterLicenseLogic LicenseLogic = new RegisterLicenseLogic();
				LicenseLogic.registerLicenseExecute(licenseBean);

				// license_finにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.license_fin);
				dispatcher.forward(request, response);

			} catch (HrsmUcsDBException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.db_error);
				dispatcher.forward(request, response);
			}
		}

		if (action.equals("no")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_license);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ArrayList<String> errorMsg = new ArrayList<String>();

			// jspから値を取得
			String licenseId = request.getParameter("license");
			String licenseDate = request.getParameter("licenseData");

			// employeeからIDと名前を取得
			HttpSession session = request.getSession();
			EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");

			String propertyId = employee.getPropertyId();
			String serialId = employee.getSerialId();
			String empName = employee.getEmpName();

			// 資格Idをキーにして資格名を取得する
			licenseLogic licenseMap = new licenseLogic();
			String licenseName = licenseMap.licenseExecute().get(licenseId);

			// licenseBean型のインスタンスを生成してスコープに入れる
			LicenseBean license = new LicenseBean(propertyId, serialId, empName, licenseId, licenseName, licenseDate);

			// RegisterLogicを生成
			RegisterLicenseLogic registerLogic = new RegisterLicenseLogic();

			// 入力値チェック
			errorMsg = registerLogic.checkLicense(license);

			// エラーがあればエラーメッセージを表示
			if (errorMsg.size() > 0) {
				request.setAttribute("message", errorMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_license);
				dispatcher.forward(request, response);

			} else {

				// HttpSession session = request.getSession();
				session.setAttribute("license", license);

				// 登録処理の実行

				boolean resultEmp = registerLogic.registerLicenseExecute(license);

				if (resultEmp) {

					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.license_fin);
					dispatcher.forward(request, response);

				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.register_license);
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