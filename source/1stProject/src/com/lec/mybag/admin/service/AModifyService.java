package com.lec.mybag.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.member.service.Service;

public class AModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			String aId = request.getParameter("aId");
			String aPw = "";
			if(request.getParameter("newaPw").equals("")) {
				aPw=request.getParameter("oldaPw");
			}else {
				aPw=request.getParameter("newaPw");
			}
			String aName = request.getParameter("aName");
			AdminDao aDao = AdminDao.getInstance();
			// 관리자정보 수정
			AdminDto admin = new AdminDto(aId, aPw, aName);
			int result = aDao.modifyAdmin(admin);
			if (result == AdminDao.SUCCESS) {// 수정 성공시 세션도 수정
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("admin", admin);
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
