package com.lec.mybag.faq.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.FaqBoardDao;
import com.lec.mybag.dto.FaqBoardDto;
import com.lec.mybag.service.Service;

public class FaqBoardListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FaqBoardDao fDao = FaqBoardDao.getInstance();
		ArrayList<FaqBoardDto> faqboardList = fDao.fList();
		request.setAttribute("faqboardList", faqboardList);
		int ftotCnt = fDao.getFaqBoardTotCnt();
		request.setAttribute("ftotCnt", ftotCnt);
	}

}
