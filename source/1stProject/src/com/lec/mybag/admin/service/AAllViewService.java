package com.lec.mybag.admin.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.dao.MemberDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.member.service.Service;

public class AAllViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			if(request.getAttribute("pageNum")==null) { 
				pageNum = "1";
			}else {
				pageNum = (String)request.getAttribute("pageNum");
			}
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE=12, BLOCKSIZE=5;
		int startRow = (currentPage-1) * PAGESIZE +1;
		int endRow   = startRow + PAGESIZE -1;
		AdminDao aDao = AdminDao.getInstance();
		ArrayList<AdminDto> adminList = aDao.allAdmin(startRow, endRow);
		request.setAttribute("adminList", adminList);
		int totCnt = aDao.getAdminTotCnt(); // 관리자수
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
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", currentPage);
	}

}
