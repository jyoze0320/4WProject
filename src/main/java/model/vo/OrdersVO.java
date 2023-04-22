package model.vo;

// 주문 테이블 값을 다루는 VO 클래스
// 작성자 : 김준영
public class OrdersVO {
	private int order_num;			// 주문번호
	private int mem_num;			// 회원번호
	private String order_email;		// 받을 이메일
	private int pd_num;				// 상품번호
	private int basket;				// 장바구니 or 구매여부
	private String payment;			// 결제수단
	private String order_Date;		// 주문날짜
	
	// 기본 생성자
	public OrdersVO() {
		super();
	}
	
	// 초기화 생성자(오버로드)
	public OrdersVO(int order_num, int mem_num, String order_email, int pd_num, int basket, String payment,
			String order_Date) {
		super();
		this.order_num = order_num;
		this.mem_num = mem_num;
		this.order_email = order_email;
		this.pd_num = pd_num;
		this.basket = basket;
		this.payment = payment;
		this.order_Date = order_Date;
	}
	
	// getter, setter
	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getOrder_email() {
		return order_email;
	}

	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}

	public int getPd_num() {
		return pd_num;
	}

	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}

	public int getBasket() {
		return basket;
	}

	public void setBasket(int basket) {
		this.basket = basket;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getOrder_Date() {
		return order_Date;
	}

	public void setOrder_Date(String order_Date) {
		this.order_Date = order_Date;
	}
}
