package com.lec.mybag.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.NoticeBoardDao;
import com.lec.mybag.member.service.Service;

public class NoticeBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nId = Integer.parseInt(request.getParameter("nId"));
		NoticeBoardDao nDao = NoticeBoardDao.getInstance();
		int result = nDao.deleteNoticeBoard(nId);
		if(result == nDao.SUCCESS) {
			request.setAttribute("noticeboardResult", "notice글삭제 성공");
		}else {
			request.setAttribute("noticeboardResult", "notice글삭제 실패");
		}
	}

}
