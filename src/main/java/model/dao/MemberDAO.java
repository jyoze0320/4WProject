package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.MemberVO;
import utilmanager.DBCPManager;

// pj_4w.members 테이블에 접근하는 DAO 클래스(인터페이스 구현)
// 작성자 : 이수봉
public class MemberDAO implements DAOInterface<MemberVO>{

	private Connection conn = DBCPManager.getConnection();	// DBCP 객체 참조
	
	private static MemberDAO daoInstance;	// DAO 객체	
	private MemberDAO() {
		// 기본 생성자(private 지정)
	}
	// DAO 객체 반환하는 메소드(싱글톤 패턴)
	public static MemberDAO getDAO() {
		// DAO 객체 null일 시 객체 생성
		if(daoInstance == null) {
			daoInstance = new MemberDAO();
		}
		return daoInstance;	// DAO 객체 반환
	}
	
	@Override
	// insert 실행 메소드 구현
	public void dataInsert(MemberVO vo) {
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		// 실행할 insert문 작성
		String sql = "insert into pj_4w.MEMBERS(mem_num, mem_id, mem_pw, mem_name, "
					+ "email, address, phone, initdate) "
					+ "values(member_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 insert문 세팅
			
			// PreparedStatement의 각 인수에 값 할당
			pstmt.setString(1, vo.getMem_id());
			pstmt.setString(2, vo.getMem_pw());
			pstmt.setString(3, vo.getMem_name());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getPhone());
			
