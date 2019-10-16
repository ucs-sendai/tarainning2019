package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.ucs.constants.Constants;

/**
 * システム名：社員管理システム
 * クラス名  ：HaveLicenseServlet
 * 処理概要  ：資格情報を登録する。
 * プロジェクト名：Hrsm
 * 作成者    ：小西香菜子
 * 作成日付：2019年10月8日(火)
 */

@WebServlet("/HaveLicenseSelect")
public class HaveLicenseSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public HaveLicenseSelectServlet() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.haveLicenseSelect);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		// 変数の宣言、パラメータを取得
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String licenseDate = request.getParameter("licenseDate");
		String license = request.getParameter("license");

		//セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("empId",empId);
		session.setAttribute("empName", empName);
		session.setAttribute("licenseDate", licenseDate);
		session.setAttribute("license", license);

		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.haveLicenseFin);
		dispatcher.forward(request, response);
	}

}

