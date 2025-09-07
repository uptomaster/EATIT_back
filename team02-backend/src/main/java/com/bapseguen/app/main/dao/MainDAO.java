package com.bapseguen.app.main.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.AdminImageDTO;
import com.bapseguen.app.dto.GeneralMemberDTO;
import com.bapseguen.app.dto.SellerMemberDTO;
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
	
	public List<AdminImageDTO> selectAdminImageDTO(){
		return sqlSession.selectList("main.bannerList");
	}
	
	//나무등급 아이콘용
    public String getMemberType(int memberNumber) {
        return sqlSession.selectOne("member.getMemberType", memberNumber);
    }
    public GeneralMemberDTO getGeneralGradeInfo(int memberNumber) {
        return sqlSession.selectOne("member.getGeneralGradeInfo", memberNumber);
    }
    public SellerMemberDTO getSellerGradeInfo(int memberNumber) {
        return sqlSession.selectOne("member.getSellerGradeInfo", memberNumber);
    }	

}
