package com.lec.mybag.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.admin.service.AAllViewService;
import com.lec.mybag.admin.service.AJoinService;
import com.lec.mybag.admin.service.ALoginService;
import com.lec.mybag.admin.service.ALogoutService;
import com.lec.mybag.admin.service.AModifyService;
import com.lec.mybag.admin.service.ASearchAdminService;
import com.lec.mybag.admin.service.AWithdrawalService;
import com.lec.mybag.admin.service.AidConfirmService;
import com.lec.mybag.item.service.ItemBoardListService;
import com.lec.mybag.member.service.MAllViewService;
import com.lec.mybag.member.service.MJoinService;
import com.lec.mybag.member.service.MLoginService;
import com.lec.mybag.member.service.MLogoutService;
import com.lec.mybag.member.service.MModifyService;
import com.lec.mybag.member.service.MSearchMemberService;
import com.lec.mybag.member.service.MWithdrawalService;
import com.lec.mybag.member.service.MemailConfirmService;
import com.lec.mybag.member.service.MidConfirmService;
import com.lec.mybag.member.service.MyBoardListService;
import com.lec.mybag.member.service.MyLikeyListService;
import com.lec.mybag.member.service.Service;

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
		String com     = uri.substring(conPath.length()); // ????????? ??????
		String viewPage = null;
		Service service = null;
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * member ?????? ??????  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		if(com.equals("/joinView.let")) { // ???????????? ??????
			viewPage = "member/join.jsp";
		}else if(com.equals("/idConfirm.let")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(com.equals("/emailConfirm.let")) {
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/emailConfirm.jsp";
		}else if(com.equals("/join.let")) { // ???????????? DB ??????
			service = new MJoinService(); // execute????????? : mId???????????? ??? ????????????
			service.execute(request, response);
			viewPage = "loginView.let";
		}else if(com.equals("/loginView.let")) { // ????????? ??????
			viewPage = "member/login.jsp";
		}else if(com.equals("/login.let")) { // ????????? DB ??? ?????? ??????
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/logout.let")) {//???????????? - ?????? ?????????
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/modifyView.let")) { // ?????? ?????? ??????
			viewPage = "member/modify.jsp";
		}else if(com.equals("/modify.let")) { // DB??? ?????? ??????
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/withdrawalagreeView.let")) { // ???????????? ???
			viewPage = "member/withdrawalagree.jsp";
		}else if(com.equals("/withdrawalView.let")) { // ???????????? ????????????
			viewPage = "member/withdrawal.jsp";
		}else if(com.equals("/withdrawal.let")) { // ????????????
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "/main.do";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * myBag ?????? ??????  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/myboardList.let")) {
			service = new MyBoardListService();
			service.execute(request, response);
			viewPage = "member/myboardList.jsp";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * likey ?????? ??????  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/likeyList.let")) {
			service = new MyLikeyListService();
			service.execute(request, response);
			viewPage = "member/mylikeyList.jsp";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * admin ?????? ??????  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/adminloginView.let")) { // ????????? ??????
			viewPage = "admin/login.jsp";
		}else if(com.equals("/adminlogin.let")) { // ????????? DB ??? ?????? ??????
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/adminlogout.let")) {//???????????? - ?????? ?????????
			service = new ALogoutService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/adminmodifyView.let")) { // ?????? ?????? ??????
			viewPage = "admin/modify.jsp";
		}else if(com.equals("/adminmodify.let")) { // DB??? ?????? ??????
			service = new AModifyService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/adminwithdrawalView.let")) { // ???????????????
			viewPage = "admin/withdrawal.jsp";
		}else if(com.equals("/adminwithdrawal.let")) { // ???????????????
			service = new AWithdrawalService();
			service.execute(request, response);
			viewPage = "/main.do";
		}else if(com.equals("/allView.let")) { // ????????????????????????
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "admin/mAllView.jsp";
		}else if(com.equals("/searchMemberView.let")) { // ????????????
			service = new MSearchMemberService();
			service.execute(request, response);
			viewPage = "admin/mSearchMemberView.jsp";
		}else if(com.equals("/withdrawalMember.let")) { // ????????????
			viewPage = "admin/beforeWithdrawal.jsp";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * ??????????????? ?????? ??????  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/adminjoinView.let")) { // ???????????? ??????
			viewPage = "admin/join.jsp";
		}else if(com.equals("/adminidConfirm.let")) {
			service = new AidConfirmService();
			service.execute(request, response);
			viewPage = "admin/idConfirm.jsp";
		}else if(com.equals("/adminjoin.let")) { // ???????????? DB ??????
			service = new AJoinService(); // execute????????? : aId???????????? ??? ????????????
			service.execute(request, response);
			viewPage = "adminloginView.let";
		}else if(com.equals("/allAdminView.let")) { // ???????????????????????????
			service = new AAllViewService();
			service.execute(request, response);
			viewPage = "admin/aAllView.jsp";
		}else if(com.equals("/searchAdminView.let")) { // ???????????????
			service = new ASearchAdminService();
			service.execute(request, response);
			viewPage = "admin/aSearchAdminView.jsp";
		}else if(com.equals("/withdrawalAdmin.let")) { // ???????????????
			viewPage = "admin/beforeWithdrawal.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
