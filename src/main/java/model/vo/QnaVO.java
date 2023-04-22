package model.vo;

/*
 * QNA 게시판 VO
 * 작성자 : 손일형
 * */
public class QnaVO {
	private int    qna_qseq ; 	// 글 번호
	private String qna_title ; 	// 글 제목
	private String qna_cnt;		// 문의 내용
	private String qna_rep;		// 답변 내용
	private int mem_num; 		// 작성자(회원번호)
	private String repyn; 		// 답변 유무
	private String qna_indate;	// 작성날짜
	private String mem_id;		// 작성자 ID
	
	public QnaVO() {}
	  
	public int getQna_qseq() {	return qna_qseq; }
	public void setQna_qseq(int qna_qseq) {	this.qna_qseq = qna_qseq; }
	
	public String getQna_title() {	return qna_title; }
	public void setQna_title(String qna_title) {	this.qna_title = qna_title; }
	
	public String getQna_cnt() {	return qna_cnt;	}	
	public void setQna_cnt(String qna_cnt) {	this.qna_cnt = qna_cnt;	}
	
	public String getQna_rep() {	return qna_rep;	}
	public void setQna_rep(String qna_rep) {	this.qna_rep = qna_rep;	}
	
	public int getMem_num() {	return mem_num;	}
	public void setMem_num(int mem_num) {	this.mem_num = mem_num;	}
	
	public String getRepyn() {	return repyn;	}
	public void setRepyn(String repyn) {	this.repyn = repyn;	}
	
	public String getQna_indate() {	return qna_indate;	}
	public void setQna_indate(String qna_indate) {	this.qna_indate = qna_indate;	}

	public String getMem_id() {	return mem_id;	}
	public void setMem_id(String mem_id) { this.mem_id = mem_id; }  
}
