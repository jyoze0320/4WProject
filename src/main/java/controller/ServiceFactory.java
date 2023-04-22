package controller;

import controller.adminservice.AdminIndexService;
import controller.adminservice.AdminLoginService;
import controller.adminservice.AdminLogoutService;
import controller.adminservice.AdminMemberListService;
import controller.adminservice.AdminProductListService;
import controller.adminservice.AdminProductWriteFormService;
import controller.adminservice.AdminProductWriteService;
import controller.adminservice.AdminQnaDetailService;
import controller.adminservice.AdminQnaListService;
import controller.adminservice.AdminQnaUpdateService;
import controller.index.IndexService;
import controller.memberservice.CreateMemService;
import controller.memberservice.IdOverlapChkService;
import controller.memberservice.InsertNewMemService;
import controller.memberservice.LoginChkService;
import controller.memberservice.LoginService;
import controller.memberservice.LogoutService;
import controller.memberservice.ModMemService;
import controller.memberservice.UpdateMemService;
import controller.qnaservice.QnaDeleteService;
import controller.qnaservice.QnaListService;
import controller.qnaservice.QnaModFormService;
import controller.qnaservice.QnaModService;
import controller.qnaservice.QnaViewService;
import controller.qnaservice.QnaWriteFormService;
import controller.qnaservice.QnaWriteService;
import controller.shopping.basketDeleteService;
import controller.shopping.basketInService;
import controller.shopping.categoryService;
import controller.shopping.orderService;
import controller.shopping.paymentInService;
import controller.shopping.paymentService;
import controller.shopping.pdDetailService;

// 작성자 : 김준영, 손영석, 손일형, 이수봉
public class ServiceFactory {
	private static ServiceFactory factory;	// ServiceFactory 객체
	private ServiceFactory() {
		// 기본 생성자(private 지정)
	}
	// ServiceFactory 객체 반환하는 메소드(싱글톤 패턴)
	public static ServiceFactory getFactory() {
		// factory 객체가 없는 경우 객체 생성하여 반환
		if(factory == null) {
			factory = new ServiceFactory();	// 객체 생성
		}
		return factory;
	}
	
	// 입력받은 command에 따라 해당하는 Service 객체를 반환하는 메소드(팩토리 패턴)
	public Service getService(String command) {
		Service service = null;		// Service 객체(인터페이스이므로 단독으로 객체 생성 불가)

		// command 패턴 >> command에 따라 적용할 service 제어
		// index 페이지로 이동하는 서비스
		if (command.equals("index")) {
			service = new IndexService();
		} 
		// 회원 관련 서비스(이수봉)
		// --로그인 관련 서비스
		else if(command.equals("login")) {
			service = new LoginService();
		} else if(command.equals("loginchk")) {
			service = new LoginChkService();
		} else if(command.equals("logout")) {
			service = new LogoutService();
		}
		// --회원가입 관련 서비스
		else if(command.equals("createmem")) {
			service = new CreateMemService();
		} else if(command.equals("insert_newmem")) {
			service = new InsertNewMemService();
		} else if(command.equals("id_overlapchk")) {
			service = new IdOverlapChkService();
		}
		// --회원정보수정 관련 서비스
		else if(command.equals("modmem")) {
			service = new ModMemService();
		} else if(command.equals("update_mem")) {
			service = new UpdateMemService();
		}
		// shopping 관련 서비스(김준영)
		else if(command.equals("categorylist")) {
    		service =  new categoryService();
		} else if(command.equals("pddetail")) {
			service =  new pdDetailService();
	    } else if(command.equals("basketin")) {
	    	service =  new basketInService();
	    } else if(command.equals("basketdelete")) {
	    	service =  new basketDeleteService();
	    } else if(command.equals("order")) {
	    	service =  new orderService();
	    } else if(command.equals("payment")) {
	    	service =  new paymentService();
	    } else if(command.equals("paymentIn")) {
	    	service =  new paymentInService();
	    } 
		// 관리자 관련 서비스(손영석)
		else if (command.equals("admin_login_form")) {
	    	 service = new AdminIndexService();  
	    } else if (command.equals("admin_login")) {
	    	service = new AdminLoginService();
	    } else if (command.equals("admin_logout")) {
	    	service = new AdminLogoutService();	
	    } else if (command.equals("admin_product_list")) {
	    	service = new AdminProductListService();  
	    } else if (command.equals("admin_product_write_form")) {
	        service = new AdminProductWriteFormService();
	    } else if (command.equals("admin_product_write")) {
	    	service = new AdminProductWriteService();
	    }
		// --회원목록보기
		else if (command.equals("admin_member_list")) {
			service = new AdminMemberListService();
    	}
	    // Q&A 게시판 관련 서비스(손일형)
	    else if(command.equals("qna_list")) {
	    	service = new QnaListService();
	    } else if(command.equals("qna_view")) {
	    	service = new QnaViewService();
	    } else if(command.equals("qna_write_form")) {
	    	service = new QnaWriteFormService();
	    } else if(command.equals("qna_write")) {
	    	service = new QnaWriteService();
	    } else if(command.equals("qna_delete")) {
	    	service = new QnaDeleteService();
	    } else if(command.equals("qna_modform")) {
	    	service = new QnaModFormService();
	    } else if(command.equals("qna_mod")) {
	    	service = new QnaModService();
	    } 
		// 관리자 페이지 Q&A 관련 서비스(손일형)
	    else if (command.equals("admin_qna_list")) {
	    	service = new AdminQnaListService();
	    } else if (command.equals("admin_qna_detail")) {
	        service = new AdminQnaDetailService();
	    } else if (command.equals("admin_qna_update")) {
	        service = new AdminQnaUpdateService();
	    }
		return service;
	}
}