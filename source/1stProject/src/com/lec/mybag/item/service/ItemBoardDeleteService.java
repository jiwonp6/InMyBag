package com.lec.mybag.item.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ItemBoardDao;
import com.lec.mybag.service.Service;

public class ItemBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int iId = Integer.parseInt(request.getParameter("iId"));
		ItemBoardDao iDao = ItemBoardDao.getInstance();
		int result = iDao.deleteItemBoard(iId);
		if(result == iDao.SUCCESS) {
			request.setAttribute("itemboardResult", "item글삭제 성공");
		}else {
			request.setAttribute("itemboardResult", "item글삭제 실패");
		}
	}

}
