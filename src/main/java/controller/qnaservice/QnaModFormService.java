//작성자 : 손일형
//클래스 : QnaModFormService
//설명 : qna 수정하기전에 기존글의 내용을 받을수있게 사용자 정보를 전송해주는 클래스

package controller.qnaservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.QnaDAO;
import model.vo.QnaVO;

public class QnaModFormService  implements Service {
	 @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {	 
		 
	    String url = "view/qna/qnaModView.jsp";
	    
	   
	    int qseq = Integer.parseInt(request.getParameter("qna_qseq"));
	    QnaDAO qnaDAO = QnaDAO.getDAO();
	    QnaVO qnaVO = qnaDAO.getQna(qseq);
	    request.setAttribute("qnaVO", qnaVO);
	    
	    request.getRequestDispatcher(url).forward(request, response);
	    
	 }
}

