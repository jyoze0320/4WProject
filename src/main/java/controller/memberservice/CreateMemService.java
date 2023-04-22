package controller.memberservice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;

// 회원가입 페이지로 넘어가는 서비스 구현 클래스
// 작성자 : 이수봉
public class CreateMemService implements Service {
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "view/member/createmem.jsp";	// 회원가입 페이지 URL

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);	// 이동할 URL 설정
		dispatcher.forward(request, response); 								// 페이지 이동
	}

}
