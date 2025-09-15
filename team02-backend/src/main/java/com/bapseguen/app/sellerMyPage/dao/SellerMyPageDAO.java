package com.bapseguen.app.sellerMyPage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.ItemListDTO;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.dto.ReviewDTO;
import com.bapseguen.app.dto.view.CommentListDTO;
import com.bapseguen.app.dto.view.ItemInsertDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MyPurchaseDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
import com.bapseguen.app.dto.view.SaleHistoryDTO;
import com.bapseguen.app.dto.view.SellerInfoDTO;
import com.bapseguen.config.MyBatisConfig;

public class SellerMyPageDAO {
	public SqlSession sqlSession;
	
	public SellerMyPageDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	// 비밀번호 확인 메소드
	public int chkPw(SellerInfoDTO sellerinfoDTO) {
		System.out.println("[판페DAO] 비밀번호 확인 ===");
		Integer memberNumber = sqlSession.selectOne("seller.chkPw", sellerinfoDTO);
		return memberNumber == null ? -1 : memberNumber;
	}
	
	// 사업자번호 가져오는 메소드
	public String getBusinessNumber(int memberNumber) {
		System.out.println("[판페DAO] 사업자번호 가져오기 ===");
		String business = sqlSession.selectOne("seller.getBusinessNumber", memberNumber);
		return business;
	}
	
	//sellerInfo 정보 채우는 메소드
//	public SellerInfoDTO takeSellerInfoDTO(String businessNumber) {
//		System.out.println("[판페DAO] seller의 모든 정보 불러오기===");
//		System.out.println("회원 번호 : "+businessNumber);
//        SellerInfoDTO seller = sqlSession.selectOne("seller.takeSellerinfo", businessNumber);
//        System.out.println(seller);
//        return seller;
//	}
	// ***메뉴 등록***************************************************************************** //	
    
    // 메뉴 사진 저장
    public void addItemImage(ItemWithImgDTO ItemWithImgDTO) {
    	System.out.println("[판페DAO] 메뉴 사진 저장 - addItemImage 메소드 실행");
    	System.out.println("사업자번호 : "+ItemWithImgDTO.getBusinessNumber());
    	System.out.println("imageDTO : "+ItemWithImgDTO);
    	
        Map<String, Object> param = new HashMap<>();
        param.put("[판페DAO] itemImageNumber", ItemWithImgDTO.getItemImageNumber());
        param.put("[판페DAO] itemImageSystemName", ItemWithImgDTO.getItemImageSystemName());
        param.put("[판페DAO] itemImageOriginalName", ItemWithImgDTO.getItemImageOriginalName());
        param.put("[판페DAO] businessNumber", ItemWithImgDTO.getBusinessNumber());
        sqlSession.insert("storeManage.addItemImage", param);
    }


    //음식 판매 목록
    public List<ItemWithImgDTO> foodList(Map<String, Object> pageMap) {
    	System.out.println("[판페DAO] 음식판매목록 - foodList 메소드 실행");
    	System.out.println("[판페DAO] 음식판매목록 map :"+pageMap.toString());
    	System.out.println("[판페DAO] 사업자 번호 : "+pageMap.get("businessNumber"));
    	
        List<ItemWithImgDTO> list = sqlSession.selectList("storeManage.foodList", pageMap);
        return list;
    }
    
	// 음식 판매 등록
    public int addFood(ItemInsertDTO itemDTO) {
    	System.out.println("[판페DAO] 음식판매등록 - addFood 메소드 실행 ");
    	System.out.println("itemDTO : "+itemDTO);
        sqlSession.insert("storeManage.addFood", itemDTO);
        int itemNum = sqlSession.selectOne("storeManage.getItemNumber", itemDTO);
        System.out.println("itemNumber : "+itemNum);
        itemDTO.setItemNumber(itemNum);
        System.out.println("DTO itemNumber : "+itemDTO.getItemNumber());
        
        return itemDTO.getItemNumber();
    }
    
    
    // 음식 메뉴 상세
    public ItemInsertDTO detailItem(int itemNumber) {
    	System.out.println("[판페DAO] 음식메뉴상세 - detailFood 메소드 실행 ");
    	System.out.println("[판페DAO] itemNumber : "+itemNumber);
    	
    	ItemInsertDTO answer= sqlSession.selectOne("storeManage.detailItem", itemNumber);
    	System.out.println("DB에서 가져온 값 : "+answer);
    	return answer;
    }
    public ItemListDTO detailItemList(int itemNumber) {
    	System.out.println("[판페DAO] 음식메뉴상세 - detailFood 메소드 실행 ");
    	System.out.println("[판페DAO] itemNumber : "+itemNumber);
    	
    	ItemListDTO answer= sqlSession.selectOne("storeManage.detailItemList", itemNumber);
    	System.out.println("DB에서 가져온 값 : "+answer);
    	return answer;
    }
    // 음식 판매 수정
    public void editFood(ItemInsertDTO dto) {
    	System.out.println("[판페DAO] 음식판매 수정 - editFood 메소드 실행");
    	System.out.println("[판페DAO] itemDTO : "+dto);
    	
       sqlSession.update("storeManage.editFood", dto);
    }
    // 음식 판매 삭제
    public void deleteFood(int itemNumber) {
    	System.out.println("[판페DAO] 음식판매삭제 - deleteFood 메소드 실행");
    	System.out.println("[판페DAO] itemNumber : "+itemNumber);
    	
        sqlSession.delete("storeManage.deleteFood", itemNumber);
    }

