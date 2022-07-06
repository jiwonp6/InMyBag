package com.lec.mybag.mybag.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.dto.MyBagBoardDto;
import com.lec.mybag.member.service.Service;

public class MyBagBoardListService implements Service {

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
		final int PAGESIZE=6;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		MyBagBoardDao bDao = MyBagBoardDao.getInstance();
		ArrayList<MyBagBoardDto> mybagboardList = bDao.bListBoard(startRow, endRow);
		request.setAttribute("mybagboardList", mybagboardList);
		int totCnt = bDao.getMyBagBoardTotCnt(); // 글갯수
		int pageCnt = (int)Math.ceil((double)totCnt/PAGESIZE);//페이지갯수
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt); // totCnt는 없으면 myBagboardList.size()대용
		request.setAttribute("pageNum", currentPage);
	}

}
