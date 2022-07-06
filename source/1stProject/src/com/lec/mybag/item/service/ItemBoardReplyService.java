package com.lec.mybag.item.service;

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

import com.lec.mybag.dao.ItemBoardDao;
import com.lec.mybag.dto.MemberDto;
import com.lec.mybag.member.service.Service;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ItemBoardReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("itemBoardFileUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		String iFilename = "";
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			iFilename = mRequest.getFilesystemName(param);
			// mId, iTitle, iContent,  filename, iIp
			HttpSession httpSession = request.getSession();
			String mId = ((MemberDto)httpSession.getAttribute("member")).getmId();
			String iTitle = mRequest.getParameter("iTitle");
			String iContent = mRequest.getParameter("iContent");
			String iIp = request.getRemoteAddr();
			int iGroup = Integer.parseInt(mRequest.getParameter("iGroup"));
			int iStep = Integer.parseInt(mRequest.getParameter("iStep"));
			int iIndent = Integer.parseInt(mRequest.getParameter("iIndent"));
			ItemBoardDao iDao = ItemBoardDao.getInstance();
			int result = iDao.replyItemBoard(mId, iTitle, iContent, iFilename, iIp, iGroup, iStep, iIndent);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == ItemBoardDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("itemboaredResult", "답글쓰기 성공");
			}else {
				request.setAttribute("itemboaredResult", "답글쓰기 실패");
			}
			// mRequest에서 넘어온 pageNum(mRequest를 사용하면 request의 파라미터들이 다 null이 됨)을 request에 set
			request.setAttribute("pageNum", mRequest.getParameter("pageNum"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("itemboaredResult", "답글쓰기 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		if(iFilename!=null) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				File serverFile = new File(path+"/"+iFilename);
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:\\webPro\\source\\08_1stProject\\personal\\WebContent\\itemBoardFileUp\\"+iFilename);
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
