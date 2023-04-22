//작성자 : 손일형
//클래스 : QnaWriteFormService
//설명 : qna를 작성하기전 로그인상태인지 확인

package controller.qnaservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;

public class QnaWriteFormService implements Service {
	
	  @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

		  String url = "view/qna/qnaWrite.jsp";
	    
	    HttpSession session = request.getSession();
	    int loginUser = 0;
	    
	    try {
	    	loginUser = (int) session.getAttribute("loginUser"); 
	    }catch(NullPointerException e) {

	    }
	    
	    // 로그인중이 아니라면(null) 로그인 화면으로 보냄
	    if (loginUser == 0) {
	      url = "4w.do?command=login";
	    }     
	    request.getRequestDispatcher(url).forward(request, response);
	  }
	}//end class
	
	

