package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/4w.do")
// 서블릿 사용하여 요청받은 command에 따라 서비스를 실행하는 컨트롤러
// reference >> 수업 교안 MVC2 샘플 소스 - MVC2_ORACLE_Shop
// 주석 : 이수봉
public class Servlet extends HttpServlet{

	/**default serial version UID*/
	private static final long serialVersionUID = 1L;

	@Override
	// get 방식 데이터 통신 처리
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");		// 요청 command 가져오기
	    System.out.println("요청 Command : " + command);		// 요청 command 콘솔 출력하여 확인

	    ServiceFactory factory = ServiceFactory.getFactory();	// ServiceFactory 객체 참조
	    Service service = factory.getService(command);			// 요청 command에 맞는 Service 객체 생성(팩토리 패턴)

	    // 해당하는 서비스가 존재할 경우(null이 아닐 때)
	    if (service != null) {
	      service.execute(request, response);	// 서비스 실행 메소드 호출
	    }
	}

	@Override
	// post 방식 데이터 통신 처리
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 인코딩 설정(post 방식의 경우 데이터 전달 과정에서 인코딩 문제 생길 수 있음)
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html; charset=utf-8");
		doGet(request, response);	// get 방식과 동일한 알고리즘으로 처리
	}
}
