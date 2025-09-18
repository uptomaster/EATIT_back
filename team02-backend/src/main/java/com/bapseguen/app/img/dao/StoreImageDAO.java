package com.bapseguen.app.img.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.StoreImageDTO;
import com.bapseguen.config.MyBatisConfig;

public class StoreImageDAO {
	private SqlSession sqlSession;

	public StoreImageDAO() { 
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	// 파일 추가 메소드
	public void insert(StoreImageDTO StoreImageDTO) {
		System.out.println("파일 DAO - 파일 저장 " + StoreImageDTO);

		try {
			int result = sqlSession.insert("image.storeInsert", StoreImageDTO);
			System.out.println("파일 저장 완료 - DB에 저장된 행의 개수 : " + result);

			// db에 파일이 제대로 저장되었는지 확인
			List<StoreImageDTO> uploadFile = select(StoreImageDTO.getBusinessNumber());
			System.out.println("db에서 가져온 파일 : " + uploadFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("파일 저장이 실패되었습니다. : " + e.getMessage());
			e.printStackTrace();
		}
	}

	// 파일 조회 메소드
	public List<StoreImageDTO> select(String businessNumber) {
		return sqlSession.selectList("image.storeSelect", businessNumber);
	}
	public StoreImageDTO selectone(String businessNumber) {
		return sqlSession.selectOne("image.storeSelect", businessNumber);
	}

	// 파일 삭제 메소드
	public void delete(String businessNumber) {
		sqlSession.delete("image.storeDelete", businessNumber);
	}
}
