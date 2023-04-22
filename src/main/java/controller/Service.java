package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 각 페이지의 요청을 처리할 서비스 형식을 정의한 인터페이스
// 작성자 : 이수봉
public interface Service {
	// 서비스 실행 메소드
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	// 알림창 출력 후 페이지 이동하는 기능 제공(default 메소드)
	public default void alertAndForward(HttpServletRequest request, HttpServletResponse response,
			String forwardURL, String pageTitle, String alertMessage) throws ServletException, IOException {
		// request 속성에 이동할 URL, 페이지 제목, 알림 메시지 정보 저장
		request.setAttribute("forwardURL", forwardURL);
		request.setAttribute("title", pageTitle);
		request.setAttribute("message", alertMessage);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("view/alert/show_alert.jsp");	// 이동할 URL 설정
		dispatcher.forward(request, response);  // 알림창 띄우는 페이지로 이동
	}
}
