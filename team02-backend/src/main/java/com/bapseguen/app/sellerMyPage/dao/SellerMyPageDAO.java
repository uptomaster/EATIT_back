package com.bapseguen.app.sellerMyPage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.config.MyBatisConfig;

public class SellerMyPageDAO {
	public SqlSession sqlSession;
	
	public SellerMyPageDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	
	// 음식 판매 등록
    public int addFood(ItemDTO dto) {
        return sqlSession.insert("storeManage.addFood", dto);
    }
    // 음식 메뉴 상세
    public ItemDTO detaileFood(int itemNumber) {
        return sqlSession.selectOne("storeManage.detaileFood", itemNumber);
    }
    // 음식 판매 수정
    public int editFood(ItemDTO dto) {
        return sqlSession.update("storeManage.editFood", dto);
    }
    // 음식 판매 삭제
    public int deleteFood(int itemNumber) {
        return sqlSession.delete("storeManage.deleteFood", itemNumber);
    }
    //음식 판매 목록
    public List<Map<String, Object>> foodList(String businessNumber) {
        return sqlSession.selectList("storeManage.foodList", businessNumber);
    }
    // 이미 준비된 음식
    public int alreadyFood(ItemDTO dto) {
        return sqlSession.selectOne("storeManage.alreadyfood", dto);
    }

    // INGREDIENT
    //재료 판매 등록
    public int addIngredient(ItemDTO dto) {
        return sqlSession.insert("storeManage.addIngredient", dto);
    }
    // 재료 판매 상세
    public ItemDTO detaileIngredient(int itemNumber) {
        return sqlSession.selectOne("storeManage.detaileIngredient", itemNumber);
    }
    //재료 판매 수정
    public int editIngredient(ItemDTO dto) {
        return sqlSession.update("storeManage.editIngredient", dto);
    }
    //재료 판매 삭제
    public int deleteIngredient(int itemNumber) {
        return sqlSession.delete("storeManage.deleteingredient", itemNumber);
    }
    // 재료 판매 목록
    public List<Map<String, Object>> ingredientList(String businessNumber) {
        return sqlSession.selectList("storeManage.ingredientList", businessNumber);
    }
    
    // Item Image - needs businessNumber for MAX(ITEM_NUMBER) subquery
    // 메슈 사진 저장
    public int addItemImage(ItemImageDTO imageDTO, String businessNumber) {
        Map<String, Object> param = new HashMap<>();
        param.put("itemImageNumber", imageDTO.getItemImageNumber());
        param.put("itemImageSystemName", imageDTO.getItemImageSystemName());
        param.put("itemImageOriginalName", imageDTO.getItemImageOriginalName());
        param.put("businessNumber", businessNumber);
        return sqlSession.insert("storeManage.addItemImage", param);
    }

    // Store info
    public SellerInfoDTO selectStoreInfo(String businessNumber) {
        return sqlSession.selectOne("storeManage.selectStoreInfo", businessNumber);
    }

    // 판매 내역
    // 오늘 판매 내역
    public List<Map<String, Object>> todaySaleHistory(String businessNumber) {
        return sqlSession.selectList("storeManage.todaySaleHistory", businessNumber);
    }
    // 총 판매 내역
    public List<Map<String, Object>> totalSaleHistory(String businessNumber) {
        return sqlSession.selectList("storeManage.totalSaleHistory", businessNumber);
    }
    
    // 원산지
    //원산지 정보 추가
    public int addOrigin(OriginDTO dto) {
        return sqlSession.insert("origin.addOrigin", dto);
    }
    // 원산지 정보 목록
    public List<OriginDTO> originList(String businessNumber) {
        return sqlSession.selectList("origin.originList", businessNumber);
    }
    // 원산지 정보 수정
    public int updateOrigin(OriginDTO dto) {
        return sqlSession.update("origin.updateOrigin", dto);
    }
    // 원산지 정보 삭제
    public int deleteOrigin(int originNumber) {
        return sqlSession.delete("origin.deleteOrigin", originNumber);
    }
    // 이미 등록한 원산지 정보
    public int alreadyOrigin(OriginDTO dto) {
        return sqlSession.selectOne("origin.alreadyOrigin", dto);
    }

	// // 
}
