package com.lec.mybag.notice.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.NoticeBoardDao;
import com.lec.mybag.dto.NoticeBoardDto;
import com.lec.mybag.member.service.Service;

public class NoticeBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int nId = Integer.parseInt(request.getParameter("nId"));
		NoticeBoardDao nDao = NoticeBoardDao.getInstance();
		NoticeBoardDto noticeboard = nDao.modifyView_replyView(nId);
		request.setAttribute("noticeboard", noticeboard);
	}

}
