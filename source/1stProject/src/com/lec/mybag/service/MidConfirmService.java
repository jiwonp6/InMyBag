package com.lec.mybag.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.MemberDao;

public class MidConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		MemberDao mDao = MemberDao.getInstance();
		//mId중복체크
		int result = mDao.mIdConfirm(mId);
		if(result == MemberDao.NONEXISTENT) {
			request.setAttribute("idConfirmResult", "사용 가능한 ID");
		}else {
			request.setAttribute("idConfirmResult", "<b>중복된 ID</b>");
		}
	}
}
