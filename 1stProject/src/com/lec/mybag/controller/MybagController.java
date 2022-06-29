package com.lec.mybag.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import com.lec.mybag.faq.service.FaqBoardListService;
import com.lec.mybag.item.service.ItemBoardContentService;
import com.lec.mybag.item.service.ItemBoardDeleteService;
import com.lec.mybag.item.service.ItemBoardListService;
import com.lec.mybag.item.service.ItemBoardModifyService;
import com.lec.mybag.item.service.ItemBoardModifyViewService;
import com.lec.mybag.item.service.ItemBoardReplyViewService;
import com.lec.mybag.item.service.ItemBoardReplyService;
import com.lec.mybag.item.service.ItemBoardWriteService;
import com.lec.mybag.qna.service.QnaBoardContentService;
import com.lec.mybag.qna.service.QnaBoardDeleteService;
import com.lec.mybag.qna.service.QnaBoardListService;
import com.lec.mybag.qna.service.QnaBoardModifyService;
import com.lec.mybag.qna.service.QnaBoardModifyViewService;
import com.lec.mybag.qna.service.QnaBoardReplyService;
import com.lec.mybag.qna.service.QnaBoardReplyViewService;
import com.lec.mybag.qna.service.QnaBoardWriteService;
import com.lec.mybag.service.MJoinService;
import com.lec.mybag.service.MLoginService;
import com.lec.mybag.service.MLogoutService;
import com.lec.mybag.service.MModifyService;
import com.lec.mybag.service.MWithdrawalService;
import com.lec.mybag.service.MemailConfirmService;
import com.lec.mybag.service.MidConfirmService;
import com.lec.mybag.service.Service;

//Servlet implementation class MybagController
@WebServlet("*.do")
public class MybagController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    //@see HttpServlet#HttpServlet()
    public MybagController() {    }

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
		if(com.equals("/main.do")) {
			viewPage="main/main.jsp";
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * member 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		}else if(com.equals("/joinView.do")) { // 회원가입 화면
			viewPage = "member/join.jsp";
		}else if(com.equals("/idConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(com.equals("/emailConfirm.do")) {
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/emailConfirm.jsp";
		}else if(com.equals("/join.do")) { // 회원가입 DB 처리
			service = new MJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.do";
		}
		else if(com.equals("/loginView.do")) { // 로그인 화면
			viewPage = "member/login.jsp";
		}
		else if(com.equals("/login.do")) { // 로그인 DB 및 세션 처리
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(com.equals("/logout.do")) {//로그아웃 - 세션 날리기
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(com.equals("/modifyView.do")) { // 정보 수정 화면
			viewPage = "member/modify.jsp";
		}else if(com.equals("/modify.do")) { // DB에 정보 수정
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(com.equals("/withdrawal.do")) { // 회원탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main/main.jsp";	
		}	
		
		/*else if(com.equals("/allView.do")) { // 회원목록가져오기
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}*/
		
		
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * ItemBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/itemboardList.do")) {
			service = new ItemBoardListService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardList.jsp";
		}else if(com.equals("/itemboardWriteView.do")) {
			viewPage = "itemBoard/itemboardWrite.jsp";
		}else if(com.equals("/itemboardWrite.do")) {
			service = new ItemBoardWriteService();
			service.execute(request, response);
			viewPage = "itemboardList.do";
		}else if(com.equals("/itemboardContent.do")) {
			service = new ItemBoardContentService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardContent.jsp";
		}else if(com.equals("/itemboardModifyView.do")) {
			service = new ItemBoardModifyViewService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardModify.jsp";
		}else if(com.equals("/itemboradModify.do")) {
			service = new ItemBoardModifyService();
			service.execute(request, response);
			viewPage = "itemboardList.do";
		}else if(com.equals("/itemboardDelete.do")) {
			service = new ItemBoardDeleteService();
			service.execute(request, response);
			viewPage = "itemboardList.do";
		}else if(com.equals("/itemboardReplyView.do")) {
			service = new ItemBoardReplyViewService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardReply.jsp";
		}else if(com.equals("/itemboardReply.do")) {
			service = new ItemBoardReplyService();
			service.execute(request, response);
			viewPage = "itemboardList.do";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * QNABoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/qnaboardList.do")) {
			service = new QnaBoardListService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardList.jsp";
		}else if(com.equals("/qnaboardWriteView.do")) {
			viewPage = "qnaBoard/qnaboardWrite.jsp";
		}else if(com.equals("/qnaboardWrite.do")) {
			service = new QnaBoardWriteService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}else if(com.equals("/qnaboardContent.do")) {
			service = new QnaBoardContentService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardContent.jsp";
		}else if(com.equals("/qnaboardModifyView.do")) {
			service = new QnaBoardModifyViewService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardModify.jsp";
		}else if(com.equals("/qnaboradModify.do")) {
			service = new QnaBoardModifyService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}
		else if(com.equals("/qnaboardDelete.do")) {
			service = new QnaBoardDeleteService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}else if(com.equals("/qnaboardReplyView.do")) {
			service = new QnaBoardReplyViewService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardReply.jsp";
		}else if(com.equals("/qnaboardReply.do")) {
			service = new QnaBoardReplyService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * FAQBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/faqboardList.do")) {
			service = new FaqBoardListService();
			service.execute(request, response);
			viewPage = "faqBoard/faqboardList.jsp";
		}/*else if(com.equals("/qnaboardWriteView.do")) {
			viewPage = "qnaBoard/qnaboardWrite.jsp";
		}else if(com.equals("/qnaboardWrite.do")) {
			service = new QnaBoardWriteService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}else if(com.equals("/qnaboardContent.do")) {
			service = new QnaBoardContentService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardContent.jsp";
		}else if(com.equals("/qnaboardModifyView.do")) {
			service = new QnaBoardModifyViewService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardModify.jsp";
		}else if(com.equals("/qnaboradModify.do")) {
			service = new QnaBoardModifyService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}
		else if(com.equals("/qnaboardDelete.do")) {
			service = new QnaBoardDeleteService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}*/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
