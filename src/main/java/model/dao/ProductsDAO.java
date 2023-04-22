package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.vo.ProductsVO;
import utilmanager.DBCPManager;

// pj_4w.products 테이블에 접근하는 DAO 클래스(인터페이스 구현)
//작성자 : 김준영
public class ProductsDAO implements DAOInterface<ProductsVO>{
	
	private static ProductsDAO daoInstance = new ProductsDAO();	// DAO 객체	
	public static ProductsDAO getDAO() {
		return daoInstance;
	}
	private ProductsDAO() {
		// 기본 생성자(private 지정)ProductsVO
	}
	
	@Override
	// insert 실행 메소드 구현
	public void dataInsert(ProductsVO vo) {
		// TODO Auto-generated method stub
	}

	@Override
	// update 실행 메소드 구현
	public void dataUpdate(ProductsVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// delete 실행 메소드 구현
	public void dataDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//작성자 : 김준영
	//상품 전체 목록을 불러오는 메서드
	public ArrayList<ProductsVO> selectAllData() {
		ArrayList<ProductsVO> productList = new ArrayList<ProductsVO>();
		//상품 전체 목록을 불러오는 SQL문
	    String sql = "select pd_num, pd_image from (select * from products where sellyn = 'y' "
	    		+ "order by dbms_random.value)";
	  
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;    
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      rs = pstmt.executeQuery();			//select는 executeQuery
	      while (rs.next()) {					//상품 전체 목록 불러옴
	    	  	ProductsVO products = new ProductsVO();
	    	  	products.setPd_num(rs.getInt("pd_num"));
	    	  	products.setPd_image(rs.getString("pd_image"));
		        productList.add(products);		//ArrayList에 담음
	      }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    }
	    return productList;
	}
	
