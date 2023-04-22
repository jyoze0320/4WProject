//작성자 : 손일형
//클래스 : QnaDeleteService
//설명 : 사용자가 작성한 qna 를 삭제하는 페이지

package controller.qnaservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import controller.Service;
import model.dao.QnaDAO;
import model.vo.QnaVO;


public class QnaDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String url = "4w.do?command=qna_list";
		
		// 요청 파라메터 : qna_qseq
		int qna_qseq = Integer.parseInt(request.getParameter("qna_qseq"));
		
		QnaDAO qnadao = QnaDAO.getDAO();
		QnaVO qnaVO = new QnaVO();
		
		// 받아온 파라메터를 dao의 deleteqna에 넣어줌
		qnadao.deleteqna(qna_qseq);
		request.setAttribute("qna_qesq", qnaVO);
		
		// 작업이 완료되면 다시 url(=qnalist) 로 이동
		response.sendRedirect(url);
		
	    }
	
	}// end class
	
	
