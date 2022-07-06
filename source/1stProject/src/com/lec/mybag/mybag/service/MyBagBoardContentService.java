package com.lec.mybag.mybag.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.dto.MyBagBoardDto;
import com.lec.mybag.member.service.Service;

public class MyBagBoardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// mybagContent관련
		int bId = Integer.parseInt(request.getParameter("bId"));
		MyBagBoardDao bDao = MyBagBoardDao.getInstance();
		MyBagBoardDto mybagboard = bDao.bContentView(bId);
		request.setAttribute("mybagboard", mybagboard);
	}

}
