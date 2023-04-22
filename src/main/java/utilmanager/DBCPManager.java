package utilmanager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * <참고 자료>
 * Apache Commons DBCP Developers guide - https://commons.apache.org/proper/commons-dbcp/guide/jndi-howto.html
 * 수업 교안 MVC2 샘플 소스 - MVC2_ORACLE_Shop
 */

// DBCP 관리 클래스(싱글톤 패턴 적용)
// 작성자 : 이수봉
public class DBCPManager {
	private static Connection conn;		// DBCP 객체
	
	private DBCPManager() {
		// 기본 생성자(private 지정)
	}
	
	// DBCP 객체 반환하는 메소드(연결 상태를 1개 객체로만 유지 - 싱글톤 패턴)
	public static Connection getConnection() {		
		// DBCP 객체가 null인 경우 새로 생성
		if(conn == null) {
			try {
				Context initContext = new InitialContext();								// 초기 Context 객체 생성
				Context envContext = (Context) initContext.lookup("java:/comp/env");	// 해당 이름의 Context 검색하여 참조
				DataSource dataSource = (DataSource) envContext.lookup("jdbc/oracle");	// 해당 이름의 DataSource 검색하여 참조
				conn = dataSource.getConnection();		// DB 연결
			} catch (Exception e) {
				System.out.println("DBCP 오류");		// DB 연동 오류 시 콘솔 출력
				e.printStackTrace();
			}
		}
		return conn;	// DBCP 객체 반환
	}

	// Connection, Statement의 resource 해제 시도하는 메소드
	public static void close(Connection conn, Statement stmt) {
		// Connection이나 Statement 객체가 null이 아닌 경우(close 되지 않은 경우)
		if(conn != null || stmt != null) {
			try {
				stmt.close();		// Statement 리소스 해제
				conn.close();		// Connection 리소스 해제
			} catch (Exception e) {
				System.out.println("resource close 실패 : case 1");	// 리소스 해제 오류 시 콘솔 출력
				e.printStackTrace();
			}
		}
	}
	
	// Connection, Statement, ResultSet의 resource 해제 시도하는 메소드
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		// ResultSet 객체가 null이 아닌 경우(close 되지 않은 경우)
		if(rs != null) {
			try {
				rs.close();			// ResultSet 리소스 해제
				close(conn, stmt);	// Connection, Statement 리소스 해제하는 메소드 호출
			} catch (Exception e) {
				System.out.println("resource close 실패 : case 2");	// 리소스 해제 오류 시 콘솔 출력
				e.printStackTrace();
			}
		}
	}
}