    // 이미 준비된 음식
    public int alreadyFood(ItemDTO dto) {
    	System.out.println("[판페DAO] 이미 등록한 음식 - alreadyFood 메소드 실행");
    	System.out.println("itemDTO : "+dto);
    	
        return sqlSession.selectOne("storeManage.alreadyfood", dto);
    }
    // 음식 개수
    public int foodCount(String bussinessNumber) {
    	System.out.println("[판페DAO] 음식 개수 - foodCount 메소드 실행 ");
    	System.out.println("[판페DAO] itemNumber : "+bussinessNumber);
    	
    	int answer= sqlSession.selectOne("storeManage.foodCount", bussinessNumber);
    	System.out.println("총 음식 개수 : "+answer);
    	return answer;
    }

    // INGREDIENT
    //재료 판매 등록
    public int addIngredient(ItemInsertDTO itemDTO) {
    	System.out.println("[판페DAO] 음식판매등록 - addFood 메소드 실행 ");
    	System.out.println("itemDTO : "+itemDTO);
        sqlSession.insert("storeManage.addIngredient", itemDTO);
        int itemNum = sqlSession.selectOne("storeManage.getItemNumber", itemDTO);
        System.out.println("itemNumber : "+itemNum);
        itemDTO.setItemNumber(itemNum);
        System.out.println("DTO itemNumber : "+itemDTO.getItemNumber());
        
        return itemDTO.getItemNumber();
    }
    // 재료 판매 상세
    public ItemListDTO detaileIngredient(int itemNumber) {
    	System.out.println("[판페DAO] 재료판매상세 - detailIngredient 메소드 실행");
    	System.out.println("itemNumber : "+itemNumber);
    	
        return sqlSession.selectOne("storeManage.detaileIngredient", itemNumber);
    }
    //재료 판매 수정
    public int editIngredient(ItemListDTO dto) {
    	System.out.println("[판페DAO] 재료판매수정 - editIngredient 메소드 실행");
    	System.out.println("itemDTO : "+ dto);
    	
        return sqlSession.update("storeManage.editIngredient", dto);
    }
    //재료 판매 삭제
    public int deleteIngredient(int itemNumber) {
    	System.out.println("[판페DAO] 재료판매삭제 - deleteIngredient 메소드 실행");
    	System.out.println("itemNumber : "+itemNumber);
    	
        return sqlSession.delete("storeManage.deleteingredient", itemNumber);
    }
    // 재료 판매 목록
    public List<ItemWithImgDTO> ingredientList(Map<String, Object> pageMap) {
    	System.out.println("[판페DAO] 재료판매목록 - ingredientList 메소드 실행");
    	System.out.println("[판페DAO] 재료판매목록 map :"+pageMap.toString());
    	System.out.println("[판페DAO]사업자번호 : "+pageMap.get("businessNumber"));
    	
    	List<ItemWithImgDTO> list = sqlSession.selectList("storeManage.ingredientList", pageMap);
    	return list;
    }
    // 음식 개수
    public int ingredientCount(String bussinessNumber) {
    	System.out.println("[판페DAO] 음식 개수 - foodCount 메소드 실행 ");
    	System.out.println("[판페DAO] itemNumber : "+bussinessNumber);
    	
    	int answer= sqlSession.selectOne("storeManage.ingredientCount", bussinessNumber);
    	System.out.println("총 음식 개수 : "+answer);
    	return answer;
    }
    
