package com.bapseguen.app.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.BannerDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.config.MyBatisConfig;

public class MainDAO {
	private SqlSession sqlSession;
	
	public MainDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}

	// 메인 가게 리스트
	public List<MainStoreListDTO> selectMainStoreList() {
		return sqlSession.selectList("main.storeList");
	}
	
	// 메인 재료 리스트
	public List<ItemWithImgDTO> selectMainIngredientList() {
	    return sqlSession.selectList("main.ingredientList");
	}
	
	// 레시피 리스트
	public List<PostDetailDTO> selectMainRecipeList(){
		return sqlSession.selectList("main.recipeList");
	}	

}
