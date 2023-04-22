package model.vo;

// 상품 테이블 값을 다루는 VO 클래스
// 작성자 : 김준영
public class ProductsVO {
	private int pd_num;			// 상품번호
	private String pd_name;		// 상품명
	private String kind;		// 상품종류
	private int cost_price;		// 원가
	private int sell_price;		// 판매가
	private String pd_content;	// 상품 설명
	private String pd_image;	// 상품 이미지 파일명
	private int pd_totalsell;	// 누적 판매량
	private String sellyn;		// 판매여부
	private String regdate;		// 등록날짜
	
	
	// 기본 생성자
	public ProductsVO() {
		super();
	}
	
	// 초기화 생성자(오버로드)
	public ProductsVO(int pd_num, String pd_name, String kind, int cost_price, int sell_price, String pd_content,
			String pd_image, int pd_totalsell, String sellyn, String regdate) {
		super();
		this.pd_num = pd_num;
		this.pd_name = pd_name;
		this.kind = kind;
		this.cost_price = cost_price;
		this.sell_price = sell_price;
		this.pd_content = pd_content;
		this.pd_image = pd_image;
		this.pd_totalsell = pd_totalsell;
		this.sellyn = sellyn;
		this.regdate = regdate;
	}
	
	// getter, setter
	public int getPd_num() {
		return pd_num;
	}
	public void setPd_num(int pd_num) {
		this.pd_num = pd_num;
	}
	public String getPd_name() {
		return pd_name;
	}
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getCost_price() {
		return cost_price;
	}
	public void setCost_price(int cost_price) {
		this.cost_price = cost_price;
	}
	public int getSell_price() {
		return sell_price;
	}
	public void setSell_price(int sell_price) {
		this.sell_price = sell_price;
	}
	public String getPd_content() {
		return pd_content;
	}
	public void setPd_content(String pd_content) {
		this.pd_content = pd_content;
	}
	public String getPd_image() {
		return pd_image;
	}
	public void setPd_image(String pd_image) {
		this.pd_image = pd_image;
	}
	public int getPd_totalsell() {
		return pd_totalsell;
	}
	public void setPd_totalsell(int pd_totalsell) {
		this.pd_totalsell = pd_totalsell;
	}
	public String getSellyn() {
		return sellyn;
	}
	public void setSellyn(String sellyn) {
		this.sellyn = sellyn;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