    // 가게 사진 조회
    public String selectStoreImageSystemName(String businessNumber) {
        return sqlSession.selectOne("storeManage.selectStoreImage", businessNumber);
    }
    // 

    //=== 판매 내역 ===========
    // 오늘 판매 내역
    public List<Map<String, Object>> todaySaleHistory(String businessNumber) {
    	System.out.println("[판페DAO]오늘판매내역 - todaySalehistory 메소드 실행");
    	System.out.println("[판페DAO]사업자 번호 : "+businessNumber);
        return sqlSession.selectList("storeManage.todaySaleHistory", businessNumber);
    }
    // 총 판매 내역
    public List<Map<String, Object>> totalSaleHistory(String businessNumber) {
    	System.out.println("[판페DAO]총판매내역 - totalSaleHistory 메소드 실행");
    	System.out.println("[판페DAO]사업자 번호 : "+businessNumber);
        return sqlSession.selectList("storeManage.totalSaleHistory", businessNumber);
    }
    

	// // 내 게시글 관리
    // 내 게시글 관리
    public List<PostDetailDTO> selectAllmyPost(Map<String, Integer> pageMap) {
		System.out.println("[판페DAO]모든 게시글 조회하기 - selectAllmypost 메소드 실행 : " + pageMap);
		List<PostDetailDTO> list = sqlSession.selectList("post.myPostSelect", pageMap);
		System.out.println("[판페DAO]조회결과 : " + list);
		return list;
	}
    // 내 게시글 총 개수
    public int myPostCount(Map<String, Integer> pageMap) {
		System.out.println("[판페DAO]내 게시글 총 개수 조회 - myPostCount 메소드 실행");
		int count = sqlSession.selectOne("post.myPostCount",pageMap);
		System.out.println("[판페DAO] 내 게시글 수 : "+count);
		return count;
	}
    
    // // 내 댓글 관리 
    // 내 댓글 관리
    public List<CommentListDTO> selectAllmyComment(Map<String, Integer> pageMap) {
		System.out.println("[판페DAO]모든 게시글 조회하기 - selectAllmypost 메소드 실행 : " + pageMap);
		List<CommentListDTO> list = sqlSession.selectList("myComment.myCommentSelect", pageMap);
		System.out.println("[판페DAO]조회결과 : " + list);
		return list;
	}
    // 내 댓글 총 개수
    public int myCommentCount(Map<String, Integer> pageMap) {
		System.out.println("[판페DAO]내 게시글 총 개수 조회 - myCommentCount 메소드 실행");
		int count = sqlSession.selectOne("myComment.myCommentCount",pageMap);
		System.out.println("[판페DAO] 내 댓글 수 : "+count);
		return count;
	}
    // // 내 리뷰 관리 
    // 내 리뷰 관리
    public List<ReviewWriteDTO> selectAllmyReview(Map<String, Integer> pageMap) {
    	System.out.println("[판페DAO]내 모든 리뷰 조회하기 - selectAllmyReview 메소드 실행 : " + pageMap);
    	List<ReviewWriteDTO> list = sqlSession.selectList("myReview.myReviewSelect", pageMap);
    	System.out.println("[판페DAO]조회결과 : " + list);
    	return list;
    }
    // 내 리뷰 총 개수
    public int myReviewCount(Map<String, Integer> pageMap) {
    	System.out.println("[판페DAO]내 게시글 총 개수 조회 - myReviewCount 메소드 실행");
    	int count = sqlSession.selectOne("myReview.myReviewCount",pageMap);
    	System.out.println("[판페DAO] 내 리뷰 수 : "+count);
    	return count;
    }
    // // 내 구매 내역 관리
    // 음식 구매내역
    public List<MyPurchaseDTO> myFoodPurchaseList(Map<String, Integer> pageMap){
    	System.out.println("[판페DAO]내 음식 구매 목록 조회 - myFoodPurchseList 메소드 실행");
    	List<MyPurchaseDTO> list = sqlSession.selectList("myOrder.myOrderFoodSelect", pageMap);
    	System.out.println("조회결과 : " + list);
    	return list;
    }
    // 음식 구매내역 갯수
    public int myFoodPurchaseCount(Map<String, Integer> pageMap) {
    	System.out.println("[판페DAO]내 음식 구매 목록 개수 조회 - myReviewCount 메소드 실행");
    	int count = sqlSession.selectOne("myOrder.myOrderFoodCount",pageMap);
    	System.out.println("[판페DAO] 내 음식 구매 수 : "+count);
    	return count;
    }
    // 재료 구매내역
    public List<MyPurchaseDTO> myIngrePurchaseList(Map<String, Integer> pageMap){
    	System.out.println("[판페DAO]내 음식 구매 목록 조회 - myIngrePurchaseList 메소드 실행");
    	List<MyPurchaseDTO> list = sqlSession.selectList("myOrder.myOrderIngreSelect", pageMap);
    	System.out.println("조회결과 : " + list);
    	return list;
    }
    // 재료 구매내역 갯수
    public int myIngrePurchaseCount(Map<String, Integer> pageMap) {
    	System.out.println("[판페DAO]내 음식 구매 목록 개수 조회 - myIngrePurchaseCount 메소드 실행");
    	int count = sqlSession.selectOne("myOrder.myOrderIngreCount",pageMap);
    	System.out.println("[판페DAO] 내 음식 구매 수 : "+count);
    	return count;
    }
   
    
    // // 내정보수정
    // 내정보 조회
    public SellerInfoDTO selectSellerInfo(int memberNumber) {
    	System.out.println("[판페DAO] 내정보 조회 selectSellerInfo");
        return sqlSession.selectOne("seller.selectSellerinfo", memberNumber);
    }

