package controller.shopping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;
import model.dao.OrdersDAO;

//작성자 : 김준영
//장바구니 목록 페이지 클래스
public class orderService implements Service{

	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		String url = "view/shopping/order.jsp";	//장바구니 페이지
		ArrayList<HashMap<String, Object>> basketList = new ArrayList<HashMap<String, Object>>();
		HttpSession session = request.getSession();
		int loginUser = (int)session.getAttribute("loginUser");	//로그인 유저 확인
		int baskettotal = 0;
		
		OrdersDAO orderList = OrdersDAO.getDAO();			//DAO 싱글턴 패턴
		basketList = orderList.orderBasket(loginUser);		//로그인한 유저의 장바구니 목록 가져옴
		baskettotal = total(basketList);					//장바구니 목록의 총 합계 금액
		
		request.setAttribute("baskettotal", baskettotal);	//총 합계 담음
		request.setAttribute("basketList", basketList);		//장바구니 목록 담음
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);	//이동 준비
		dispatcher.forward(request, response);				//포워딩
	}
	
	//위의 장바구니 목록에서 상품들의 총 합산 금액
	public int total(ArrayList<HashMap<String, Object>> basketList) {
		int odtotal = 0;
		int value = 0;
		HashMap<String, Object> key = null;
		
		for (int i = 0; i < basketList.size(); i++) {	//상품들 만큼 반복
			key = basketList.get(i);					//ArrayList의 index 값 
			//object를 String 후 int로 바꿈
			value = Integer.parseInt(key.get("sell_price").toString()); //index의 상품 금액
			odtotal += value;							//장바구니 금액의 총합
		}
		
		return odtotal;
	}
}
