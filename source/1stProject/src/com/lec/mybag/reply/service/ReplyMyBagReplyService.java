package com.lec.mybag.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.member.service.Service;

public class ReplyMyBagReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("답변쓰기");
		// mId, iTitle, iContent,  filename, iIp
		HttpSession httpSession = request.getSession();
		String mId = ((MemberDto)httpSession.getAttribute("member")).getmId();
		String rContent = request.getParameter("rContent");
		String rIp = request.getRemoteAddr();
		int rGroup = Integer.parseInt(request.getParameter("rGroup"));
		int rStep = Integer.parseInt(request.getParameter("rStep"));
		int rIndent = Integer.parseInt(request.getParameter("rIndent"));
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		int result = rDao.replyReplyMyBag(mId, rContent, rGroup, rStep, rIndent, rIp);
		// joinMember결과에 따라 적절히 request.setAttribute
		if(result == ReplyMyBagDao.SUCCESS) { // 회원가입 진행
			request.setAttribute("replymybagResult", "답글쓰기 성공");
		}else {
			request.setAttribute("replymybagResult", "답글쓰기 실패");
		}
		// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
		request.setAttribute("rpageNum", request.getParameter("rpageNum"));
	}

}
