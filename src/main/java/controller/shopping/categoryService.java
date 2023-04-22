package controller.shopping;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.ProductsDAO;
import model.vo.ProductsVO;

//작성자 : 김준영
//상품의 카테고리로 분류된 상품 목록 클래스
public class categoryService implements Service{
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		ArrayList<ProductsVO> productsView = null;
		
		int kind = Integer.parseInt(request.getParameter("kind"));	//카테고리 구분
		String url = "view/shopping/category.jsp";					//카테고리 페이지
		ProductsDAO productsList = ProductsDAO.getDAO();			//DAO 싱글턴 패턴
		productsView = productsList.selectCategory(kind);			//카테고리 별 상품목록

		request.setAttribute("kind", kind);							//페이지 상단 이미지
		request.setAttribute("productsView", productsView);			//상품 목록 return
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);//이동 준비
		dispatcher.forward(request, response);						//포워딩

	}
}
