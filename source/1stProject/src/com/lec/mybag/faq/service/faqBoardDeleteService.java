package com.lec.mybag.faq.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.FaqBoardDao;
import com.lec.mybag.service.Service;

public class faqBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		FaqBoardDao fDao = FaqBoardDao.getInstance();
		int result = fDao.deleteFaqBoard(fId);
		if(result == fDao.SUCCESS) {
			request.setAttribute("faqboardResult", "faq글삭제 성공");
		}else {
			request.setAttribute("faqboardResult", "faq글삭제 실패");
		}
	}

}
