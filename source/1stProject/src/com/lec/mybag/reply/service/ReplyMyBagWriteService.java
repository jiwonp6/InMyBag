package com.lec.mybag.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.member.service.Service;

public class ReplyMyBagWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//String pageNum = request.getParameter("pageNum");
		// mId, iTitle, iContent,  filename, iIp
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		if(member!=null) {
			String mId = member.getmId(); // 로그인 한 사람의 mId
			int bId = Integer.parseInt(request.getParameter("bId"));
			String rContent = request.getParameter("rContent");
			String rIp = request.getRemoteAddr();
			ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
			int result = rDao.writeReplyMyBag(mId, bId, rContent, rIp);
			System.out.println(result);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == ReplyMyBagDao.SUCCESS) { 
				request.setAttribute("replymybagResult", "글쓰기 성공");
			}else {
				request.setAttribute("replymybagResult", "글쓰기 실패");
			}
		}else {
			request.setAttribute("replymybagResult", "로그인 한 사람만 글쓸 수 있어요");
		}
	}
}
