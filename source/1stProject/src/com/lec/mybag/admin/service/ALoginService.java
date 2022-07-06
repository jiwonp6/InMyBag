package com.lec.mybag.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.member.service.Service;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		AdminDao aDao = AdminDao.getInstance();
		int result = aDao.aloginCheck(aId, aPw);
		System.out.println(result);
		if(result==AdminDao.LOGIN_SUCCESS) { // 로그인 성공
			HttpSession session = request.getSession();
			AdminDto admin = aDao.getAdmin(aId);
			session.setAttribute("admin", admin);
		}else { // 로그인 실패
			request.setAttribute("loginErrorMsg", "아이디와 비밀번호를 확인해주세요");
			System.out.println("55");
		}
	}

}
