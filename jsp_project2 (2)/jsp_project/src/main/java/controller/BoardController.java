package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	private RequestDispatcher rdp;  //�쎒�쓽 紐⑹쟻吏� 二쇱냼濡� 媛앹껜瑜� �쟾�떖�빐二쇰뒗 媛앹껜
	private String destPage; //紐⑹쟻吏� 二쇱냼瑜� ���옣�빐二쇰뒗 蹂��닔
	private int isOk;  //db�쓽 insert, update, delete�쓽 寃곌낵瑜� 諛쏅뒗 蹂��닔
	private BoardService bsv;  //interface
	//파일 경로를 저장할 변수
	private String savePath;
	private final String UTF8 = "utf-8"; //인코딩 설정시

    
    public BoardController() {
     bsv = new BoardServiceImpl(); //interface 援ы쁽泥�
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 罹먮┃�꽣 �씤肄붾뵫 �꽕�젙, 而⑦뀗痢� ���엯 �꽕�젙
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		log.info(">>> uri > "+uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path > "+path);
		
		switch(path) {
		case "register":
			destPage="/board/register.jsp";
			break;
			
		case "insert":
			try {
				//파일을 업로드할 물리적인 경로를 설정
				savePath = getServletContext().getRealPath("/_fileUpload");
				log.info(">>> 저장경로 : "+savePath);
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileitemFactory = new DiskFileItemFactory();
				//파일의 저장위치를 담고있는 객체를 저장
				fileitemFactory.setRepository(fileDir); 
				//파일 저장을 위한 임시 메모리 용량설정 : byte단위
				fileitemFactory.setSizeThreshold(2*1024*1024); 
				
				BoardVO bvo = new BoardVO();
				//multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileitemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString(UTF8)); //인코딩 형식을 담아서 변환
						break;
						
					case "writer":
						bvo.setWriter(item.getString(UTF8));
						break;
						
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
						
					case "image_file":
						//이미지가 있는지 없는지 체크
						if(item.getSize() > 0) { //데이터의 크기를 이용하여 유무 결정
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1); //경로를 포함한 파일 이름
							fileName = System.currentTimeMillis()+"_"+fileName;
							log.info(">>> fimeName : "+fileName);
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("실제 파일 경로 : "+uploadFilePath);
							
							//저장
							
							try {
								item.write(uploadFilePath); //자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								//썸네일 작업 : 리스트 페이지에서 트레픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
								
								
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
						}
						break;
					}
				}
				isOk = bsv.insert(bvo);
				log.info(">>> insert > "+(isOk>0? "OK":"FAIL"));
				
				
				
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content");
//				
//				BoardVO bvo = new BoardVO(title, writer, content);
//				//insert, update, delete => 由ы꽩���엯 isOk
//				//select => BoardVO�쓽 媛앹껜媛� (�뿬�윭媛� 由ы꽩�씠硫� List)
//				isOk = bsv.insert(bvo);
//				log.info(">>> insert > "+(isOk>0? "OK":"FAIL"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="page";
			break;
			
		case "list":
			try {
				List<BoardVO> list = bsv.getList();
				request.setAttribute("list", list);
				log.info(">>> list Ok");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/list.jsp";
			break;

		case "page":
			try {
				int pageNo = 1;
				int qty = 10;
				String type = "";
				String keyword = "";
				if(request.getParameter("type") != null) {
					type = request.getParameter("type");
					keyword = request.getParameter("keyword");
				}
				if(request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				PagingVO pgvo = new PagingVO(pageNo,qty);
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				int totCount = bsv.getTotal(pgvo);
				log.info("�쟾泥� �럹�씠吏� 媛쒖닔 : "+totCount);
				List<BoardVO> list = bsv.getPageList(pgvo); 
				log.info(">>> list[0]"+list.get(0));
				log.info(">>>> list : "+list.size());
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				request.setAttribute("pgh", ph);
				request.setAttribute("list", list);
				log.info("pageList �꽦怨�~!!");
				destPage="/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "my":
			try {
				int pageNo = 1;
				int qty = 10;
				String writer = request.getParameter("writer");
				HttpSession ses = request.getSession();
				request.setAttribute("writer", writer);
				
				String type = "";
				String keyword = "";
				if(request.getParameter("type") != null) {
					type = request.getParameter("type");
					keyword = request.getParameter("keyword");
				}
				if(request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				
				PagingVO pgvo = new PagingVO(pageNo,qty);
				int totCount = bsv.my(pgvo);
				List<BoardVO> list = bsv.getPageList(pgvo); 
				log.info(">>>> list : "+list.size());
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				request.setAttribute("pgh", ph);
				request.setAttribute("list", list);
				destPage="/board/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/detail.jsp";
			break;
		case "modify":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="/board/modify.jsp";
			break;
		case "edit":
				try {
					savePath = getServletContext().getRealPath("/_fileUpload");
					File fileDir = new File(savePath);
					
					DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
					fileItemFactory.setRepository(fileDir);
					fileItemFactory.setSizeThreshold(2*1024*1024);
					
					BoardVO bvo = new BoardVO();
					
					ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
					log.info(">>> update 준비");
					
					List<FileItem> itemList = fileUpload.parseRequest(request);
					String old_file = null;
					for(FileItem item : itemList) {
						switch(item.getFieldName()) {
						
						case "bno":
							bvo.setBno(Integer.parseInt(item.getString(UTF8)));
							break;
						case "title":
							bvo.setTitle(item.getString(UTF8));
							break;
							
						case "writer":
							bvo.setWriter(item.getString(UTF8));
							break;
							
						case "content":
							bvo.setContent(item.getString(UTF8));
							break;
							
						case "image_file":
							old_file = item.getString(UTF8);
							break;
							
						case "new_file":
							if(item.getSize() > 0) { //새로운 파일이 등록됨
								if(old_file !=null) {
								//파일 핸들러 호출(기존 파일 삭제)
								FileHandler fileHandler = new FileHandler();
								isOk = fileHandler.deleteFile(old_file, savePath);
								}
								//이름 설정
								String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1);
								log.info(">>> new_fileName : "+fileName);
								//실제 저장이름
								fileName = System.currentTimeMillis()+"_"+fileName;
								File uploadFilePath = new File(fileDir+File.separator+fileName);
								try {
									item.write(uploadFilePath);
									bvo.setImage_file(fileName);
									log.info(">>> bvo.image_file > "+bvo.getImage_file());
									//썸네일 작업
									Thumbnails.of(uploadFilePath).size(75, 75)
									.toFile(new File(fileDir+File.separator+"th_"+fileName));
									
								} catch (Exception e) {
									// TODO: handle exception
									log.info(">>> file update on disk fail");
									e.printStackTrace();
								}
	
							}else { //새로운 파일을 넣지 않았다면...
								//기존파일을 다시 저장
								bvo.setImage_file(old_file);
							}
							break;
					};
				
				
				
					}
//				String title = request.getParameter("title");
//				String content = request.getParameter("content");
//				int bno = Integer.parseInt(request.getParameter("bno"));
//				BoardVO bvo = new BoardVO(bno, title, content);
				isOk = bsv.update(bvo);
				log.info(">>> insert > "+(isOk>0? "OK":"FAIL"));
					} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
//			destPage="/board/list.jsp";  //鍮� �럹�씠吏�留� �쓣�썙以�
			destPage="page";  //而⑦듃濡ㅻ윭 list�뿉�꽌 db 寃��깋 �썑 媛앹껜 媛�吏�怨� list.jsp濡� �씠�룞
			break;
			
		case "delete":
			try {
				//파일 삭제 추가
				//image_file 호출, savePath
				int bno = Integer.parseInt(request.getParameter("bno"));
				String imageFileName = bsv.getFileName(bno); //삭제할 파일이름 호출
				log.info(">>> removeFileName : "+imageFileName);
				savePath = getServletContext().getRealPath("/_fileUpload");
				FileHandler fh = new FileHandler();
				isOk = fh.deleteFile(imageFileName, savePath);
				log.info(">>> removeFile >>"+(isOk> 0? "ok":"fail"));
				
				isOk = bsv.delete(bno);
				log.info(">>> remove>>"+(isOk> 0? "ok":"fail"));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			destPage="page";
			break;
		
		}
		rdp=request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
