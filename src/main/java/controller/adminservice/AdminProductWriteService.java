package controller.adminservice;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.Service;
import model.dao.ProductsDAO;
import model.vo.ProductsVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 작성자 : 손영석
public class AdminProductWriteService implements Service {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
	  
    String url = "4w.do?command=admin_product_list";    
    HttpSession session = request.getSession();
    
    int sizeLimit = 5 * 1024 * 1024;
    String savePath = "image/products";
    ServletContext context = session.getServletContext();
    String uploadFilePath = context.getRealPath(savePath);
    System.out.println("url:" + uploadFilePath);
    MultipartRequest multi = new MultipartRequest(request, 					//객체생성
        uploadFilePath, 													//업로드될 파일이 저장될 파일 경로명
        sizeLimit, 														
        "UTF-8", 															//인코딩 타입 
        new DefaultFileRenamePolicy() 										
    ); 
    
    ProductsVO productVO = new ProductsVO();								//객체생성
    productVO.setKind(multi.getParameter("pd_kind"));						//입력값저장
    productVO.setPd_name(multi.getParameter("pd_name"));
    productVO.setCost_price(Integer.parseInt(multi.getParameter("cost_price")));
    productVO.setSell_price(Integer.parseInt(multi.getParameter("sell_price")));


    productVO.setPd_content(multi.getParameter("pd_content"));
    productVO.setPd_image(multi.getFilesystemName("pd_image"));
    
    ProductsDAO productDAO = ProductsDAO.getDAO();
    productDAO.insertProduct(productVO);									//새로운 정보 DB 입력			
    
    response.sendRedirect(url);
  }
}