package controller.adminservice;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Service;
import model.dao.AdminDAO;

// 작성자 : 손영석
public class AdminLoginService implements Service {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
    String url = "4w.do?command=admin_login_form";
    String msg = "";
    String admin_id = request.getParameter("adminId").trim();
    String admin_pw = request.getParameter("adminPw").trim();

    AdminDAO adminDAO = AdminDAO.getDAO();

    int result = adminDAO.adminCheck(admin_id, admin_pw);
    
    if (result == 1) {																// 관리자 로그인 성공
      HttpSession session = request.getSession();
      session.setAttribute("admin_id", admin_id);    
      msg = "로그인성공.";
      url = "view/admin/login.jsp";
    } else if (result == 0) {
      msg = "비밀번호를 확인하세요.";
    } else if (result == -1) {
      msg = "아이디를 확인하세요.";
    }
    request.setAttribute("message", msg);											//로그인성공 메세지
    request.getRequestDispatcher(url).forward(request, response);
  }
}