package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.vo.QnaVO;
import utilmanager.DBCPManager;

// 작성자 : 손일형
public class QnaDAO implements DAOInterface<QnaDAO>{
	private QnaDAO() {
	} // 싱글톤 패턴

	private static QnaDAO daoInstance = new QnaDAO();

	public static QnaDAO getDAO() {
		return daoInstance;
	}

	// QNA 게시글 리스트
	public ArrayList<QnaVO> listQna(int mem_num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		String sql = "select * from mqv " 
				+ "where mem_num = ? " 
				+ "order by qna_qseq desc";

		try {
			conn = DBCPManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setQna_qseq(rs.getInt("qna_qseq"));
				qnaVO.setQna_title(rs.getString("qna_title"));
				qnaVO.setQna_cnt(rs.getString("qna_cnt"));
				qnaVO.setMem_num(rs.getInt("mem_num"));
				qnaVO.setQna_indate(rs.getString("qna_indate"));
				qnaVO.setQna_rep(rs.getString("qna_rep"));
				qnaVO.setRepyn(rs.getString("repyn"));
				qnaList.add(qnaVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaList;
	}

	// getQna 메소드
	public QnaVO getQna(int qna_seq) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		QnaVO qnaVO = null;
		String sql = "select * from mqv " 
				+ "where qna_qseq = ?";

		try {
			conn = DBCPManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_seq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				qnaVO = new QnaVO();
				qnaVO.setQna_qseq(qna_seq);
				qnaVO.setQna_title(rs.getString("qna_title"));
				qnaVO.setQna_cnt(rs.getString("qna_cnt"));
				qnaVO.setMem_id(rs.getString("mem_id"));
				qnaVO.setQna_indate(rs.getString("qna_indate"));
				qnaVO.setQna_rep(rs.getString("qna_rep"));
				qnaVO.setRepyn(rs.getString("repyn"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return qnaVO;
	}

	// QNA 삽입 메소드 => insert 인터페이스 사용가능
	public void insertqna(QnaVO qnaVO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into qna (qna_qseq, qna_title, qna_cnt, mem_num)" 
				+ " values(qna_seq.nextval, ?, ?, ?)";

		try {
			conn = DBCPManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaVO.getQna_title()); // QNA 제목
			pstmt.setString(2, qnaVO.getQna_cnt()); // QNA 내용
			pstmt.setInt(3, qnaVO.getMem_num()); // 로그인중인 ID
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// QNA 수정 메소드
	public void modqna(QnaVO qnaVO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update qna "
				+ "set qna_title = ?, qna_cnt = ? "
				+ "where qna_qseq = ?";

		try {
			conn = DBCPManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaVO.getQna_title()); // QNA 제목
			pstmt.setString(2, qnaVO.getQna_cnt()); // QNA 내용
			pstmt.setInt(3, qnaVO.getQna_qseq()); 
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// QNA 삭제 메소드
	public void deleteqna(int qna_qesq) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from mqv " 
				+ "where qna_qseq = ?";

		try {
			conn = DBCPManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_qesq);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/*===============================================================================*/
	// 관리자모드에서 사용하는 메소드
	// QNA 글 불러오는 메소드
	public ArrayList<QnaVO> listAllQna() {

		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from mqv order by repyn asc";
		
		try {
			conn = DBCPManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setQna_qseq(rs.getInt("qna_qseq"));
				qnaVO.setQna_title(rs.getString("qna_title"));
				qnaVO.setQna_cnt(rs.getString("qna_cnt"));
				qnaVO.setMem_id(rs.getString("mem_id"));
				qnaVO.setQna_indate(rs.getString("qna_indate"));
				qnaVO.setQna_rep(rs.getString("qna_rep"));
				qnaVO.setRepyn(rs.getString("repyn"));
				qnaList.add(qnaVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qnaList;
	}

	// QNA 답변 업데이트 메소드
	public void updateQna(QnaVO qnaVO) {
	   String sql = " update qna "
	    		+ " set qna_rep=?, repyn='y' where qna_qseq=?";
	
	   Connection conn = null;
	   PreparedStatement pstmt = null;
	   try {
		   conn = DBCPManager.getConnection();
		   pstmt = conn.prepareStatement(sql);
		   pstmt.setString(1, qnaVO.getQna_rep());
		   pstmt.setInt(2, qnaVO.getQna_qseq());      
		   pstmt.executeUpdate();
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	}
	
	// interface override
	@Override
	public void dataInsert(QnaDAO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataUpdate(QnaDAO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataDelete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<QnaDAO> selectAllData() {
		// TODO Auto-generated method stub
		return null;
	}
}// end class
