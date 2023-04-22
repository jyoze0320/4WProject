package controller.shopping;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.ProductsDAO;
import model.vo.ProductsVO;

//작성자 : 김준영
//상품 세부 정보 페이지 클래스
public class pdDetailService implements Service{
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		ProductsVO productsDetail = null;
		int pdNum = Integer.parseInt(request.getParameter("pdnum"));	//상품 번호 정보
		String url = "view/shopping/pdDetail.jsp";						//상품 세부 정보 페이지
		
		ProductsDAO productsList = ProductsDAO.getDAO();				//DAO 싱글턴 패턴
		productsDetail =  productsList.selectOneDataNum(pdNum);			//해당 상품 정보
		
		request.setAttribute("productsDetail", productsDetail);			//request에 담음
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);							//포워딩
	}	
}
