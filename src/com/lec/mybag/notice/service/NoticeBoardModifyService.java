package com.lec.mybag.notice.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.NoticeBoardDao;
import com.lec.mybag.member.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("noticeBoardFileUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String nFilename = "", dbFilename = null;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			nFilename = mRequest.getFilesystemName(param);
			dbFilename = mRequest.getParameter("dbFilename");
			if(nFilename==null) {
				nFilename = dbFilename;
			}
			// nId, iTitle, iContent,  filename, iIp
			int nId = Integer.parseInt(mRequest.getParameter("nId"));
			String nTitle = mRequest.getParameter("nTitle");
			String nContent = mRequest.getParameter("nContent");
			String nIp = request.getRemoteAddr();
			NoticeBoardDao nDao = NoticeBoardDao.getInstance();
			int result = nDao.modifyNoticeBoard(nId, nTitle, nContent, nFilename, nIp);
			if(result == NoticeBoardDao.SUCCESS) { 
				request.setAttribute("noticeboardResult", "notice글수정 성공");
			}else {
				request.setAttribute("noticeboardResult", "notice글수정 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("noticeboardResult", "notice글수정 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy (파일 수정을 안 했거나, 예외가 떨어질 경우 복사 안 함)
		if(dbFilename!=null && !nFilename.equals(dbFilename)) { 
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+nFilename);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\pjw\\webPro\\source\\08_1stProject\\1stProject\\WebContent\\noticeBoardFileUp\\"+nFilename);
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
