package com.lec.mybag.like.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.LikeMyBagDao;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.service.Service;

public class LikeMyBagCountService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		MemberDto member = (MemberDto)httpSession.getAttribute("member");
		if(member == null) {
			String mId = null;
		}else {
			String mId = member.getmId();
			int bId = Integer.parseInt(request.getParameter("bId"));
			LikeMyBagDao lDao = LikeMyBagDao.getInstance();
			int result = lDao.countLikeMyBag(mId, bId);
			request.setAttribute("like", result);
		}
	}

}
