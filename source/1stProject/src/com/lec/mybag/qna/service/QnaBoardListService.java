package com.lec.mybag.qna.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dto.QnaBoardDto;
import com.lec.mybag.member.service.Service;

public class QnaBoardListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				pageNum = "1";
			}else {
				pageNum = (String)request.getAttribute("pageNum");
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=12, BLOCKSIZE=3;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		QnaBoardDao qDao = QnaBoardDao.getInstance();
		ArrayList<QnaBoardDto> qnaboardList = qDao.qListBoard(startRow, endRow);
		request.setAttribute("qnaboardList", qnaboardList);
		int totCnt = qDao.getQnaBoardTotCnt(); // 글갯수
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

