package com.lec.mybag.admin.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.member.service.Service;

public class AJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			// request을 이용하여 파라미터 받아와서 DB 저장
			String aId = request.getParameter("aId");
			String aPw = request.getParameter("aPw");
			String aName = request.getParameter("aName");
			AdminDao aDao = AdminDao.getInstance();
			AdminDto admin = new AdminDto(aId, aPw, aName, 0);
			// 회원가입
			int result = aDao.joinAdmin(admin);
			if(result == AdminDao.SUCCESS) {
				HttpSession session = request.getSession(); // 세션은 request로 부터
				session.setAttribute("aId", aId);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			}else {
				request.setAttribute("joinErrorMsg", "정보가 너무 길어 회원가입이 실패되었습니다");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
