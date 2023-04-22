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
//결제 내역 페이지 클래스
public class paymentService implements Service{
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		ArrayList<HashMap<String, Object>> orderList = new ArrayList<HashMap<String, Object>>();
		HttpSession session = request.getSession();
		int loginUser = (int)session.getAttribute("loginUser");	//로그인 유저 확인
		int ordertotal = 0;
		String url = "view/shopping/payment.jsp";				//결제 내역 페이지
		
		OrdersDAO order = OrdersDAO.getDAO();					//DAO 싱글턴 패턴
		orderList = order.paymentList(loginUser);				//해당 유저의 결제 내역 목록 불러옴
		
		orderService total = new orderService();				//장바구니에서 목록 합계
		ordertotal = total.total(orderList);					//목록 합계 메서드
		
		request.setAttribute("ordertotal", ordertotal);			//목록 합계
		request.setAttribute("orderList", orderList);			//결제 내역
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);					//포워딩
	}
}
