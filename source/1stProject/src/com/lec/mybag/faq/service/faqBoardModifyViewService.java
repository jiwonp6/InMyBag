package com.lec.mybag.faq.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.FaqBoardDao;
import com.lec.mybag.dto.FaqBoardDto;
import com.lec.mybag.member.service.Service;

public class faqBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		FaqBoardDao fDao = FaqBoardDao.getInstance();
		FaqBoardDto faqboard = fDao.modifyView_replyView(fId);
		request.setAttribute("faqboard", faqboard);
	}

}
