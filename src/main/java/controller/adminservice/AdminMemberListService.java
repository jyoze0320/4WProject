package controller.adminservice;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Service;
import model.dao.MemberDAO;
import model.vo.MemberVO;

// 작성자 : 손영석
public class AdminMemberListService implements Service {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String url = "view/admin/member/memberList.jsp";
    String key = request.getParameter("key");
    String tpage = request.getParameter("tpage");    
    if(key == null){
    	key=""; 															//전체 회원 조회
    }    
    if(tpage == null){
      tpage = "1"; 															//현재 페이지                    
    }else if( tpage.equals("") ){
       tpage="1";  																	
    }
    request.setAttribute("key", key);
    request.setAttribute("tpage",tpage);
    
    MemberDAO memberDAO = MemberDAO.getDAO();								//객체생성
    ArrayList<MemberVO> list=												//list로 값 받기
    		memberDAO.listmember( Integer.parseInt(tpage), key);    
    String paging = memberDAO.pageNumber( Integer.parseInt(tpage), key);
     //jsp로 데이터 전달
    request.setAttribute("list",list);
    int n = list.size();   
    request.setAttribute("memberListSize",n); 
    request.setAttribute("paging", paging);    
    request.getRequestDispatcher(url).forward(request, response);
  }
}