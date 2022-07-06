package com.lec.mybag.member.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.MemberDao;
import com.lec.mybag.dto.MemberDto;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String mId = request.getParameter("mId");
			String mPw = "";
			if(request.getParameter("newmPw").equals("")) {
				mPw=request.getParameter("oldmPw");
			}else {
				mPw=request.getParameter("newmPw");
			}
			String mName = request.getParameter("mName");
			String mBirthStr = request.getParameter("mBirth");
			Date mBirth = null;
			if (!mBirthStr.equals("")) {
				mBirth = Date.valueOf(mBirthStr);
			}
			String mEmail = request.getParameter("mEmail");
			MemberDao mDao = MemberDao.getInstance();
			// 회원정보 수정
			MemberDto member = new MemberDto(mId, mPw, mName, mBirth, mEmail, null);
			int result = mDao.modifyMember(member);
			if (result == MemberDao.SUCCESS) {// 수정 성공시 세션도 수정
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("member", member);
				request.setAttribute("modifyResult", "회원정보 수정 성공");
			} else {
				// 수정 실패시
				request.setAttribute("modifyResult", "회원정보 수정 실패");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
