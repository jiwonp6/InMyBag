package com.lec.mybag.qna.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.mybag.dao.QnaBoardDao;
import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class QnaBoardReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("itemBoardFileUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String qFilename = "";
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			qFilename = mRequest.getFilesystemName(param);
			// mId, qTitle, qContent,  filename, qIp
			HttpSession httpSession = request.getSession();
			String aId = ((AdminDto)httpSession.getAttribute("admin")).getaId();
			String qTitle = mRequest.getParameter("iTitle");
			String qContent = mRequest.getParameter("iContent");
			String qIp = request.getRemoteAddr();
			int qGroup = Integer.parseInt(mRequest.getParameter("qGroup"));
			int qStep = Integer.parseInt(mRequest.getParameter("qStep"));
			int qIndent = Integer.parseInt(mRequest.getParameter("qIndent"));
			QnaBoardDao qDao = QnaBoardDao.getInstance();
			int result = qDao.replyQnaBoard(aId, qTitle, qContent, qFilename, qIp, qGroup, qStep, qIndent);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == QnaBoardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("qnaboaredResult", "답글쓰기 성공");
			}else {
				request.setAttribute("qnaboaredResult", "답글쓰기 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("qnaboaredResult", "답글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		if(qFilename!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+qFilename);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\webPro\\source\\08_1stProject\\1stProject\\WebContent\\qnaBoardFileUp\\"+qFilename);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} // try
		}// 파일 복사 if
	}

}
