package controller.memberservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Service;
import model.dao.MemberDAO;
import model.vo.MemberVO;

// 수정한 회원정보를 DB에 update하는 서비스 구현 클래스
// 작성자 : 이수봉
public class UpdateMemService implements Service {
	@Override
	// 서비스 실행 메소드 구현
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "4w.do?command=modmem";
		MemberVO vo = new MemberVO();	// 수정할 회원정보를 담을 VO 객체 생성
		// 요청받은 수정 정보를 변수에 저장
		vo.setMem_id(request.getParameter("id"));
		vo.setMem_pw(request.getParameter("pw"));
		vo.setMem_name(request.getParameter("name"));
		vo.setEmail(request.getParameter("email1") + "@" + request.getParameter("email2"));
		vo.setAddress(request.getParameter("address"));
		vo.setPhone(request.getParameter("phone1") + "-"
					+ request.getParameter("phone2") + "-"
					+ request.getParameter("phone3"));
		
		MemberDAO dao = MemberDAO.getDAO();		// DAO 객체 참조
		dao.dataUpdate(vo);						// DB에 회원정보 update(=정보수정)
		
		// 정보수정 완료 알림창 팝업 후 계정정보 확인 페이지로 이동
		Service.super.alertAndForward(request, response, url, "Complete", "회원정보가 수정되었습니다.");
	}
}
