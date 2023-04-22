//작성자 : 손일형
//클래스 : AdminQnaDetailService
//설명 : 관리자가 고객이 작성한 qna글을 db로부터 불러오는 클래스

package controller.adminservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.QnaDAO;
import model.vo.QnaVO;

import controller.Service;

public class AdminQnaDetailService implements Service {
	  @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
  
	    String url = "view/admin/adminqna/adminqnaDetail.jsp";
	    
	    // 작성글의 시퀀스 불러옴
	    String qna_qseq = request.getParameter("qna_qseq");
	    QnaDAO qnaDAO = QnaDAO.getDAO();
	    QnaVO qnaVO = qnaDAO.getQna(Integer.parseInt(qna_qseq));
	    // 시퀀스에 맞는 VO 세팅
	    request.setAttribute("qnaVO", qnaVO);       
	    // uri로 해당 정보를 전송, 고객의 글을 불러옴
	    request.getRequestDispatcher(url).forward(request, response);
	  }
	}//end class

