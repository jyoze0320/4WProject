package controller.memberservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Service;

// 로그아웃 시의 서비스 구현 클래스
// 작성자 : 이수봉
public class LogoutService implements Service {
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		// 세션 가져오기
		String url = "4w.do?command=index";				// 이동할 페이지 URL
		session.removeAttribute("loginUser");			// 세션에 저장된 유저(로그인한 회원) 정보 제거

		// 로그아웃 완료 알림창 팝업 후 index 페이지로 이동
		Service.super.alertAndForward(request, response, url, "Logout done", "로그아웃이 완료되었습니다.");
	}

}
