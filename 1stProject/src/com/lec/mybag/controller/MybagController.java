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
import com.lec.mybag.mybag.service.MyBagBoardListService;
import com.lec.mybag.mybag.service.MyBagBoardWriteService;
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
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * MyBagBoard 관련 요청  * * * * * * *
		 * * * * * * * * * * *  * * * * * * * * * * * * */
		if(com.equals("/main.do")) {	// "/mybagboardList.do"
			service = new MyBagBoardListService();
			service.execute(request, response);
			viewPage = "mybagBoard/mybagboardList.jsp";
			/* viewPage="main/main.jsp"; */
		}else if(com.equals("/append.do")) {
			service = new MyBagBoardListService();
			service.execute(request, response);
			viewPage = "mybagBoard/append.jsp";
		}else if(com.equals("/mybagboardWriteView.do")) {
			viewPage = "mybagBoard/mybagboardWrite.jsp";
		}else if(com.equals("/mybagboardWrite.do")) {
			service = new MyBagBoardWriteService();
			service.execute(request, response);
			viewPage = "mybagboardList.do";
		}
		/*else if(com.equals("/qnaboardContent.do")) {
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
		}*/
		
		
		
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
		 * * * * * * * * * NoticeBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		/*else if(com.equals("/faqboardList.do")) {
			service = new FaqBoardListService();
			service.execute(request, response);
			viewPage = "faqBoard/faqboardList.jsp";
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
		}*/
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