	//작성자 : 김준영
	//상품 상세 정보 불러오는 메서드
	public ProductsVO selectOneDataNum(int pd_num) {
		ProductsVO pdDetail = new ProductsVO();
		//상품의 상세 정보를 불러오는 SQL문
		String sql = "select * from products where pd_num = ? ";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;    
	    try {
	        conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	        pstmt = conn.prepareStatement(sql);	//JDBC
	        pstmt.setInt(1, pd_num);			//1번째 ? 값, 로그인 유저
	        rs = pstmt.executeQuery();			//select는 executeQuery
	        rs.next();							//첫 행(상품 상세 정보) 불러옴
	    	  	pdDetail.setPd_num(rs.getInt("PD_NUM"));
	    	  	pdDetail.setPd_name(rs.getString("PD_NAME"));  
	    	  	pdDetail.setSell_price(rs.getInt("SELL_PRICE"));
	    	  	pdDetail.setPd_content(rs.getString("PD_CONTENT"));
	    	  	pdDetail.setPd_image(rs.getString("PD_IMAGE"));
	    	  	pdDetail.setPd_totalsell(rs.getInt("pd_totalsell"));
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return pdDetail;
	}
	
	//작성자 : 김준영
	//상품 카테고리 목록을 불러오는 메서드
	public ArrayList<ProductsVO> selectCategory(int kind) {
		ArrayList<ProductsVO> productList = new ArrayList<ProductsVO>();
		//상품 카테고리 목록을 불러오는 SQL문
	    String sql = "select pd_num, pd_name, pd_image from products where kind = ? and sellyn = 'y' order by pd_num desc";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;    
	    try {
	      conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	      pstmt = conn.prepareStatement(sql);	//JDBC
	      pstmt.setInt(1, kind);				//1번째 ? 값, 카테고리 종류
	      rs = pstmt.executeQuery();			//select는 executeQuery
	      while (rs.next()) {					//카테고리 전체 목록 불러옴
	    	  	ProductsVO products = new ProductsVO();
	    	  	products.setPd_num(rs.getInt("pd_num"));
	    	  	products.setPd_name(rs.getString("pd_name"));
	    	  	products.setPd_image(rs.getString("pd_image"));
	        productList.add(products);			//ArrayList에 담음
	      }
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return productList;
	}
	
	//작성자 : 김준영
	//상품 결제시 주문한 상품 번호를 알아내는 메서드
	public ProductsVO productNum(int orNum) {
		ProductsVO pdNum = new ProductsVO();
		//주문 번호에 대한 상품 번호 SQL문
		String sql = "select pd_num from orders where order_num = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null; 
	    try {
	        conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	        pstmt = conn.prepareStatement(sql);	//JDBC
	        pstmt.setInt(1, orNum);				//1번째 ? 값, 주분 번호
	        rs = pstmt.executeQuery();			//select는 executeQuery
	        rs.next();							//1번 행 불러옴
	        pdNum.setPd_num(rs.getInt("pd_num"));
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	    return pdNum;
	}
	
	//작성자 : 김준영
	//상품 결제시 상품의 판매량을 올리는 메서드
	public void totalProduct(int pdNum) {
		//상품 결제시 상품의 판매량을 올리는 SQL문
		String sql = "update products SET pd_totalsell = (pd_totalsell +1) where pd_num = ?";
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    try {
	        conn = DBCPManager.getConnection();	//DBCP 싱글턴 패턴
	        pstmt = conn.prepareStatement(sql);	//JDBC
	        pstmt.setInt(1, pdNum);				//1번째 ? 값, 상품 번호
	        pstmt.executeUpdate();				//insert, update, delete는 executeUpdate
	    } catch (Exception e) {
	    		e.printStackTrace();
	    } 
	}
	
	
	//작성자 : 손영석
	//등록된 상품 전체 카운트
	public int totalRecord(String product_name) {	  
	    int total_pages = 0;    
	    String sql = "select count(*) from products where pd_name like '%'||?||'%'";
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet pageset = null;    
		try {
			con = DBCPManager.getConnection();
		    pstmt = con.prepareStatement(sql);
		    if (product_name.equals("")){
		    	pstmt.setString(1, "%");
		    } else{
		        pstmt.setString(1, product_name);
		    }
		    pageset = pstmt.executeQuery();
		    if (pageset.next()) {
		        total_pages = pageset.getInt(1);
		        pageset.close();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}  
		return total_pages;
	}
		  
	private int view_rows = 10; 
	private int counts = 10; 

	//상품 페이지 
	public String pageNumber(int tpage, String name) {
	    String str = "";   
	    int total_pages = totalRecord(name); 
	    int page_count = ( total_pages / counts ) + 1;
	    
	    if (total_pages % counts == 0) {
	    	page_count-- ; 
	    }
	    if (tpage < 1) {
	    	tpage = 1; 
	    }
	    int start_page = tpage - (tpage % view_rows) + 1;  
	    System.out.println(start_page);
	    int end_page = start_page + ( counts - 1 ); 
	    if ( end_page > page_count ) { 
	    	end_page = page_count; 
	    }
	    
	    for (int i = start_page; i <= end_page; i++) {
	    	if (i == tpage) {
	    		str += "<font color=red>[" + i + "] </font>";
	    	} else {
	    		str += "<a href='4w.do?command=admin_product_list&tpage=" 
	    				+ i + "&key=" + name + "'> [" + i + "] </a>";
	    	}
	    }
	    return str;
	}
	  
	//상품검색
	public ArrayList<ProductsVO> listProduct(int tpage, String product_name) {
		  
		ArrayList<ProductsVO> productList = new ArrayList<ProductsVO>();
		String str = "select pd_num, pd_name, kind, cost_price, sell_price, sellyn, regdate " +
		        	" from products "
		        	+ " where pd_name like '%'||?||'%' order by pd_num desc";	    
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int absolutepage = 1;
		try {
			con = DBCPManager.getConnection();
		    absolutepage = ( (tpage - 1) * counts ) + 1; 
		    pstmt = con.prepareStatement(str
		    		, ResultSet.TYPE_SCROLL_SENSITIVE
		    		, ResultSet.CONCUR_UPDATABLE); 
		      
		    if (product_name.equals("")){
		    	pstmt.setString(1, "%");
		    } 
		    else{
		        pstmt.setString(1, product_name);
		    }	      
		    rs = pstmt.executeQuery();
		      
		    if (rs.next()) {
		        rs.absolute(absolutepage);
		        int count = 0;
		        while (count < counts) { 
		        	ProductsVO product = new ProductsVO();
		        	product.setPd_num(rs.getInt(1));
		        	product.setPd_name(rs.getString(2));
		        	product.setKind(rs.getString(3));
		        	product.setCost_price(rs.getInt(4));
		        	product.setSell_price(rs.getInt(5));
		        	product.setSellyn(rs.getString(6));
		        	product.setRegdate(rs.getString(7));
		        	productList.add(product);
		        if (rs.isLast()){
		            break;
		        }         
		          rs.next();
		          count++; 
		        }
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    } finally {
		      //DBCPManager.close(con, pstmt, rs);
		    }
		    return productList;
	  }
	  //상품등록
	  public int insertProduct(ProductsVO product) {
		    int result = 0;
		    String sql = "insert into products(pd_num, kind, pd_name, cost_price, sell_price, pd_content, pd_image) " 
		    		+  " values(product_seq.nextval, ?, ?, ?, ?, ?, ?)";
		    Connection con = null;
		    PreparedStatement pstmt = null;	    
		    try {
		     con = DBCPManager.getConnection();
		      pstmt = con.prepareStatement(sql);
		      pstmt.setString(1, product.getKind());      
		      pstmt.setString(2, product.getPd_name());
		      pstmt.setInt(3, product.getCost_price());
		      pstmt.setInt(4, product.getSell_price());
		      pstmt.setString(5, product.getPd_content());
		      pstmt.setString(6, product.getPd_image());
		      result = pstmt.executeUpdate();
		    } catch (Exception e) {
		      System.out.println("추가실패");
		      e.printStackTrace();
		    } finally {
		  //    DBCPManager.close(con, pstmt);
		    }
		    return result;
	  }
}
