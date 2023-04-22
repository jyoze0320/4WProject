package model.vo;

// 관리자 정보를 다루는 VO 클래스
// 작성자 : 손영석
public class AdminVO {
	private int admin_num;		// 관리자 번호
	private String admin_id;	// 관리자 ID
	private String admin_pw;	// 관리자 비밀번호
	private String admin_name;	// 관리자 이름
	
		// 기본 생성자
		public AdminVO() {
			super();
		}
		// 생성자 오버로드(초기화 시 사용)
		public AdminVO(int admin_num, String admin_id,  String admin_pw, String admin_name ) {
			super();
			this.admin_num = admin_num;
			this.admin_id = admin_id;
			this.admin_pw = admin_pw;
			this.admin_name = admin_name;
		}
		
		// getter, setter
		public int getAdmin_num() {
			return admin_num;
		}
		public void setAdmin_num(int admin_num) {
			this.admin_num = admin_num;
		}
		public String getAdmin_id() {
			return admin_id;
		}
		public void setAdmin_id(String admin_id) {
			this.admin_id = admin_id;
		}
		public String getAdmin_pw() {
			return admin_pw;
		}
		public void setAdmin_pw(String admin_pw) {
			this.admin_pw = admin_pw;
		}
		public String getAdmin_name() {
			return admin_name;
		}
		public void setAdmin_name(String admin_name) {
			this.admin_name = admin_name;
		}
		
}
