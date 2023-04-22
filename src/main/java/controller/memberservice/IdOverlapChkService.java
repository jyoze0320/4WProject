package controller.memberservice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.MemberDAO;

// 회원가입 시도 시 입력값을 확인하여 회원가입 여부를 결정하는 서비스 구현 클래스
// 작성자 : 이수봉
public class IdOverlapChkService implements Service {
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();		// 응답에 사용할 PrintWriter 참조
		String inputID = request.getParameter("input");	// ajax로 전송받은 사용자 입력 ID 값
		MemberDAO dao = MemberDAO.getDAO();				// DAO 객체 참조
		
		// ID 중복체크 >> isIdOverlap() 사용 - ID 중복이면 true, 중복 아니면 false 반환
		if(!dao.isIdOverlap(inputID)) {
			writer.print("true");		// ID 사용 가능 전송
		} else {
			writer.print("false");		// ID 사용 불가 전송
		}
	}

}
