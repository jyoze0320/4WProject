package controller.adminservice;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Service;

// 작성자 : 손영석
public class AdminLogoutService implements Service {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
    String url="4w.do?command=admin_login_form";						//로그인 페이지로 이동
    
    HttpSession session=request.getSession(false);
    if(session!=null){
      session.invalidate();
      request.setAttribute("message", "");
    }        
    request.getRequestDispatcher(url).forward(request, response);  
  }
}