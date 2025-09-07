package com.bapseguen.app.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CommentDTO;
import com.bapseguen.app.dto.view.CommentListDTO;
import com.bapseguen.config.MyBatisConfig;

public class CommentDAO {
	private SqlSession sqlSession;

	public CommentDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public int insertCommunity(CommentDTO dto) {
        return sqlSession.insert("comment.insertCommunity", dto);
    }
	
	public int insertInquiry(CommentDTO dto) {
		return sqlSession.insert("comment.insertInquiry", dto);
	}

    //게시글 번호로 댓글 목록 조회
    public List<CommentListDTO> selectAll(int postNumber) {
        return sqlSession.selectList("comment.selectAll", postNumber);
    }
    
    // 게시글 타입 조회 (FREE/PROMOTION/RECIPE/INQUIRY/…)
    public String findPostType(int postNumber) {
        return sqlSession.selectOne("comment.findPostType", postNumber);
    }

    // 댓글 → 게시글 타입 조회(삭제 분기용)
    public String findPostTypeByComment(int commentNumber) {
        return sqlSession.selectOne("comment.findPostTypeByComment", commentNumber);
    }
    // 댓글 작성자(회원/관리자) 식별
    public Map<String, Object> findAuthor(int commentNumber) {
        return sqlSession.selectOne("comment.findAuthor", commentNumber);
    }
    
    public int delete(int commentNumber) {
        return sqlSession.delete("comment.delete", commentNumber);
    }
}
