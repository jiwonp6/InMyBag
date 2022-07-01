package com.lec.mybag.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.service.Service;

public class ReplyMyBagDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int rId = Integer.parseInt(request.getParameter("rId"));
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		int result = rDao.deleteReplyMyBag(rId);
		if(result == rDao.SUCCESS) {
			request.setAttribute("replymybagResult", "replymybag글삭제 성공");
		}else {
			request.setAttribute("replymybagResult", "replymybag글삭제 실패");
		}
	}

}
