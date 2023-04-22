package controller.index;

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
//index(메인) 페이지로 넘어가는 서비스 구현 클래스
public class IndexService implements Service {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "index.jsp";							//홈페이지 첫 화면,os주소
		ArrayList<ProductsVO> productsMain = null;
		
		ProductsDAO productsAll = ProductsDAO.getDAO(); 	//DAO의 싱글턴 패턴
		productsMain = productsAll.selectAllData();			//전체 상품 목록

		request.setAttribute("productsMain", productsMain);	//request에 목록 담음
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);	// 이동할 URL 설정
		dispatcher.forward(request, response);  			//포워딩
	}
}
