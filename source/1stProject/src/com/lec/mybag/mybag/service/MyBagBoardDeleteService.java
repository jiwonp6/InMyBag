package com.lec.mybag.mybag.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.service.Service;

public class MyBagBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bId = Integer.parseInt(request.getParameter("bId"));
		MyBagBoardDao bDao = MyBagBoardDao.getInstance();
		int result = bDao.deleteMyBagBoard(bId);
		if(result == bDao.SUCCESS) {
			request.setAttribute("mybagboardResult", "mybag글삭제 성공");
		}else {
			request.setAttribute("mybagboardResult", "mybag글삭제 실패");
		}
	}

}
