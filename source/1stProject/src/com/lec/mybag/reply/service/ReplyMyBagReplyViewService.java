package com.lec.mybag.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.ReplyMyBagDto;
import com.lec.mybag.service.Service;

public class ReplyMyBagReplyViewService implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("답변쓰기 폼 내놔라");
		int rId = Integer.parseInt(request.getParameter("rId"));
		System.out.println(rId);
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		ReplyMyBagDto originReplyMyBag = rDao.modifyView_replyView(rId);
		request.setAttribute("originReplyMyBag", originReplyMyBag);
	}

}
