package com.lec.mybag.qna.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.service.Service;

public class QnaBoardDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int qId = Integer.parseInt(request.getParameter("qId"));
		QnaBoardDao qDao = QnaBoardDao.getInstance();
		int result = qDao.deleteQnaBoard(qId);
		if(result == qDao.SUCCESS) {
			request.setAttribute("qnaboaredResult", "qna글삭제 성공");
		}else {
			request.setAttribute("qnaboaredResult", "qna글삭제 실패");
		}
	}

}
