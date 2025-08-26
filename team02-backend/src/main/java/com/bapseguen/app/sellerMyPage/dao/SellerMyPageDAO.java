package com.bapseguen.app.sellerMyPage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemListDTO;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.dto.view.CommentListDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MyPurchaseDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
import com.bapseguen.app.dto.view.ReviewWriteDTO;
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
    public int addFood(ItemWithImgDTO itemDTO) {
    	System.out.println("[판페DAO] 음식판매등록 - addFood 메소드 실행 ");
    	System.out.println("itemDTO : "+itemDTO);
        sqlSession.insert("storeManage.addFood", itemDTO);
        int itemNum = sqlSession.selectOne("storeManage.getLastItemNumber", itemDTO);
        System.out.println("itemNumber : "+itemNum);
        return itemDTO.getItemNumber();
    }
    
    
    // 음식 메뉴 상세
    public ItemListDTO detaileFood(int itemNumber) {
    	System.out.println("[판페DAO] 음식메뉴상세 - detailFood 메소드 실행 ");
    	System.out.println("[판페DAO] itemNumber : "+itemNumber);
    	
    	ItemListDTO answer= sqlSession.selectOne("storeManage.detaileFood", itemNumber);
    	return answer;
    }
    // 음식 판매 수정
    public int editFood(ItemListDTO dto) {
    	System.out.println("[판페DAO] 음식판매 수정 - editFood 메소드 실행");
    	System.out.println("[판페DAO] itemDTO : "+dto);
    	
        return sqlSession.update("storeManage.editFood", dto);
    }
    // 음식 판매 삭제
    public int deleteFood(int itemNumber) {
    	System.out.println("[판페DAO] 음식판매삭제 - deleteFood 메소드 실행");
    	System.out.println("[판페DAO] itemNumber : "+itemNumber);
    	
        return sqlSession.delete("storeManage.deleteFood", itemNumber);
    }

    // 이미 준비된 음식
    public int alreadyFood(ItemDTO dto) {
    	System.out.println("[판페DAO] 이미 등록한 음식 - alreadyFood 메소드 실행");
    	System.out.println("itemDTO : "+dto);
    	
        return sqlSession.selectOne("storeManage.alreadyfood", dto);
    }
    //

    // INGREDIENT
    //재료 판매 등록
    public int addIngredient(ItemListDTO dto) {
    	System.out.println("[판페DAO] 재료판매등록 - addIngredient 메소드 실행");
    	System.out.println("[판페DAO] itemDTO : "+dto);
    	
        return sqlSession.insert("storeManage.addIngredient", dto);
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
    


    // 판매 내역
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
    
    // 원산지
    //원산지 정보 추가
    public int addOrigin(OriginDTO dto) {
    	System.out.println();
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
    	List<MyPurchaseDTO> list = sqlSession.selectList("myOrder.myOrderSelect", pageMap);
    	System.out.println("조회결과 : " + list);
    	return list;
    }
    // 음식 구매내역 갯수
    public int myFoodPurchaseCount(Map<String, Integer> pageMap) {
    	System.out.println("[판페DAO]내 게시글 총 개수 조회 - myReviewCount 메소드 실행");
    	int count = sqlSession.selectOne("myOrder.myOrderCount",pageMap);
    	System.out.println("[판페DAO] 내 리뷰 수 : "+count);
    	return count;
    }
    // 재료 구매내역
    // 재료 구매내역 갯수
    
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
    
}
