package jp.ucs.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * システム名：社員管理システム
 * クラス名  ：LogoutServlet
 * 処理概要  ：ログアウトする。
 * プロジェクト名：Hrsm
 * 作成者    ：小西香菜子
 * 作成日付：2019年10月3日
 */

@WebServlet("/Logout")

    public class LogoutServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public LogoutServlet() {
            super();

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            HttpSession session = request.getSession();
            session.invalidate();

            RequestDispatcher dis = request.getRequestDispatcher("/login.jsp");
            dis.forward(request, response);
        }

    }