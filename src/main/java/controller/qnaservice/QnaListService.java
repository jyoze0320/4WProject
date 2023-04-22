//작성자 : 손일형
//클래스 : QnaListService
//설명 : 고객이 본인이 작성한 qna리스트를 불러옴, 로그인하지 않을경우 로그인페이지로 이동

package controller.qnaservice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;
import model.dao.QnaDAO;
import model.vo.QnaVO;

public class QnaListService implements Service{
	  @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  
		
	    String url = "view/qna/qnaList.jsp";    
	    
	    // loginUser 로그인 세션 받아오기
	    HttpSession session = request.getSession();
	    int loginUser = 0;
	    
	    try {
	    	loginUser = (int) session.getAttribute("loginUser"); 
	    }catch(NullPointerException e) {

	    }
	    
	    
	    System.out.println(loginUser);
	    
	    // if 문 시작
	    //로그인 하지 않았다면, 로그인 페이지로 이동
	    if (loginUser == 0) {
	      url = "4w.do?command=login";
	    } 
	    // loginUser가 null이 아닌경우(로그인중인경우)
	    else {
	      // DAO 객체생성
	      QnaDAO qnaDAO = QnaDAO.getDAO();    
	      
	      // 사용자의 QnaVO 리스트 타입으로 받음
	      ArrayList<QnaVO> qnaList = qnaDAO.listQna(loginUser);
	      
	      // 리스트 타입 받기위한 setAttribute
	      request.setAttribute("qnaList", qnaList);
	    }
	    // 요청된 값을 정해진 url로 넘겨줌
	    request.getRequestDispatcher(url).forward(request, response);
	  }
	}//end class
