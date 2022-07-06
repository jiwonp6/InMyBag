package com.lec.mybag.admin.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.member.service.Service;

public class ASearchAdminService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String search_aId = request.getParameter("search_aId"); // 찾는 aId
		String spageNum = request.getParameter("spageNum");
		if(spageNum==null) {
			if(request.getAttribute("spageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				spageNum = "1";
			}else {
				spageNum = (String)request.getAttribute("spageNum");
			}
		}
		int scurrentPage = Integer.parseInt(spageNum);
		final int sPAGESIZE=12, sBLOCKSIZE=5;
		int sstartRow = (scurrentPage-1) * sPAGESIZE +1;
		int sendRow   = sstartRow + sPAGESIZE -1;
		AdminDao aDao = AdminDao.getInstance();
		ArrayList<AdminDto> searchAdmin = aDao.searchAdmin(search_aId, sstartRow, sendRow);
		request.setAttribute("searchAdmin", searchAdmin);
		int stotCnt = aDao.getSearchAdminTotCnt(search_aId); // 검색된 관리자수
		int spageCnt = (int)Math.ceil((double)stotCnt/sPAGESIZE);//페이지갯수
		int sstartPage = ((scurrentPage-1)/sBLOCKSIZE)*sBLOCKSIZE+1;
		int sendPage = sstartPage + sBLOCKSIZE - 1;
		if(sendPage>spageCnt) {
			sendPage = spageCnt;
		}
		request.setAttribute("sBLOCKSIZE", sBLOCKSIZE);
		request.setAttribute("sstartPage", sstartPage);
		request.setAttribute("sendPage", sendPage);
		request.setAttribute("spageCnt", spageCnt);
		request.setAttribute("stotCnt", stotCnt);
		request.setAttribute("spageNum", scurrentPage);

	}

}
