package controller.memberservice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.MemberDAO;
import model.vo.MemberVO;

// 계정정보 확인 페이지로 넘어가는 서비스 구현 클래스
// 작성자 : 이수봉
public class ModMemService implements Service {
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "view/member/modmem.jsp";		// 계정정보 확인 페이지 URL
		int loginUser = (int) request.getSession().getAttribute("loginUser");	// 세션에 저장된 사용자 회원번호 참조
		
		MemberDAO dao = MemberDAO.getDAO();			// DAO 객체 참조
		MemberVO vo = dao.selectByNum(loginUser);	// 해당 사용자의 회원정보를 DB에서 불러오기
		vo.setMem_pw("");							// 사용자 비밀번호 지우기
		request.setAttribute("userInfo", vo);		// 사용자 회원정보를 요청 파라미터에 저장

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);	// 이동할 URL 설정
		dispatcher.forward(request, response); 								// 페이지 이동
	}

}
