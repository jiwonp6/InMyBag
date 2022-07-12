package com.lec.mybag.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.MemberDao;
import com.lec.mybag.dto.MemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		MemberDao mDao = MemberDao.getInstance();
		int result = mDao.loginCheck(mId, mPw);
		if(result==MemberDao.LOGIN_SUCCESS) { // 로그인 성공
			HttpSession session = request.getSession();
			MemberDto member = mDao.getMember(mId);
			session.setAttribute("member", member);
		}else { // 로그인 실패
			request.setAttribute("loginErrorMsg", "아이디와 비밀번호를 확인해주세요");
		}
	}

}
