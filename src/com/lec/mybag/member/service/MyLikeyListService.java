package com.lec.mybag.member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.LikeMyBagDao;
import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.dto.LikemyBagDto;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.dto.MyBagBoardDto;


public class MyLikeyListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String member_mId = member.getmId(); // 로그인 한 사람의 mId
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				pageNum = "1";
			}else {
				pageNum = (String)request.getAttribute("pageNum");
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=6, BLOCKSIZE=3;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		LikeMyBagDao lDao = LikeMyBagDao.getInstance();
		ArrayList<MyBagBoardDto> likemybagList = lDao.getLikeMyBag(member_mId, startRow, endRow);
		request.setAttribute("likemybagList", likemybagList);
		int totCnt = lDao.getLikeMyBagTotCnt(member_mId); // 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		int startPage = ((currentPage-1)/BLOCKSIZE)*BLOCKSIZE+1;
		int endPage = startPage + BLOCKSIZE - 1;
		if(endPage>pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 itemboardList.size()대용
		request.setAttribute("pageNum", currentPage);
	}

}
