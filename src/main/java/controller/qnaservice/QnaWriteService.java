//작성자 : 손일형
//클래스 : QnaWriteService
//설명 : qna를 작성해 db로 전송해주는 클래스

package controller.qnaservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;
import model.dao.QnaDAO;
import model.vo.QnaVO;

public class QnaWriteService  implements Service {
	 @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  
	    String url = "4w.do?command=qna_list";
	    
	    HttpSession session = request.getSession();
	    int loginUser = 0;
	    
	    try {
	    	loginUser = (int) session.getAttribute("loginUser"); 
	    }catch(NullPointerException e) {

	    }   
	    
	    if (loginUser == 0) {
	      url = "4w.do?command=login";
	    }
	    else{      
	      QnaVO qnaVO = new QnaVO();
	      //로그인 확인되면 제목과 내용을 작성할수있도록 준비
	      qnaVO.setQna_title(request.getParameter("qna_title"));
	      qnaVO.setQna_cnt(request.getParameter("qna_cnt"));      
	      QnaDAO qnaDAO = QnaDAO.getDAO();
	      qnaVO.setMem_num(loginUser);
	      // 제목, 내용, 사용자 정보를 insert
	      qnaDAO.insertqna(qnaVO);      
	    }    
	    response.sendRedirect(url);
	  }
	}//end class