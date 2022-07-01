package com.lec.mybag.mybag.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.mybag.dao.MyBagBoardDao;
import com.lec.mybag.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyBagBoardModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("mybagBoardFileUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String bFilename = "", dbFilename = null;
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			bFilename = mRequest.getFilesystemName(param);
			dbFilename = mRequest.getParameter("dbFilename");
			if(bFilename==null) {
				bFilename = dbFilename;
			}
			// mId, bName, bContent,  filename, bIp
			int bId = Integer.parseInt(mRequest.getParameter("bId"));
			String bName = mRequest.getParameter("bName");
			String bContent = mRequest.getParameter("bContent");
			String bIp = request.getRemoteAddr();
			MyBagBoardDao bDao = MyBagBoardDao.getInstance();
			int result = bDao.modifyMyBagBoard(bId, bName, bContent, dbFilename, bIp);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == MyBagBoardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("mybagboardResult", "mybag글수정 성공");
			}else {
				request.setAttribute("mybagboardResult", "mybag글수정 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("mybagboardResult", "mybag글수정 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy (파일 수정을 안 했거나, 예외가 떨어질 경우 복사 안 함)
		if(dbFilename!=null && !bFilename.equals(dbFilename)) { 
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+bFilename);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\webPro\\source\\08_1stProject\\1stProject\\WebContent\\mybagBoardFileUp\\"+bFilename);
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
