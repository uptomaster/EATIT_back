package com.bapseguen.app.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CommentDTO;
import com.bapseguen.app.dto.view.CommentListDTO;
import com.bapseguen.config.MyBatisConfig;

public class CommentDAO {
	private SqlSession sqlSession;

	public CommentDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public int insert(CommentDTO dto) {
        return sqlSession.insert("comment.insert", dto);
    }

    /** 게시글 번호로 댓글 목록 조회 (삭제되지 않은 것만) */
    public List<CommentListDTO> selectAll(int postNumber) {
        return sqlSession.selectList("comment.selectAll", postNumber);
    }

    /** 댓글 삭제(소프트 삭제: COMMENT_DELETE_STATE = 1) */
    public int delete(int commentNumber) {
        return sqlSession.update("comment.delete", commentNumber);
    }
}
