package com.lec.mybag.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.ItemBoardDao;
import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.ItemBoardDto;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.dto.MyBagBoardDto;
import com.lec.mybag.dto.QnaBoardDto;
import com.lec.mybag.dto.ReplyMyBagDto;

public class MyBoardListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		String mId = member.getmId(); // 로그인 한 사람의 mId
		//mybagboard
		String bpageNum = request.getParameter("bpageNum");
		if(bpageNum==null) {
			if(request.getAttribute("bpageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				bpageNum = "1";
			}else {
				bpageNum = (String)request.getAttribute("bpageNum");
			}
		}
		int bcurrentPage = Integer.parseInt(bpageNum);
		final int bPAGESIZE=3, bBLOCKSIZE=3;
		int bstartRow = (bcurrentPage-1) * bPAGESIZE +1;
		int bendRow   = bstartRow + bPAGESIZE -1;
		MyBagBoardDao bDao = MyBagBoardDao.getInstance();
		ArrayList<MyBagBoardDto> mybagboardList = bDao.bMyListBoard(mId, bstartRow, bendRow);
		request.setAttribute("mybagboardList", mybagboardList);
		int btotCnt = bDao.getMybListTotCnt(mId); // 내글갯수
		int bpageCnt = (int)Math.ceil((double)btotCnt/bPAGESIZE);//페이지갯수
		int bstartPage = ((bcurrentPage-1)/bBLOCKSIZE)*bBLOCKSIZE+1;
		int bendPage = bstartPage + bBLOCKSIZE - 1;
		if(bendPage>bpageCnt) {
			bendPage = bpageCnt;
		}
		request.setAttribute("bBLOCKSIZE", bBLOCKSIZE);
		request.setAttribute("bstartPage", bstartPage);
		request.setAttribute("bendPage", bendPage);
		request.setAttribute("bpageCnt", bpageCnt);
		request.setAttribute("btotCnt", btotCnt); // totCnt는 없으면 itemboardList.size()대용
		request.setAttribute("bpageNum", bcurrentPage);
		
		//reply
		String rpageNum = request.getParameter("rpageNum");
		if(rpageNum==null) {
			if(request.getAttribute("rpageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				rpageNum = "1";
			}else {
				rpageNum = (String)request.getAttribute("rpageNum");
			}
		}
		int rcurrentPage = Integer.parseInt(rpageNum);
		final int rPAGESIZE=3, rBLOCKSIZE=3;
		int rstartRow = (rcurrentPage-1) * rPAGESIZE +1;
		int rendRow   = rstartRow + rPAGESIZE -1;
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		ArrayList<ReplyMyBagDto> replymybagList = rDao.rMyListBoard(mId, rstartRow, rendRow);
		request.setAttribute("replymybagList", replymybagList);
		int rtotCnt = rDao.getMyrListTotCnt(mId); // 내글갯수
		int rpageCnt = (int)Math.ceil((double)rtotCnt/rPAGESIZE);//페이지갯수
		int rstartPage = ((rcurrentPage-1)/rBLOCKSIZE)*rBLOCKSIZE+1;
		int rendPage = rstartPage + rBLOCKSIZE - 1;
		if(rendPage>rpageCnt) {
			rendPage = rpageCnt;
		}
		request.setAttribute("rBLOCKSIZE", rBLOCKSIZE);
		request.setAttribute("rstartPage", rstartPage);
		request.setAttribute("rendPage", rendPage);
		request.setAttribute("rpageCnt", rpageCnt);
		request.setAttribute("rtotCnt", rtotCnt); // totCnt는 없으면 itemboardList.size()대용
		request.setAttribute("rpageNum", rcurrentPage);
		
		//itemboard
		String ipageNum = request.getParameter("ipageNum");
		if(ipageNum==null) {
			if(request.getAttribute("ipageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				ipageNum = "1";
			}else {
				ipageNum = (String)request.getAttribute("ipageNum");
			}
		}
		int icurrentPage = Integer.parseInt(ipageNum);
		final int iPAGESIZE=3, iBLOCKSIZE=3;
		int istartRow = (icurrentPage-1) * iPAGESIZE +1;
		int iendRow   = istartRow + iPAGESIZE -1;
		ItemBoardDao iDao = ItemBoardDao.getInstance();
		ArrayList<ItemBoardDto> itemboardList = iDao.iMyListBoard(mId, istartRow, iendRow);
		request.setAttribute("itemboardList", itemboardList);
		int itotCnt = iDao.getMyiListTotCnt(mId); // 내글갯수
		int ipageCnt = (int)Math.ceil((double)itotCnt/iPAGESIZE);//페이지갯수
		int istartPage = ((icurrentPage-1)/iBLOCKSIZE)*iBLOCKSIZE+1;
		int iendPage = istartPage + iBLOCKSIZE - 1;
		if(iendPage>ipageCnt) {
			iendPage = ipageCnt;
		}
		request.setAttribute("iBLOCKSIZE", iBLOCKSIZE);
		request.setAttribute("istartPage", istartPage);
		request.setAttribute("iendPage", iendPage);
		request.setAttribute("ipageCnt", ipageCnt);
		request.setAttribute("itotCnt", itotCnt); // totCnt는 없으면 itemboardList.size()대용
		request.setAttribute("ipageNum", icurrentPage);
		
		//qnaboard
		String qpageNum = request.getParameter("qpageNum");
		if(qpageNum==null) {
			if(request.getAttribute("qpageNum")==null) { // 글 수정이나 답변글처리시 mRequest를 사용하여서 request에 set함
				qpageNum = "1";
			}else {
				qpageNum = (String)request.getAttribute("qpageNum");
			}
		}
		int qcurrentPage = Integer.parseInt(qpageNum);
		final int qPAGESIZE=3, qBLOCKSIZE=3;
		int qstartRow = (qcurrentPage-1) * qPAGESIZE +1;
		int qendRow   = qstartRow + qPAGESIZE -1;
		QnaBoardDao qDao = QnaBoardDao.getInstance();
		ArrayList<QnaBoardDto> qnaboardList = qDao.qMyListBoard(mId, qstartRow, qendRow);
		request.setAttribute("qnaboardList", qnaboardList);
		int qtotCnt = qDao.getMyqListTotCnt(mId); // 내글갯수
		int qpageCnt = (int)Math.ceil((double)qtotCnt/qPAGESIZE);//페이지갯수
		int qstartPage = ((qcurrentPage-1)/qBLOCKSIZE)*qBLOCKSIZE+1;
		int qendPage = qstartPage + qBLOCKSIZE - 1;
		if(qendPage>qpageCnt) {
			qendPage = qpageCnt;
		}
		request.setAttribute("qBLOCKSIZE", qBLOCKSIZE);
		request.setAttribute("qstartPage", qstartPage);
		request.setAttribute("qendPage", qendPage);
		request.setAttribute("qpageCnt", qpageCnt);
		request.setAttribute("qtotCnt", qtotCnt); // totCnt는 없으면 itemboardList.size()대용
		request.setAttribute("qpageNum", qcurrentPage);
		
	}

}
