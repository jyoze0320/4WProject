package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.vo.OrdersVO;
import utilmanager.DBCPManager;


// pj_4w.orders 테이블에 접근하는 DAO 클래스(인터페이스 구현)
//작성자 : 김준영
public class OrdersDAO implements DAOInterface<OrdersVO>{
	
	private static OrdersDAO daoInstance = new OrdersDAO();	// DAO 객체	
	public static OrdersDAO getDAO() {
		return daoInstance;
	}
	
	private OrdersDAO() {
		// 기본 생성자(private 지정)
	}
	
	
	@Override
	// insert 실행 메소드 구현
	public void dataInsert(OrdersVO vo) {
		
		
	}

	@Override
	// update 실행 메소드 구현
	public void dataUpdate(OrdersVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// delete 실행 메소드 구현
	public void dataDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<OrdersVO> selectAllData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//장바구니에 담는 메서드
	public void inBasket(int loginUser, int pdNum) {
		//1:장바구니 2:결제, n:보이기 y:안보이게
	    String sql = " insert into orders(order_num, mem_num, pd_num, basket, order_hide)"
	    		+ " values(ORDER_SEQ.nextval, ?, ?, 1,'n')";
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, loginUser);	//1번째 ? 값, 로그인 유저
	      pstmt.setInt(2, pdNum);		//2번째 ? 값, 상품 번호
	      pstmt.executeUpdate();		//insert, update, delete는 executeUpdate
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	}
	
	//장바구니에서 삭제하는 메서드
	public void basketDelete(int orNum) {
		//1:장바구니 2:결제, n:보이기 y:안보이게 >> 삭제 안하고 안보이게 update함
	    String sql = "UPDATE orders SET order_hide = 'y' where order_num = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, orNum);				//1번째 ? 값, 주문 번호
	      pstmt.executeUpdate();				//insert, update, delete는 executeUpdate
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	}
	
	//장바구니에서 결제하는 메서드
	public void paymentUpdate(int odNum,String chkRd) {
		//1:장바구니 2:결제, n:보이기 y:안보이게
	    String sql = "UPDATE orders SET basket= 2, payment=?, order_date=sysdate where order_num = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setString(1, chkRd);			//1번째 ? 값, 결제 방식
	      pstmt.setInt(2, odNum);				//2번째 ? 값, 주문 번호
	      pstmt.executeUpdate();				//insert, update, delete는 executeUpdate
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	}
	
