package com.lec.mybag.member.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.MemberDao;
import com.lec.mybag.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// request을 이용하여 파라미터 받아와서 DB 저장
			String mId = request.getParameter("mId");
			String mPw = request.getParameter("mPw");
			String mName = request.getParameter("mName");
			String mBirthStr = request.getParameter("mBirth");
			String mEmail = request.getParameter("mEmail");
			Date mBirth = null;
			if(!mBirthStr.equals("")) {
				mBirth = Date.valueOf(mBirthStr);
			}
			MemberDao mDao = MemberDao.getInstance();
			MemberDto member = new MemberDto(mId, mPw, mName, mBirth, mEmail, null);
			// 회원가입
			int result = mDao.joinMember(member);
			if(result == MemberDao.SUCCESS) {
				HttpSession session = request.getSession(); // 세션은 request로 부터
				session.setAttribute("mId", mId);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			}else {
				request.setAttribute("joinErrorMsg", "정보가 너무 길어 회원가입이 실패되었습니다");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
