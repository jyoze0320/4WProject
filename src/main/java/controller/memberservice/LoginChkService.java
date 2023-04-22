package controller.memberservice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;
import model.dao.MemberDAO;
import model.vo.MemberVO;

// 로그인 시도 시 입력값을 확인하여 로그인 여부를 결정하는 서비스 구현 클래스
// 작성자 : 이수봉
public class LoginChkService implements Service{
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		// 세션 가져오기
		String url = "4w.do?command=login";				// 로그인 페이지 URL(로그인 실패 시 이동)
		String id = request.getParameter("userID");		// ID 입력값 가져오기
		String pw = request.getParameter("userPW");		// PW 입력값 가져오기
		MemberDAO dao = MemberDAO.getDAO();				// MemberDAO 객체 가져오기
		MemberVO vo = dao.selectById(id);				// 일치하는 ID를 가진 행을 DB에서 select하여 VO 객체에 저장
		
		// VO 객체가 null이 아닌 경우(= 일치하는 ID 찾음)
		if(vo != null) {								
			// 비밀번호가 일치하는지 확인
			if(pw.equals(vo.getMem_pw())) {
				session.setAttribute("loginUser", vo.getMem_num());	// 세션에 유저(로그인한 회원) 회원번호 저장
				url = "4w.do?command=index";						// 이동할 URL 변경(메인 페이지)
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);	// 이동할 URL 설정
				dispatcher.forward(request, response);  							// 페이지 이동
			} 
			else {	// 비밀번호가 일치하지 않는 경우
				// PW 오류 알림창 팝업 후 로그인 페이지로 이동
				Service.super.alertAndForward(request, response, url, "Login failed", "비밀번호를 잘못 입력했습니다.");
			}
		} else {	// VO 객체가 null인 경우(= 일치하는 ID 없음)
			// ID 오류 알림창 팝업 후 로그인 페이지로 이동
			Service.super.alertAndForward(request, response, url, "Login failed", "해당 ID로 등록된 계정을 찾을 수 없습니다.");
		}
	}
}
