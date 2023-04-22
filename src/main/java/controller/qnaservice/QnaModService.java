//작성자 : 손일형
//클래스 : QnaModService
//설명 : 기존 작성되어있던 qna글을 불러와서 수정할수있도록 하는 클래스

package controller.qnaservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Service;
import model.dao.QnaDAO;
import model.vo.QnaVO;

public class QnaModService  implements Service {
	 @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  
	    String url = "4w.do?command=qna_list";
	    
      
	      QnaVO qnaVO = new QnaVO();
	      
	      // 기존 작성글의 시퀀스를 받아와서 제목과 내용을 다시 불러옴
	      qnaVO.setQna_qseq(Integer.parseInt(request.getParameter("updatecnt")));
	      qnaVO.setQna_title(request.getParameter("qna_title"));
	      qnaVO.setQna_cnt(request.getParameter("qna_cnt")); 
	      
	         
	      QnaDAO qnaDAO = QnaDAO.getDAO();
	      
	      // qna 수정
	      qnaDAO.modqna(qnaVO);    
	      
	      // 수정이 끝나면 qna리스트로 이동
	      response.sendRedirect(url);
	    }    
	}//end class