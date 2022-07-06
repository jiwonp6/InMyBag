package com.lec.mybag.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dto.QnaBoardDto;
import com.lec.mybag.member.service.Service;

public class QnaBoardContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qId = Integer.parseInt(request.getParameter("qId"));
		QnaBoardDao qDao = QnaBoardDao.getInstance();
		QnaBoardDto qnaboard = qDao.qContentView(qId);
		request.setAttribute("qnaboard", qnaboard);
	}
}
