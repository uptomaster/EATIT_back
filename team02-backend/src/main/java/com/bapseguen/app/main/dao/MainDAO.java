package com.bapseguen.app.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.dto.view.MemberListDTO;
import com.bapseguen.config.MyBatisConfig;

public class MainDAO {
	private SqlSession sqlSession;
	
	public MainDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 메인 가게 리스트
	public List<MainStoreListDTO> selectMainStoreList(Map<String, Object> pageMap) {
		return sqlSession.selectList("main.storeList", pageMap);
	}
	
	// 메인 재료 리스트
	public List<ItemWithImgDTO> selectIngredientList(Map<String, Object> pageMap) {
		return sqlSession.selectList("main.ingredientList", pageMap);
	}

}
