package com.bapseguen.app.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.dto.view.MemberListDTO;
import com.bapseguen.config.MyBatisConfig;

public class MainDAO {
	private SqlSession sqlSession;

	public MainDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public List<MainStoreListDTO> selectStoreList(Map<String, Object> pageMap) {
		return sqlSession.selectList("main.storeList", pageMap);
	}

}
