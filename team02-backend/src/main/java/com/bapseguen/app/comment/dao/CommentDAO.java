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

    //게시글 번호로 댓글 목록 조회
    public List<CommentListDTO> selectAll(int postNumber) {
        return sqlSession.selectList("comment.selectAll", postNumber);
    }

    public void delete(int commentNumber) {
        sqlSession.delete("comment.delete", commentNumber);
    }
}
