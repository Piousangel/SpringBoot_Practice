package spring.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 *                                            web.xml에 등록해 줘야합니다!!!
 */
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터 값들 받기(dir, filename)
		String dir = request.getParameter("dir");
		String filename = request.getParameter("filename");
		
		//dir을 절대경로로 만듭니다.
		//내장객체인 ServletContext application이 필요합니다.
		String path = getServletContext().getRealPath(dir);
		
		//앞서 얻어낸 upload의 절대경로와 파일명을 연결하면 전체경로가 됩니다.
		String fullPath = path + System.getProperty("file.separator") + filename;
		
		//전체경로를 가지고 File객체를 생성합니다.
		File f = new File(fullPath);
		
		//바구니 역할
		byte[] buf = new byte[2048];        //byte[] buf = new byte[(int)f.length()];
		
		//전송할 데이터가 Stream처리될 때 문자셋 지정을 해줘야합니다.
		response.setContentType("application/octet-stream;charset=8859_1");
		
		//다운로드 대화상자 처리
		response.setHeader("Content-Disposition",
				"attachment;filename="+new String(filename.getBytes("utf-8"), "8859_1"));
		
		//전송타입이 이진데이터(binary)
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		if(f.isFile()) {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
			
			//요청한 곳으로 보내기 위해(응답)
			//스트림을 응답객체(response)로 부터 얻어내면 됩니다.
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			int size = -1;
			try {
				//읽어서 보낼겁니다.
				while((size = bis.read(buf)) != -1){
					bos.write(buf, 0, size);   //읽은만큼 쓰려고 합니다.
					bos.flush();               
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(bos != null)
					bos.close();
				if(bis != null)
					bis.close();
			}
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
