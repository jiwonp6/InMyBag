package com.lec.mybag.like.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.LikeMyBagDao;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.member.service.Service;

public class LikeMyBagDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto) httpSession.getAttribute("member");
		String mId = member.getmId(); // 로그인 한 사람의 mId
		int bId = Integer.parseInt(request.getParameter("bId"));
		LikeMyBagDao lDao = LikeMyBagDao.getInstance();
		int result = lDao.deleteLikemybag(mId, bId);
		System.out.println("좋아요취소"+result);
		if (result == LikeMyBagDao.SUCCESS) {
			request.setAttribute("dislikemybagResult", "좋아요취소 성공");
		} else {
			request.setAttribute("dislikemybagResult", "좋아요취소 실패");
		}
	}

}
