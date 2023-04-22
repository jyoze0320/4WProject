//작성자 : 손일형
//클래스 : QnaViewService
//설명 : qna 리스트에서 제목을 누를경우 작성한 qna를 볼수있게 해주는 클래스

package controller.qnaservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;
import model.dao.QnaDAO;
import model.vo.QnaVO;

public class QnaViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "view/qna/qnaView.jsp";
		
		
		//로그인 중인 사용자의 세션을 받아옴
		HttpSession session = request.getSession();
		int loginUser = 0;
	    
	    try {
	    	loginUser = (int) session.getAttribute("loginUser"); 
	    }catch(NullPointerException e) {

	    }
		
		// 로그인 하지 않았다면, 로그인 페이지로 이동
		if (loginUser == 0) {
			url = "4w.do?command=login";
		} else {
			// jsp파일의 qna_qseq(=시퀀스) 요청
			int qseq = Integer.parseInt(request.getParameter("qna_qseq"));
			QnaDAO qnaDAO = QnaDAO.getDAO();
			QnaVO qnaVO = qnaDAO.getQna(qseq);
			request.setAttribute("qnaVO", qnaVO);
		}
		// 요청된 값을 정해진 url로 넘겨줌
		request.getRequestDispatcher(url).forward(request, response);
	}
}// end class