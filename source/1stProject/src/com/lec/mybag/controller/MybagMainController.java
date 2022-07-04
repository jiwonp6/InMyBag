package com.lec.mybag.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.item.service.ItemBoardListService;
import com.lec.mybag.service.MJoinService;
import com.lec.mybag.service.MLoginService;
import com.lec.mybag.service.MLogoutService;
import com.lec.mybag.service.MModifyService;
import com.lec.mybag.service.MWithdrawalService;
import com.lec.mybag.service.MemailConfirmService;
import com.lec.mybag.service.MidConfirmService;
import com.lec.mybag.service.MyBoardListService;
import com.lec.mybag.service.MyLikeyListService;
import com.lec.mybag.service.Service;

//Servlet implementation class MybagController
@WebServlet("*.let")
public class MybagMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
  //@see HttpServlet#HttpServlet()
  public MybagMainController() {    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	//@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com     = uri.substring(conPath.length()); // 들어온 요청
		String viewPage = null;
		Service service = null;
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * member 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		if(com.equals("/joinView.let")) { // 회원가입 화면
			viewPage = "member/join.jsp";
		}else if(com.equals("/idConfirm.let")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(com.equals("/emailConfirm.let")) {
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/emailConfirm.jsp";
		}else if(com.equals("/join.let")) { // 회원가입 DB 처리
			service = new MJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.let";
		}else if(com.equals("/loginView.let")) { // 로그인 화면
			viewPage = "member/login.jsp";
		}else if(com.equals("/login.let")) { // 로그인 DB 및 세션 처리
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/logout.let")) {//로그아웃 - 세션 날리기
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/modifyView.let")) { // 정보 수정 화면
			viewPage = "member/modify.jsp";
		}else if(com.equals("/modify.let")) { // DB에 정보 수정
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/withdrawalagreeView.let")) { // 회원탈퇴 전
			viewPage = "member/withdrawalagree.jsp";
		}else if(com.equals("/withdrawalView.let")) { // 회원탈퇴
			viewPage = "member/withdrawal.jsp";
		}else if(com.equals("/withdrawal.let")) { // 회원탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "/main.do";
		}
		
		/*else if(com.equals("/allView.do")) { // 회원목록가져오기
		service = new MAllViewService();
		service.execute(request, response);
		viewPage = "member/mAllView.jsp";
		}*/
		
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * myBag 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/myboardList.let")) {
			service = new MyBoardListService();
			service.execute(request, response);
			viewPage = "member/myboardList.jsp";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * likey 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/likeyList.let")) {
			service = new MyLikeyListService();
			service.execute(request, response);
			viewPage = "member/mylikeyList.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
