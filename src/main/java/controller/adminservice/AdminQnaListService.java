//작성자 : 손일형
//클래스 : AdminQnaListService
//설명 : 관리자가 고객이 작성한 qna글의 리스트를 보는 클래스

package controller.adminservice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.QnaDAO;
import model.vo.QnaVO;

import controller.Service;

public class AdminQnaListService implements Service {
	
	  @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    String url = "view/admin/adminqna/adminqnaList.jsp";
	    
	    QnaDAO qnaDAO = QnaDAO.getDAO();
	    //리스트 형식으로 VO 불러옴
	    ArrayList<QnaVO> qnaList = qnaDAO.listAllQna();
	    request.setAttribute("qnaList", qnaList);
	    request.getRequestDispatcher(url).forward(request, response);
	  }
	}//end class
