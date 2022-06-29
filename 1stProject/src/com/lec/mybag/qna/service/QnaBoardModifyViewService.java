package com.lec.mybag.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dto.QnaBoardDto;
import com.lec.mybag.service.Service;

public class QnaBoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qId = Integer.parseInt(request.getParameter("qId"));
		QnaBoardDao qDao = QnaBoardDao.getInstance();
		QnaBoardDto qnaboard = qDao.modifyView_replyView(qId);
		request.setAttribute("qnaboard", qnaboard);
	}

}
