package com.lec.mybag.member.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.LikeMyBagDao;
import com.lec.mybag.dao.MemberDao;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.dto.MyBagBoardDto;

public class MSearchMemberService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String search_mId = request.getParameter("search_mId"); // 찾는 mId
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
		MemberDao mDao = MemberDao.getInstance();
		ArrayList<MemberDto> searchMember = mDao.searchMember(search_mId, sstartRow, sendRow);
		request.setAttribute("searchMember", searchMember);
		int stotCnt = mDao.getSearchMemberTotCnt(search_mId); // 검색된 회원수
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
