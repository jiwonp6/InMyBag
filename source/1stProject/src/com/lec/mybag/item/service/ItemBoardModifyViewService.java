package com.lec.mybag.item.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ItemBoardDao;
import com.lec.mybag.dto.ItemBoardDto;
import com.lec.mybag.service.Service;

public class ItemBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int iId = Integer.parseInt(request.getParameter("iId"));
		ItemBoardDao iDao = ItemBoardDao.getInstance();
		ItemBoardDto itemboard = iDao.modifyView_replyView(iId);
		request.setAttribute("itemboard", itemboard);
	}

}
