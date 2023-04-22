package model.vo;

// 회원정보 테이블 값을 다루는 VO 클래스
// 작성자 : 이수봉
public class MemberVO {
	private int mem_num;		// 회원번호
	private String mem_id;		// 회원 ID
	private String mem_pw;		// 회원 비밀번호
	private String mem_name;	// 회원 이름
	private String email;		// 이메일
	private String address;		// 주소지
	private String phone;		// 전화번호
	private String initDate;	// 가입날짜
	
	// 기본 생성자
	public MemberVO() {
		super();
	}

	// 생성자 오버로드(초기화에 사용)
	public MemberVO(int mem_num, String mem_id, String mem_pw, String mem_name, 
			String email, String address, String phone, String initDate) {		// select 시 사용(모든 필드 초기화)
		super();
		this.mem_num = mem_num;
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.initDate = initDate;
	}
	public MemberVO(String mem_id, String mem_pw, String mem_name, String email,
			String address, String phone) {			// insert 시 사용(회원번호와 가입일 제외하고 초기화)
		super();
		this.mem_id = mem_id;
		this.mem_pw = mem_pw;
		this.mem_name = mem_name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	// getter 정의
	public int getMem_num() {
		return mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public String getMem_pw() {
		return mem_pw;
	}

	public String getMem_name() {
		return mem_name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getInitDate() {
		return initDate;
	}

	// setter 정의
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}
	
}
