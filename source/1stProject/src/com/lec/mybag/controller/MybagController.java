package com.lec.mybag.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.SetCharacterEncodingFilter;

import com.lec.mybag.faq.service.faqBoardListService;
import com.lec.mybag.faq.service.faqBoardContentService;
import com.lec.mybag.faq.service.faqBoardDeleteService;
import com.lec.mybag.faq.service.faqBoardModifyService;
import com.lec.mybag.faq.service.faqBoardModifyViewService;
import com.lec.mybag.faq.service.faqBoardWriteService;
import com.lec.mybag.item.service.ItemBoardContentService;
import com.lec.mybag.item.service.ItemBoardDeleteService;
import com.lec.mybag.item.service.ItemBoardListService;
import com.lec.mybag.item.service.ItemBoardModifyService;
import com.lec.mybag.item.service.ItemBoardModifyViewService;
import com.lec.mybag.item.service.ItemBoardReplyViewService;
import com.lec.mybag.item.service.ItemBoardReplyService;
import com.lec.mybag.item.service.ItemBoardWriteService;
import com.lec.mybag.like.service.LikeMyBagCountService;
import com.lec.mybag.like.service.LikeMyBagDeleteService;
import com.lec.mybag.like.service.LikeMyBagWriteService;
import com.lec.mybag.member.service.MJoinService;
import com.lec.mybag.member.service.MLoginService;
import com.lec.mybag.member.service.MLogoutService;
import com.lec.mybag.member.service.MModifyService;
import com.lec.mybag.member.service.MWithdrawalService;
import com.lec.mybag.member.service.MemailConfirmService;
import com.lec.mybag.member.service.MidConfirmService;
import com.lec.mybag.member.service.MyBoardListService;
import com.lec.mybag.member.service.Service;
import com.lec.mybag.mybag.service.MyBagBoardContentService;
import com.lec.mybag.mybag.service.MyBagBoardDeleteService;
import com.lec.mybag.mybag.service.MyBagBoardListService;
import com.lec.mybag.mybag.service.MyBagBoardModifyService;
import com.lec.mybag.mybag.service.MyBagBoardModifyViewService;
import com.lec.mybag.mybag.service.MyBagBoardWriteService;
import com.lec.mybag.notice.service.NoticeBoardContentService;
import com.lec.mybag.notice.service.NoticeBoardDeleteService;
import com.lec.mybag.notice.service.NoticeBoardListService;
import com.lec.mybag.notice.service.NoticeBoardModifyService;
import com.lec.mybag.notice.service.NoticeBoardModifyViewService;
import com.lec.mybag.notice.service.NoticeBoardWriteService;
import com.lec.mybag.qna.service.QnaBoardContentService;
import com.lec.mybag.qna.service.QnaBoardDeleteService;
import com.lec.mybag.qna.service.QnaBoardListService;
import com.lec.mybag.qna.service.QnaBoardModifyService;
import com.lec.mybag.qna.service.QnaBoardModifyViewService;
import com.lec.mybag.qna.service.QnaBoardReplyService;
import com.lec.mybag.qna.service.QnaBoardReplyViewService;
import com.lec.mybag.qna.service.QnaBoardWriteService;
import com.lec.mybag.reply.service.ReplyMyBagDeleteService;
import com.lec.mybag.reply.service.ReplyMyBagListService;
import com.lec.mybag.reply.service.ReplyMyBagModifyService;
import com.lec.mybag.reply.service.ReplyMyBagModifyViewService;
import com.lec.mybag.reply.service.ReplyMyBagReplyService;
import com.lec.mybag.reply.service.ReplyMyBagReplyViewService;
import com.lec.mybag.reply.service.ReplyMyBagWriteService;

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
			service = new MyBagBoardListService();	//글 리스트
			service.execute(request, response);
			viewPage = "mybagBoard/mybagboardList.jsp";
		}else if(com.equals("/append.do")) {	//댓글추가
			service = new MyBagBoardListService();
			service.execute(request, response);
			viewPage = "mybagBoard/append.jsp";
		}else if(com.equals("/mybagboardWriteView.do")) {//글쓰기 모듈
			viewPage = "mybagBoard/mybagboardWrite.jsp";
		}else if(com.equals("/mybagboardWrite.do")) {//글쓰기
			service = new MyBagBoardWriteService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/mybagboardContent.do")) {//글내용상세보기
			service = new MyBagBoardContentService();
			service.execute(request, response);
			service = new ReplyMyBagListService();	//댓글리스트
			service.execute(request, response);
			service = new LikeMyBagCountService();	//좋아요표시
			service.execute(request, response);
			viewPage = "mybagBoard/mybagboardContent.jsp";
		}else if(com.equals("/mybagboardModifyView.do")) {//글수정모듈
			service = new MyBagBoardModifyViewService();
			service.execute(request, response);
			viewPage = "mybagBoard/mybagboardModify.jsp";
		}else if(com.equals("/mybagboradModify.do")) {//글수정
			service = new MyBagBoardModifyService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}else if(com.equals("/mybagboardDelete.do")) {//글삭제
			service = new MyBagBoardDeleteService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * ReplyMyBag 관련 요청  * * * * * * *
		 * * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/replyappend.do")) {
			service = new MyBagBoardContentService();
			service.execute(request, response);
			service = new ReplyMyBagListService();
			service.execute(request, response);
			viewPage = "replymybag/replyappend.jsp";
		}else if(com.equals("/replymybagWrite.do")) {	//댓글쓰기
			service = new ReplyMyBagWriteService();
			service.execute(request, response);
			viewPage = "mybagboardContent.do";
		}else if(com.equals("/replymybagModifyView.do")) {//댓글수정 모듈
			service = new ReplyMyBagModifyViewService();
			service.execute(request, response);
			viewPage = "replymybag/replymybagModify.jsp";
		}else if(com.equals("/replymybagModify.do")) {//댓글수정
			service = new ReplyMyBagModifyService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}else if(com.equals("/replymybagDelete.do")) {//댓글삭제
			service = new ReplyMyBagDeleteService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}
		
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * LikemyBag 관련 요청  * * * * * * *
		 * * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/likemybagWrite.do")) {
			service = new LikeMyBagWriteService();
			service.execute(request, response);
			viewPage = "mybagboardContent.do";
		}else if(com.equals("/likemybagDelete.do")) {
			service = new LikeMyBagDeleteService();
			service.execute(request, response);
			viewPage = "mybagboardContent.do";
		}
		
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * ItemBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/itemboardList.do")) {//글 리스트
			service = new ItemBoardListService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardList.jsp";
		}else if(com.equals("/itemboardWriteView.do")) {//글쓰기 모듈
			viewPage = "itemBoard/itemboardWrite.jsp";
		}else if(com.equals("/itemboardWrite.do")) {//글쓰기
			service = new ItemBoardWriteService();
			service.execute(request, response);
			viewPage = "itemboardList.do";
		}else if(com.equals("/itemboardContent.do")) {//글 내용 상세보기
			service = new ItemBoardContentService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardContent.jsp";
		}else if(com.equals("/itemboardModifyView.do")) {//글 수정 모듈
			service = new ItemBoardModifyViewService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardModify.jsp";
		}else if(com.equals("/itemboradModify.do")) {//글수정
			service = new ItemBoardModifyService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}else if(com.equals("/itemboardDelete.do")) {//글삭제
			service = new ItemBoardDeleteService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}else if(com.equals("/itemboardReplyView.do")) {//답글쓰기 모듈
			service = new ItemBoardReplyViewService();
			service.execute(request, response);
			viewPage = "itemBoard/itemboardReply.jsp";
		}else if(com.equals("/itemboardReply.do")) {//답글쓰기
			service = new ItemBoardReplyService();
			service.execute(request, response);
			viewPage = "itemboardList.do";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * NoticeBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/noticeboardList.do")) {//글 리스트
			service = new NoticeBoardListService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeboardList.jsp";
		}else if(com.equals("/noticeboardWriteView.do")) {//글쓰기 모듈
			viewPage = "noticeBoard/noticeboardWrite.jsp";
		}else if(com.equals("/noticeboardWrite.do")) {//글쓰기
			service = new NoticeBoardWriteService();
			service.execute(request, response);
			viewPage = "noticeboardList.do";
		}else if(com.equals("/noticeboardContent.do")) {//글내용 상세보기
			service = new NoticeBoardContentService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeboardContent.jsp";
		}else if(com.equals("/noticeboardModifyView.do")) {//글수정 모듈
			service = new NoticeBoardModifyViewService();
			service.execute(request, response);
			viewPage = "noticeBoard/noticeboardModify.jsp";
		}else if(com.equals("/noticeboradModify.do")) {//글수정
			service = new NoticeBoardModifyService();
			service.execute(request, response);
			viewPage = "noticeboardList.do";
		}else if(com.equals("/noticeboardDelete.do")) {//글삭제
			service = new NoticeBoardDeleteService();
			service.execute(request, response);
			viewPage = "noticeboardList.do";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * FAQBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/faqboardList.do")) {//글 리스트
			service = new faqBoardListService();
			service.execute(request, response);
			viewPage = "faqBoard/faqboardList.jsp";
		}else if(com.equals("/faqboardWriteView.do")) {//글쓰기 모듈
			viewPage = "faqBoard/faqboardWrite.jsp";
		}else if(com.equals("/faqboardWrite.do")) {//글쓰기
			service = new faqBoardWriteService();
			service.execute(request, response);
			viewPage = "faqboardList.do";
		}else if(com.equals("/faqboardContent.do")) {//글내용 상세보기
			service = new faqBoardContentService();
			service.execute(request, response);
			viewPage = "faqBoard/faqboardContent.jsp";
		}else if(com.equals("/faqboardModifyView.do")) {//글수정 모듈
			service = new faqBoardModifyViewService();
			service.execute(request, response);
			viewPage = "faqBoard/faqboardModify.jsp";
		}else if(com.equals("/faqboradModify.do")) {//글수정
			service = new faqBoardModifyService();
			service.execute(request, response);
			viewPage = "faqboardList.do";
		}else if(com.equals("/faqboardDelete.do")) {//글삭제
			service = new faqBoardDeleteService();
			service.execute(request, response);
			viewPage = "faqboardList.do";
		}
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * QNABoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		else if(com.equals("/qnaboardList.do")) {//글 리스트
			service = new QnaBoardListService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardList.jsp";
		}else if(com.equals("/qnaboardWriteView.do")) {//글쓰기 모듈
			viewPage = "qnaBoard/qnaboardWrite.jsp";
		}else if(com.equals("/qnaboardWrite.do")) {//글쓰기
			service = new QnaBoardWriteService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}else if(com.equals("/qnaboardContent.do")) {//글 내용 상세보기
			service = new QnaBoardContentService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardContent.jsp";
		}else if(com.equals("/qnaboardModifyView.do")) {//글 수정 모듈
			service = new QnaBoardModifyViewService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardModify.jsp";
		}else if(com.equals("/qnaboardModify.do")) {//글수정
			service = new QnaBoardModifyService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}else if(com.equals("/qnaboardDelete.do")) {//글삭제
			service = new QnaBoardDeleteService();
			service.execute(request, response);
			viewPage = "myboardList.let";
		}else if(com.equals("/qnaboardReplyView.do")) { //답글 쓰기 모듈
			service = new QnaBoardReplyViewService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardReply.jsp";
		}else if(com.equals("/qnaboardReply.do")) {	//답글쓰기
			service = new QnaBoardReplyService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}else if(com.equals("/qnaboardreplyModifyView.do")) {	//답글수정view
			service = new QnaBoardModifyViewService();
			service.execute(request, response);
			viewPage = "qnaBoard/qnaboardreplyModify.jsp";
		}else if(com.equals("/qnaboardreplyModify.do")) {	//답글수정
			service = new QnaBoardModifyService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}else if(com.equals("/qnaboardreplyDelete.do")) {	//답글삭제
			service = new QnaBoardDeleteService();
			service.execute(request, response);
			viewPage = "qnaboardList.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
