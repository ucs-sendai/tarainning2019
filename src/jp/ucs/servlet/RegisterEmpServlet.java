package jp.ucs.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

/**
 * システム名：社員管理システム
 * クラス名  ：ResisterEmp
 * 処理概要   :社員の登録処理をする。
 * プロジェクト名：HrsmUcs
 * 作成者    ：小西香菜子
 * 作成日付：2019年9月30日
 */

@WebServlet("/RegisterEmp")
public class RegisterEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.registerForm);
		dispatcher.forward(request, response);

}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String forwardPath = Constants.regConfirm;

		// セッションスコープからregisterEmpを削除
			session.removeAttribute("registerEmp");


		// 変数の宣言、パラメータを取得
		String empId = null;
		String empName = request.getParameter("empName");
		String ruby = request.getParameter("ruby");
		String pass = request.getParameter("pass");
		String entryDateStr = request.getParameter("entryDate");
		String entryDate = Normalizer.normalize(entryDateStr, Normalizer.Form.NFKC);
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		DeptBean dept = new DeptBean(deptId , deptName);

		// エラーメッセージの変数の宣言
		String errorMsg1 = null;
		String errorMsg2 = null;
		String errorMsg3 = null;
		String errorMsg4 = null;
		String errorMsg5 = null;

		// 名前の入力チェック
		int len = empName.length();
		byte[] bytes = empName.getBytes();
		if (empName == null || empName.length() == 0) {

			errorMsg1 = MessageConstants.REGEMP_ERR01;
			request.setAttribute("errorMsg1", errorMsg1);

		} else if ( len != bytes.length ) {
			//全角で30字を上回っているか
			if (empName.length() > 30) {
				errorMsg1 = MessageConstants.REGEMP_ERR06;
				request.setAttribute("errorMsg1", errorMsg1);
				empName = null;
			}
		} else {
			//半角で60字を上回っているか
			if (empName.length() > 60) {
				errorMsg1 = MessageConstants.REGEMP_ERR06;
				request.setAttribute("errorMsg1", errorMsg1);
				empName = null;
			}



		// ふりがなの入力チェック
				}if (ruby == null || ruby.length() == 0) {

			errorMsg2 = MessageConstants.REGEMP_ERR02;
			request.setAttribute("errorMsg2", errorMsg2);

		} else if (!ruby.matches("^[\\u3040-\\u309F]+$")) {

			errorMsg2 = MessageConstants.REGEMP_ERR10;
			request.setAttribute("errorMsg2", errorMsg2);

		} else if (ruby.length() > 80) {

			errorMsg2 = MessageConstants.REGEMP_ERR07;
			request.setAttribute("errorMsg2", errorMsg2);
			ruby = null;


			// パスワードの入力チェック
		}if (pass == null || pass.length() == 0) {

			errorMsg4 = MessageConstants.REGEMP_ERR04;
			request.setAttribute("errorMsg4", errorMsg4);

		} else if (pass.length() < 8 || pass.length() > 16) {

			errorMsg4 = MessageConstants.REGEMP_ERR08;
			request.setAttribute("errorMsg4", errorMsg4);
			pass = null;

		}


			// 入社年月日の入力チェック
			if (entryDate == null || entryDate.length() == 0) {

				errorMsg5 = MessageConstants.REGEMP_ERR05;
				request.setAttribute("errorMsg5", errorMsg5);

			} else if (entryDate.length() != 10) {

				errorMsg5 = MessageConstants.REGEMP_ERR09;
				request.setAttribute("errorMsg5", errorMsg5);
				entryDate = null;

			} else {

				try {

					DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
					df.setLenient(false);
					df.parse(entryDate);

					//フォーマット誤り
				} catch(ParseException e) {

					errorMsg5 = MessageConstants.REGEMP_ERR11;
					request.setAttribute("errorMsg5", errorMsg5);

				}
			}


			// EmployeeBean型のインスタンスを生成、セッションスコープに保存
			EmployeeBean registerEmp = new EmployeeBean(empId,empName,ruby,pass,entryDate,dept);
			session.setAttribute("registerEmp",registerEmp);

			if (errorMsg1 == null && errorMsg2 == null && errorMsg3 == null
					&& errorMsg4 == null && errorMsg5 == null) {

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.registerFin);
				dispatcher.forward(request, response);

			} else {

				forwardPath =Constants.registerForm;
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
				dispatcher.forward(request, response);



			}
		}
	}






