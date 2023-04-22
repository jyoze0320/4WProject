package controller.shopping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;
import model.dao.OrdersDAO;

//작성자 : 김준영
//상품 세부 정보에서 상품을 장바구니에 담는 클래스
public class basketInService implements Service{
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		HttpSession session = request.getSession();
		int loginUser = (int)session.getAttribute("loginUser");		//로그인한 유저 번호
		int pdNum = Integer.parseInt(request.getParameter("pdnum"));//클릭된 상품 번호
		int chk = 0;												//장바구니와 결재 비교 chk
		
		OrdersDAO orderList = OrdersDAO.getDAO();			//DAO 싱글턴 패턴
		chk = orderList.oderList(loginUser, pdNum, chk);	//결재 내역에 있다면 1, 없다면 0
		if (chk == 0) {										
			chk = 2;
			chk = orderList.oderList(loginUser, pdNum,chk );
		}
		if (chk == 2) {										//장바구니 내역 있다면 3 없다면 2
			orderList.inBasket(loginUser, pdNum);
		}
		// 브라우저에게 네가 돌려받는 데이터는 순수한 텍스트(ajax)
		response.setContentType("text/plain");	//한글 세팅
		request.setCharacterEncoding("UTF-8");	//한글 세팅
		PrintWriter writer = response.getWriter();
	    writer.print(chk);	//ajax에게 return
	    writer.close();
	}

}