    // 내정보 수정
    public void updateSellerInfo(SellerInfoDTO dto) {
    	System.out.println("[판페DAO] 내정보수정 updateSellerInfo");
        sqlSession.update("seller.updateSellerInfo", dto);
    }

    // ==== 원산지 목록 =======
    public List<OriginDTO> selectOriginListByBusiness(String businessNumber) {
        return sqlSession.selectList("origin.originList", businessNumber);
    }

    public OriginDTO selectOriginOne(int originNumber) {
        return sqlSession.selectOne("origin.selectOne", originNumber);
    }

    public void insertOrigin(OriginDTO dto) {
        sqlSession.insert("origin.addOrigin", dto);
    }

    public int updateOriginByNumber(OriginDTO dto) {
        return sqlSession.update("origin.updateOrigin", dto);
    }

    public int deleteOriginByNumber(int originNumber) {
        return sqlSession.delete("origin.deleteOrigin", originNumber);
    }
    
    // ==== 판매내역 ======
    //총 판매 내역
    public List<SaleHistoryDTO> todaySaleList(Map<String, Object> pageMap){
    	System.out.println("[판페DAO]총 판매 목록 조회 - todaySaleList 메소드 실행");
    	List<SaleHistoryDTO> list = sqlSession.selectList("storeManage.todaySaleList", pageMap);
    	System.out.println("조회결과 : " + list);
    	return list;
    }
    // 총 판매 내역 갯수
    public int todaySaleCount(Map<String, Object> pageMap) {
    	System.out.println("[판페DAO] 총 판매 목록 개수 조회 - todaySaleCount 메소드 실행");
    	int count = sqlSession.selectOne("storeManage.todaySaleCount",pageMap);
    	System.out.println("[판페DAO] 목록 수 : "+count);
    	return count;
    }
    /** 판매내역 목록 (페이징) */
    public List<SaleHistoryDTO> salesHistoryList(Map<String, Object> params) {
        // params: businessNumber, startRow, endRow  (필수)
        // Mapper: storeManage.salesHistoryList
        return sqlSession.selectList("storeManage.salesHistoryList", params);
    }

    /** 판매내역 총 건수 (페이지네이션 계산용) */
    public int salesHistoryCount(Map<String, Object> params) {
        // params: businessNumber (필수)
        // Mapper: storeManage.salesHistoryCount
    	System.out.println("[sellerDAO] salesHistoryCount");
    	int count = sqlSession.selectOne("storeManage.salesHistoryCount", params);
        System.out.println("판매내역 건수 : "+count);
    	return count;
    }

    /** 요약 카드(오늘/이번달/누적) 금액 */
    public Map<String, Object> saleSummary(String businessNumber) {
        // Mapper: storeManage.saleSummary
        return sqlSession.selectMap("storeManage.saleSummary", businessNumber);
    }
    // === 리뷰 작성 =====
 // 리뷰 저장
    public int insertReview(ReviewDTO dto) {
        return sqlSession.insert("review.insertReview", dto); // selectKey로 reviewNumber 세팅됨
    }

    // 이미 리뷰 존재 여부
    public boolean existsReview(Map<String,Object> review) {
	    Integer cnt = sqlSession.selectOne("review.existsReview", review);
	    return cnt != null && cnt > 0;
    }
    
}