	//장바구니와 결제내역에 데이터가 중복되는지 확인하는 메서드
	public int oderList(int loginUser, int pdNum, int chk) {
		//1:장바구니 2:결제, n:보이기 y:안보이게
		String sql = null;
		//장바구니와 결제 내역에 상품이 담겨있는지 확인하는 SQL문
		if (chk == 0) {
			sql = "select * from orders where mem_num = ? and pd_num = ? "
					+ "and order_hide = 'n' and basket = 2 ";
		} else if (chk == 2) {
			sql = "select * from orders where mem_num = ? and pd_num = ? "
					+ "and order_hide = 'n' and basket = 1 ";
		}
	    
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, loginUser);			//1번째 ? 값, 로그인 유저
	      pstmt.setInt(2, pdNum);				//2번째 ? 값, 상품 번호
	      rs = pstmt.executeQuery();			//select는 executeQuery
	      while (rs.next()) {					//장바구니와 결제 내역에 상품이 담겨있는지 확인 chk
	    	  chk++;							//없으면 통과, 있다면 ++
	      }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return chk;
	}
	
	//장바구니 목록 불러오는 메서드(ajax-json 방식)
	public JSONArray orderBasketJson(int loginUser) {
		//ajax-json 방식을 쓰기 때문에 JsonArray을 이용하여 select함
		JSONArray jsonList = new JSONArray();
		//장바구니 목록 SQL문
	    String sql = "select order_num,pd_num,pd_content,pd_name, sell_price, "
	    		+ "pd_image from opv where order_hide = 'n' and mem_num = ? and basket = 1 "
	    		+ "order by order_num asc";
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, loginUser);			//1번째 ? 값, 로그인 유저
	      rs = pstmt.executeQuery();			//select는 executeQuery
	      while (rs.next()) {
	    	  JSONObject json = new JSONObject();				//JSONObject 생성
	    	  json.put("order_num",rs.getInt("order_num"));		//각 키마다 put 해줌
	    	  json.put("pd_num",rs.getInt("pd_num"));
	    	  json.put("pd_content",rs.getString("pd_content"));
	    	  json.put("pd_name",rs.getString("pd_name"));
	    	  json.put("sell_price",rs.getInt("sell_price"));
	    	  json.put("pd_image",rs.getString("pd_image"));
	    	  jsonList.add(json);								//JSONArray에 담음
	      }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return jsonList;
	}
	
	//장바구니 목록 불러오는 메서드(form-submit 방식)
	public ArrayList<HashMap<String, Object>> orderBasket(int loginUser) {
		//상품 테이블과 주문 테이블을 join한 VIEW를 쓰기 때문에 HashMap을 이용하여 select함
		ArrayList<HashMap<String, Object>> orderList = new ArrayList<HashMap<String, Object>>();
		//장바구니 목록 SQL문
	    String sql = "select order_num,pd_num,pd_content,pd_name, sell_price, "
	    		+ "pd_image from opv where order_hide = 'n' and mem_num = ? and basket = 1 "
	    		+ "order by order_num asc";
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, loginUser);			//1번째 ? 값, 로그인 유저
	      rs = pstmt.executeQuery();			//select는 executeQuery
	      while (rs.next()) {
	    	  //바깥은 ArrayList로 index를 형성하고 안에는 HashMap으로 두 VO객체들을 담음
	    	  HashMap<String, Object> map = new HashMap<String, Object>();
	    	  map.put("order_num",rs.getInt("order_num"));
	    	  map.put("pd_num",rs.getInt("pd_num"));
	    	  map.put("pd_content",rs.getString("pd_content"));
	    	  map.put("pd_name",rs.getString("pd_name"));
	    	  map.put("sell_price",rs.getInt("sell_price"));
	    	  map.put("pd_image",rs.getString("pd_image"));
	    	  orderList.add(map);				//ArrayList로 index에 순서대로 HashMap 담음
	      }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return orderList;
	}
	
	//결제 내역 목록 불러오는 메서드
	public ArrayList<HashMap<String, Object>> paymentList(int loginUser) {
		//상품 테이블과 주문 테이블을 join한 VIEW를 쓰기 때문에 HashMap을 이용하여 select함
		ArrayList<HashMap<String, Object>> orderList = new ArrayList<HashMap<String, Object>>();
		//결제 내역 목록 SQL문
		String sql = "select order_num,pd_num,pd_content,pd_name, sell_price, "
	    		+ "pd_image ,payment,order_email, to_char(order_date,'YY-MM-DD') order_date "
	    		+ "from opv where order_hide = 'n' and mem_num = ? and basket = 2 "
	    		+ "order by order_num asc";
	    Connection conn = null;
	    PreparedStatement pstmt = null; 
	    ResultSet rs = null; 
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, loginUser);			//1번째 ? 값, 로그인 유저
	      rs = pstmt.executeQuery();			//select는 executeQuery
	      while (rs.next()) {	  
	    	  //바깥은 ArrayList로 index를 형성하고 안에는 HashMap으로 두 VO객체들을 담음
	    	  HashMap<String, Object> map = new HashMap<String, Object>();
	    	  map.put("order_num",rs.getInt("order_num"));
	    	  map.put("pd_num",rs.getInt("pd_num"));
	    	  map.put("pd_content",rs.getString("pd_content"));
	    	  map.put("pd_name",rs.getString("pd_name"));
	    	  map.put("sell_price",rs.getInt("sell_price"));
	    	  map.put("pd_image",rs.getString("pd_image"));
	    	  map.put("payment",rs.getString("payment"));
	    	  map.put("order_email",rs.getString("order_email"));
	    	  map.put("order_date",rs.getString("order_date"));
	    	  orderList.add(map);				//ArrayList로 index에 순서대로 HashMap 담음
	      }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return orderList;
	}
}
