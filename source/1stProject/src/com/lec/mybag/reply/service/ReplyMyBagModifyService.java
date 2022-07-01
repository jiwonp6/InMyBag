package com.lec.mybag.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.service.Service;

public class ReplyMyBagModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int rId = Integer.parseInt(request.getParameter("rId"));
		String rContent = request.getParameter("rContent");
		String rIp = request.getRemoteAddr();
		ReplyMyBagDao rDao = ReplyMyBagDao.getInstance();
		int result = rDao.modifyReplyMyBag(rId, rContent, rIp);
		// joinMember결과에 따라 적절히 request.setAttribute
		if (result == ReplyMyBagDao.SUCCESS) { // 회원가입 진행
			request.setAttribute("replymybagResult", "replymybag글수정 성공");
		} else {
			request.setAttribute("replymybagResult", "replymybag글수정 실패");
		}
		// request에서 넘어온 rpageNum
		request.setAttribute("rpageNum", request.getParameter("rpageNum"));
	}

}
