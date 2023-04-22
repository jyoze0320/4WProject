package controller.adminservice;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Service;

// 작성자 : 손영석
public class AdminProductWriteFormService implements Service {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "view/admin/products/productWrite.jsp";
    String kindList[] = { "1", "2", "3", "4", "5" };    
    request.setAttribute("kindList", kindList);
    request.getRequestDispatcher(url).forward(request, response);
  }
}