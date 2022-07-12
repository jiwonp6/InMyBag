package com.lec.mybag.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.member.service.Service;

public class AidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		AdminDao aDao = AdminDao.getInstance();
		//aId중복체크
		int result = aDao.aIdConfirm(aId);
		if(result == AdminDao.NONEXISTENT) {
			request.setAttribute("idConfirmResult", "사용 가능한 ID");
		}else {
			request.setAttribute("idConfirmResult", "<b>중복된 ID</b>");
		}
	}

}
