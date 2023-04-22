package controller.shopping;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.OrdersDAO;
import model.dao.ProductsDAO;

//작성자 : 김준영
//장바구니에서 결제로 이동 클래스
public class paymentInService  implements Service{
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		String[] odNum = request.getParameterValues("chkOdNum");	//장바구니에서 체크된 상품들
		String chkRd = request.getParameter("chkRd");				//결제 수단 정보
		
		OrdersDAO orderList = OrdersDAO.getDAO();					//주문 테이블 DAO 싱글턴 패턴
		ProductsDAO pdNum = ProductsDAO.getDAO();					//상품 테이블 DAO 싱글턴 패턴
		
		for(int i = 0; i < odNum.length; i++) {						//체크된 만큼 반복
			int odNu = Integer.parseInt(odNum[i]);
			orderList.paymentUpdate(odNu,chkRd);					//장바구니에서 결제로 update
			pdNum.totalProduct(pdNum.productNum(odNu).getPd_num());	//결제된 상품의 판매량 올림
		}
		
		paymentService a = new paymentService();	//결제 내역 불러옴
		a.execute(request, response);
	

	}

}
