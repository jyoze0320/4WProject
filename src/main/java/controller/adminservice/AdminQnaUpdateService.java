//작성자 : 손일형
//클래스 : AdminQnaUpdateService
//설명 : 관리자가 고객이 작성한 qna글에 답변을 다는 클래스

package controller.adminservice;
import controller.Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.QnaDAO;
import model.vo.QnaVO;

public class AdminQnaUpdateService implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String url = "4w.do?command=admin_qna_list";

		String qna_qseq = request.getParameter("qna_qseq");
		String qna_rep =request.getParameter("qna_rep");    
		
		QnaVO qnaVO = new QnaVO();
		qnaVO.setQna_qseq(Integer.parseInt(qna_qseq));
		qnaVO.setQna_rep(qna_rep);
		QnaDAO qnaDAO = QnaDAO.getDAO();
		qnaDAO.updateQna(qnaVO);
		response.sendRedirect(url);
	}
}// end class
