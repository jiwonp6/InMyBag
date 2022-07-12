package com.lec.mybag.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.AdminDao;
import com.lec.mybag.dao.FaqBoardDao;
import com.lec.mybag.dao.NoticeBoardDao;
import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.member.service.Service;

public class AWithdrawalService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AdminDto admin = (AdminDto)session.getAttribute("admin");
		String aId = "";
		if(admin!=null) {
			if(admin.getaKing() == AdminDao.KING) {
				aId = request.getParameter("aId");
			}else {
				aId = admin.getaId();
			}
			QnaBoardDao  qDao = QnaBoardDao.getInstance();
			NoticeBoardDao nDao = NoticeBoardDao.getInstance();
			FaqBoardDao fDao = FaqBoardDao.getInstance();
			AdminDao aDao = AdminDao.getInstance();
			qDao.AllDeleteQnaBoard2(aId); // 관리자탈퇴전 쓴 글 모두 삭제되어야 관리자삭제 가능(외래키로 연결)
			nDao.AllDeleteNoticeBoard(aId);
			fDao.AllDeleteFaqBoard(aId);
			int result = aDao.withdrawal(aId);
			if(result == AdminDao.SUCCESS) {
				request.setAttribute("withdrawalResult", "관리자탈퇴가 성공되었습니다");
			}else {
				request.setAttribute("withdrawalResult", "관리자탈퇴가 실패되었습니다");
			}
		}else {
			request.setAttribute("withdrawalResult", "로그인 된 관리자가 아닙니다");
		}
		if(admin.getaKing() != AdminDao.KING) {
			session.invalidate();
		}
	}

}
