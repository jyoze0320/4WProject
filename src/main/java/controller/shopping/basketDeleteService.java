package controller.shopping;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import controller.Service;
import model.dao.OrdersDAO;

//작성자 : 김준영
//장바구니 선택 목록을 삭제하는 클래스
public class basketDeleteService implements Service {
	
	@Override
	public void execute(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException  {
		
		String[] odNum = request.getParameterValues("arrValue");	//배열로 넘어온 선택 목록들
		HttpSession session = request.getSession();
		int loginUser = (int)session.getAttribute("loginUser");	//로그인 유저 확인
		
		OrdersDAO orderList = OrdersDAO.getDAO();	//DAO의 싱글턴 패턴
		for(int i = 0; i < odNum.length; i++) {		//각 index마다 삭제를 반복
			int num = Integer.parseInt(odNum[i]);
			orderList.basketDelete(num);			//장바구니 항목 삭제
		}
		
		//DB에서 JSON으로 받기
		JSONArray jsonList = new JSONArray();
		jsonList = orderList.orderBasketJson(loginUser);
		
		// 브라우저에게 네가 돌려받는 데이터는 순수한 텍스트(ajax)
		response.setContentType("text/plain");	//한글 세팅
		request.setCharacterEncoding("UTF-8");	//한글 세팅
		PrintWriter writer = response.getWriter();	//응답 객체 생성
		String json = jsonList.toJSONString(); //json을 String으로 변환
	    writer.print(json);	//ajax에게 return
	    writer.close();	
	}

}
