package com.bapseguen.app.img.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.PostImageDTO;
import com.bapseguen.config.MyBatisConfig;

public class ItemImageDAO {
	private SqlSession sqlSession;

	public ItemImageDAO() { 
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	// 파일 추가 메소드
	public void insert(ItemImageDTO ItemImageDTO) {
		System.out.println("파일 DAO - 파일 저장 " + ItemImageDTO);

		try {
			int result = sqlSession.insert("image.itemInsert", ItemImageDTO);
			System.out.println("파일 저장 완료 - DB에 저장된 행의 개수 : " + result);

			// db에 파일이 제대로 저장되었는지 확인
			List<ItemImageDTO> uploadFile = select(ItemImageDTO.getItemNumber());
			System.out.println("db에서 가져온 파일 : " + uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("파일 저장이 실패되었습니다. : " + e.getMessage());
			e.printStackTrace();
		}
	}

	// 파일 조회 메소드
	public List<ItemImageDTO> select(int postNumber) {
		return sqlSession.selectList("image.itemSelect", postNumber);
	}
	public ItemImageDTO selectone(int postNumber) {
		return sqlSession.selectOne("image.itemSelect", postNumber);
	}

	// 파일 삭제 메소드
	public void delete(int boardNumber) {
		sqlSession.delete("image.itemDelete", boardNumber);
	}
	
}
