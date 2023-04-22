package controller.adminservice;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Service;
import model.dao.ProductsDAO;
import model.vo.ProductsVO;

// 작성자 : 손영석
public class AdminProductListService implements Service {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "view/admin/products/productList.jsp";    
    String key = request.getParameter("key");
    String tpage = request.getParameter("tpage");    
    if(key == null){
    	key=""; 															//전체 상품 조회
    }    
    if(tpage == null){
      tpage = "1"; 															//현재 페이지                    
    }else if( tpage.equals("") ){
       tpage="1";  
    }
    request.setAttribute("key", key);
    request.setAttribute("tpage",tpage);
    
    ProductsDAO productsDAO = ProductsDAO.getDAO();							//객체생성
    ArrayList<ProductsVO> productList=										//productlist로 값 받기								
                productsDAO.listProduct( Integer.parseInt(tpage), key);    
    String paging = productsDAO.pageNumber( Integer.parseInt(tpage), key);
    																		
    request.setAttribute("productList",productList);
    int n = productList.size();   
    request.setAttribute("productListSize",n); 
    request.setAttribute("paging", paging);    
    request.getRequestDispatcher(url).forward(request, response);			//url로 데이터 전달
  }
}