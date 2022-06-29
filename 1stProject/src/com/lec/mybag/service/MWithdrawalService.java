package com.lec.mybag.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.ItemBoardDao;
import com.lec.mybag.dao.LikeMyBagDao;
import com.lec.mybag.dao.MemberDao;
import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dao.ReplyMyBagDao;
import com.lec.mybag.dto.MemberDto;


public class MWithdrawalService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("member");
		if(member!=null) {
			String mId = member.getmId();
			QnaBoardDao  qDao = QnaBoardDao.getInstance();
			ItemBoardDao  iDao = ItemBoardDao.getInstance();
			MyBagBoardDao  bDao = MyBagBoardDao.getInstance();
			ReplyMyBagDao  rDao = ReplyMyBagDao.getInstance();
			LikeMyBagDao lDao = LikeMyBagDao.getInstance();
			MemberDao mDao = MemberDao.getInstance();
			qDao.AllDeleteQnaBoard(mId); // 회원탈퇴전 쓴 글 모두 삭제되어야 회원삭제 가능(외래키로 연결)
			iDao.AllDeleteItemBoard(mId);
			bDao.AllDeleteMyBagBoard(mId);
			rDao.AllDeleteReplyMyBag(mId);
			lDao.AllDeleteLikeMyBag(mId);
			int result = mDao.withdrawal(mId);
			if(result == MemberDao.SUCCESS) {
				request.setAttribute("withdrawalResult", "회원탈퇴가 성공되었습니다. 작성하신 모든 글도 다 지워집니다");
			}else {
				request.setAttribute("withdrawalResult", "회원탈퇴가 실패되었습니다");
			}
		}else {
			request.setAttribute("withdrawalResult", "로그인 된 회원이 아닙니다");
		}
		session.invalidate();
	}
}
