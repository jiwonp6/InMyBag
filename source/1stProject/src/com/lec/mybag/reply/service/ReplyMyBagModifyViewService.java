package com.lec.mybag.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.ReplyMyBagDto;
import com.lec.mybag.service.Service;

public class ReplyMyBagModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		ReplyMyBagDto replymybag = rDao.modifyView_replyView(rId);
		request.setAttribute("replymybag", replymybag);
	}

}
