package com.bapseguen.app.img.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.PostImageDTO;
import com.bapseguen.config.MyBatisConfig;

public class PostImageDAO {
	private SqlSession sqlSession;

	public PostImageDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 파일 추가 메소드
	public void insert(PostImageDTO postImageDTO) {
		System.out.println("파일 DAO - 파일 저장 " + postImageDTO);

		try {
			int result = sqlSession.insert("postImage.insert", postImageDTO);
			System.out.println("파일 저장 완료 - DB에 저장된 행의 개수 : " + result);

			// db에 파일이 제대로 저장되었는지 확인
			List<PostImageDTO> uploadFile = select(postImageDTO.getPostNumber());
			System.out.println("db에서 자겨온 파일 : " + uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("파일 저장이 실패되었습니다. : " + e.getMessage());
			e.printStackTrace();
		}
	}

	// 파일 조회 메소드
	public List<PostImageDTO> select(int postNumber) {
	    List<PostImageDTO> images = new ArrayList<>();
	    try {
	        images = sqlSession.selectList("postImage.select", postNumber);
	        System.out.println("조회된 파일 리스트 : " + images);
	    } catch (Exception e) {
	        System.out.println("첨부파일 조회 실패 : " + e.getMessage());
	        e.printStackTrace();
	    }
	    return images;
	}
	

	
	
	// 파일 삭제 메소드
	public void delete(int postNumber) {
		sqlSession.delete("postImage.delete", postNumber);
	}
	
	
	
	
}	
