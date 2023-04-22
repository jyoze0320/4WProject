package controller.memberservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.MemberDAO;
import model.vo.MemberVO;

// 회원가입 정보를 DB에 insert하는 서비스 구현 클래스
// 작성자 : 이수봉
public class InsertNewMemService implements Service {
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "4w.do?command=login";		// 로그인 페이지 URL(회원가입 완료 후 이동)
		// 요청받은 가입자 정보를 변수에 저장(아이디, 비밀번호, 이름, 이메일, 거주지 주소, 전화번호)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email1") + "@" + request.getParameter("email2");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone1") + "-"
					   + request.getParameter("phone2") + "-"
					   + request.getParameter("phone3");
		// 가입할 회원정보를 담은 VO 객체 생성
		MemberVO vo = new MemberVO(id, pw, name, email, address, phone);
		MemberDAO dao = MemberDAO.getDAO();		// DAO 객체 참조
		dao.dataInsert(vo);						// DB에 회원정보 insert(=회원가입)
		
		// 회원가입 완료 알림창 팝업 후 로그인 페이지로 이동
		Service.super.alertAndForward(request, response, url, "Welcome", "회원가입이 완료되었습니다.");
	}
}
