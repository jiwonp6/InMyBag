package com.lec.mybag.reply.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.ReplyMyBagDto;
import com.lec.mybag.service.Service;

public class ReplyMyBagListService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bId = Integer.parseInt(request.getParameter("bId"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				pageNum = "1";
			}else {
				pageNum = (String)request.getAttribute("pageNum");
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=2;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		ArrayList<ReplyMyBagDto> replymybagList = rDao.rListBoard(bId, startRow, endRow);
		request.setAttribute("replymybagList", replymybagList);
		int totCnt = rDao.getReplyMyBagTotCnt(bId); // 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 replymybagList.size()대용
		request.setAttribute("pageNum", currentPage);
	}

}
