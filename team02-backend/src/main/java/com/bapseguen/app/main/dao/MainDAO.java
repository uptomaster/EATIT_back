package com.bapseguen.app.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.BannerDTO;
import com.bapseguen.app.dto.StoreDTO;
import com.bapseguen.config.MyBatisConfig;

public class MainDAO {

	private SqlSession sqlSession;
	
	public MainDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
//	가게 리스트 조회
	 public List<StoreDTO> selectStoreList() {
	        return sqlSession.selectList("main.selectStoreList");
	    }
}