			pstmt.executeUpdate();	// insert문 실행
		} catch (SQLException e) {
			System.out.println("MEMBERS 테이블 insert 실패");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> dataInsert");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
	}

	@Override
	// update 실행 메소드 구현
	public void dataUpdate(MemberVO vo) {
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		String sql = "";
		boolean hasPW = false;
		// 실행할 update문 작성(비밀번호 수정 안 할 경우 고려)
		if(!vo.getMem_pw().equals("")) {
			hasPW = true;
			sql = "update pj_4w.MEMBERS set mem_pw = ?, mem_name = ?, "
				+ "email = ?, address = ?, phone = ? "
				+ "where mem_id = ?";
		} else {
			hasPW = false;
			sql = "update pj_4w.MEMBERS set mem_name = ?, "
				+ "email = ?, address = ?, phone = ? "
				+ "where mem_id = ?";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 update문 세팅
			
			// PreparedStatement의 각 인수에 값 할당
			if(hasPW) {
				pstmt.setString(1, vo.getMem_pw());
				pstmt.setString(2, vo.getMem_name());
				pstmt.setString(3, vo.getEmail());
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getPhone());
				pstmt.setString(6, vo.getMem_id());
			} else {
				pstmt.setString(1, vo.getMem_name());
				pstmt.setString(2, vo.getEmail());
				pstmt.setString(3, vo.getAddress());
				pstmt.setString(4, vo.getPhone());
				pstmt.setString(5, vo.getMem_id());
			}
			
			pstmt.executeUpdate();	// update문 실행
		} catch (SQLException e) {
			System.out.println("MEMBERS 테이블 update 실패");	// 오류 발생 시 콘솔 출력
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> dataUpdate");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
	}

	@Override
	// delete 실행 메소드 구현
	public void dataDelete(int mem_num) {
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		// 실행할 delete문 작성
		String sql = "delete from pj_4w.MEMBERS where mem_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 delete문 세팅
			pstmt.setInt(1, mem_num);				// PreparedStatement의 인수에 값 할당
			pstmt.executeUpdate();					// delete문 실행
		} catch (SQLException e) {
			System.out.println("MEMBERS 테이블 delete 실패");	// 오류 발생 시 콘솔 출력
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> dataDelete");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
	}

	@Override
	// pj_4w.members의 모든 행을 select 하는 메소드 구현
	public ArrayList<MemberVO> selectAllData() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();	// MemberVO 객체 List
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		ResultSet rs = null;				// ResultSet 객체
		// 실행할 select문 작성
		String sql = "select * from pj_4w.MEMBERS";
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 select문 세팅
			rs = pstmt.executeQuery();				// select문 실행 후 결과를 ResultSet 객체에 저장
			
			while(rs.next()) {		// select한 행이 존재하는 경우 list에 요소(VO 객체) 추가
				// ArrayList에 요소 추가
				list.add(new MemberVO(rs.getInt(1)			// 회원번호
									, rs.getString(2)		// 회원 ID
									, rs.getString(3)		// 회원 PW
									, rs.getString(4)		// 회원 이름
									, rs.getString(5)		// 이메일
									, rs.getString(6)		// 주소
									, rs.getString(7)		// 전화번호
									, rs.getString(8)));	// 가입일
			}
		} catch (SQLException e) {
			System.out.println("MEMBERS 테이블 selectAll 실패");	// 오류 발생 시 콘솔 출력
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> selectAllData");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
		return list;	// ArrayList 반환
	}
	
	// 해당 회원번호가 있는 행을 select하는 메소드 구현
	public MemberVO selectByNum(int mem_num) {
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		ResultSet rs = null;				// ResultSet 객체
		MemberVO vo = null;					// MemberVO 객체
		// 실행할 select문 작성
		String sql = "select * from pj_4w.MEMBERS where mem_num = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 select문 세팅
			pstmt.setInt(1, mem_num);				// PreparedStatement의 인수에 값 할당
			rs = pstmt.executeQuery();				// select문 실행 후 결과를 ResultSet 객체에 저장
			
			if(rs.next()) {		// select 결과 있는 경우 VO 객체 생성하여 값 저장
				// VO 객체 생성
				vo = new MemberVO(rs.getInt(1)		// 회원번호
								, rs.getString(2)	// 회원 ID
								, rs.getString(3)	// 회원 PW
								, rs.getString(4)	// 회원 이름
								, rs.getString(5)	// 이메일
								, rs.getString(6)	// 주소
								, rs.getString(7)	// 전화번호
								, rs.getString(8));	// 가입일
			}
		} catch (SQLException e) {
			System.out.println("MEMBERS 테이블 num 기준 select 실패");	// 오류 발생 시 콘솔 출력
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> selectByNum");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
		return vo;	// VO 객체 반환
	}
	
	// 해당 회원 ID가 있는 행을 select하는 메소드 구현
	public MemberVO selectById(String mem_id) {
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		ResultSet rs = null;				// ResultSet 객체
		MemberVO vo = null;					// MemberVO 객체
		// 실행할 select문 작성
		String sql = "select * from pj_4w.MEMBERS where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 select문 세팅
			pstmt.setString(1, mem_id);				// PreparedStatement의 인수에 값 할당
			rs = pstmt.executeQuery();				// select문 실행 후 결과를 ResultSet 객체에 저장
			
			if(rs.next()) {		// select 결과 있는 경우 VO 객체 생성하여 값 저장
				// VO 객체 생성
				vo = new MemberVO(rs.getInt(1)		// 회원번호
								, rs.getString(2)	// 회원 ID
								, rs.getString(3)	// 회원 PW
								, rs.getString(4)	// 회원 이름
								, rs.getString(5)	// 이메일
								, rs.getString(6)	// 주소
								, rs.getString(7)	// 전화번호
								, rs.getString(8));	// 가입일
			}
		} catch (SQLException e) {
			System.out.println("MEMBERS 테이블 id 기준 select 실패");	// 오류 발생 시 콘솔 출력
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> selectById");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
		return vo;	// VO 객체 반환
	}
	
	// 사용자 입력 ID가 DB에 있는 ID와 중복되는지 여부를 확인하는 메소드(회원가입 시 사용)
	public boolean isIdOverlap(String mem_id) {
		PreparedStatement pstmt = null;		// PreparedStatement 객체
		ResultSet rs = null;				// ResultSet 객체
		boolean isOverlap = false;			// ID 중복 여부 체크(기본값:false)
		// 실행할 select문 작성
		String sql = "select * from pj_4w.ID_LIST where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);		// 실행할 select문 세팅
			pstmt.setString(1, mem_id);				// PreparedStatement의 인수에 값 할당
			rs = pstmt.executeQuery();				// select문 실행 후 결과를 ResultSet 객체에 저장
			
			if(rs.next()) {		
				isOverlap = true;	// select 결과 있는 경우 불리언을 true로 변경
			}
		} catch (SQLException e) {
			System.out.println("ID_LIST 뷰 select 실패");	// 오류 발생 시 콘솔 출력
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("unknown exception : MemberDAO >> isIdOverlap");	// 오류 발생 시 콘솔 출력
	    	e.printStackTrace();
		}
		return isOverlap;	// 불리언 반환
	}
	
	//손영석 작성
	public int totalRecord(String member_name) {	  
	    int total_pages = 0;    
	    String sql = "select count(*) from members where mem_name like '%'||?||'%'";
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet pageset = null;    
	    try {
	      con = DBCPManager.getConnection();
	      pstmt = con.prepareStatement(sql);
	      if (member_name.equals("")){
	        pstmt.setString(1, "%");
	      } else{
	        pstmt.setString(1, member_name);
	      }
	      pageset = pstmt.executeQuery();
	      if (pageset.next()) {
	        total_pages = pageset.getInt(1);
	        pageset.close();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      //DBCPManager.close(con, pstmt);
	    }  
	    return total_pages;
	  }
	  
		private int view_rows = 10; 
		private int counts = 10; 
	  
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
		        str += "<a href='4w.do?command=admin_member_list&tpage=" 
		           + i + "&key=" + name + "'> [" + i + "] </a>";
		      }
		    }
		     return str;
		  }
		  
		  
		  public ArrayList<MemberVO> listmember(int tpage, String member_name) {
			  
			    ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			    String str = "select * from pj_4w.members " 
			        + " where mem_name like '%'||?||'%' order by mem_num desc";	    
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
			      
			      if (member_name.equals("")){
			        pstmt.setString(1, "%");
			      } 
			      else{
			        pstmt.setString(1, member_name);
			      }	      
			      rs = pstmt.executeQuery();
			      
			      if (rs.next()) {
			        rs.absolute(absolutepage);
			        int count = 0;
			        while (count < counts) { 
			        	MemberVO member = new MemberVO();
			        	
			        	member.setMem_id(rs.getString(2));
			        	member.setMem_name(rs.getString(4));
			        	member.setEmail(rs.getString(5));
			        	member.setAddress(rs.getString(6));
			        	member.setPhone(rs.getString(7));
			        	member.setInitDate(rs.getString(8));
			          list.add(member);
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
			    return list;
		  }

}
