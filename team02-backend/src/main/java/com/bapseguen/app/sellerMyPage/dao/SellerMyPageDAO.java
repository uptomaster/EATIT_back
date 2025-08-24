package com.bapseguen.app.sellerMyPage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CommentDTO;
import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.OriginDTO;
import com.bapseguen.app.dto.ReviewDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.PostDetailDTO;
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
//	public SellerInfoDTO takeSellerInfoDTO(int memberNumber) {
//		System.out.println("[판페DAO] seller의 모든 정보 불러오기===");
//		System.out.println("회원 번호 : "+memberNumber);
//        return sqlSession.selectOne("seller.takeSellerinfo", memberNumber);
//	}
	
    // Item Image - needs businessNumber for MAX(ITEM_NUMBER) subquery
    // 메뉴 사진 저장
    public int addItemImage(ItemImageDTO imageDTO, String businessNumber) {
    	System.out.println("[판페DAO] 메뉴 사진 저장 - addItemImage 메소드 실행");
    	System.out.println("사업자번호 : "+businessNumber);
    	System.out.println("imageDTO : "+imageDTO);
    	
        Map<String, Object> param = new HashMap<>();
        param.put("[판페DAO] itemImageNumber", imageDTO.getItemImageNumber());
        param.put("[판페DAO] itemImageSystemName", imageDTO.getItemImageSystemName());
        param.put("[판페DAO] itemImageOriginalName", imageDTO.getItemImageOriginalName());
        param.put("[판페DAO] businessNumber", businessNumber);
        return sqlSession.insert("storeManage.addItemImage", param);
    }

    // 사업장 장보
//    public SellerInfoDTO selectStoreInfo(String businessNumber) {
//    	System.out.println("사업장 정보 출력 - selectStoreInfo 메소드 실행");
//    	System.out.println("사업자 번호 : "+businessNumber);
//        return sqlSession.selectOne("storeManage.selectStoreInfo", businessNumber);
//    }
    
    //음식 판매 목록
    public List<Map<String, Object>> foodList(String businessNumber) {
    	System.out.println("[판페DAO] 음식판매목록 - foodList 메소드 실행");
    	System.out.println("[판페DAO] 사업자 번호 : "+businessNumber);
    	
        return sqlSession.selectList("storeManage.foodList", businessNumber);
    }
	// 비밀번호 확인
//	public int
	
	// 음식 판매 등록
    public int addFood(ItemDTO itemDTO) {
    	System.out.println("[판페DAO] 음식판매등록 - addFood 메소드 실행 ");
    	System.out.println("itemDTO : "+itemDTO);
        int insert = sqlSession.insert("storeManage.addFood", itemDTO);
        System.out.println("inert 결과 : "+insert);
        System.out.println("itemNumber : "+itemDTO.getItemNumber());
        return itemDTO.getItemNumber();
    }
    // 음식 메뉴 상세
    public ItemWithImgDTO detaileFood(int itemNumber) {
    	System.out.println("[판페DAO] 음식메뉴상세 - detailFood 메소드 실행 ");
    	System.out.println("[판페DAO] itemNumber : "+itemNumber);
    	
    	ItemWithImgDTO answer= sqlSession.selectOne("storeManage.detaileFood", itemNumber);
    	return answer;
    }
    // 음식 판매 수정
    public int editFood(ItemDTO dto) {
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

    // INGREDIENT
    //재료 판매 등록
    public int addIngredient(ItemDTO dto) {
    	System.out.println("[판페] 재료판매등록 - addIngredient 메소드 실행");
    	System.out.println("[판페] itemDTO : "+dto);
    	
        return sqlSession.insert("storeManage.addIngredient", dto);
    }
    // 재료 판매 상세
    public ItemDTO detaileIngredient(int itemNumber) {
    	System.out.println("[판페] 재료판매상세 - detailIngredient 메소드 실행");
    	System.out.println("itemNumber : "+itemNumber);
    	
        return sqlSession.selectOne("storeManage.detaileIngredient", itemNumber);
    }
    //재료 판매 수정
    public int editIngredient(ItemDTO dto) {
    	System.out.println("[판페] 재료판매수정 - editIngredient 메소드 실행");
    	System.out.println("itemDTO : "+ dto);
    	
        return sqlSession.update("storeManage.editIngredient", dto);
    }
    //재료 판매 삭제
    public int deleteIngredient(int itemNumber) {
    	System.out.println("[판페] 재료판매삭제 - deleteIngredient 메소드 실행");
    	System.out.println("itemNumber : "+itemNumber);
    	
        return sqlSession.delete("storeManage.deleteingredient", itemNumber);
    }
    // 재료 판매 목록
    public List<Map<String, Object>> ingredientList(String businessNumber) {
    	System.out.println("[판페] 재료판매목록 - ingredientList 메소드 실행");
    	System.out.println("사업자번호 : "+businessNumber);
    	
        return sqlSession.selectList("storeManage.ingredientList", businessNumber);
    }
    


    // 판매 내역
    // 오늘 판매 내역
    public List<Map<String, Object>> todaySaleHistory(String businessNumber) {
    	System.out.println("오늘판매내역 - todaySalehistory 메소드 실행");
    	System.out.println("사업자 번호 : "+businessNumber);
        return sqlSession.selectList("storeManage.todaySaleHistory", businessNumber);
    }
    // 총 판매 내역
    public List<Map<String, Object>> totalSaleHistory(String businessNumber) {
    	System.out.println("총판매내역 - totalSaleHistory 메소드 실행");
    	System.out.println("사업자 번호 : "+businessNumber);
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
		System.out.println("모든 게시글 조회하기 - selectAllmypost 메소드 실행 : " + pageMap);
		List<PostDetailDTO> list = sqlSession.selectList("post.myPostSelect", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
    // 내 게시글 총 개수
    public int myPostCount(Map<String, Integer> pageMap) {
		System.out.println("내 게시글 총 개수 조회 - myPostCount 메소드 실행");
		int count = sqlSession.selectOne("post.myPostCount",pageMap);
		System.out.println("[판페DAO] 내 게시글 수 : "+count);
		return count;
	}
    
    // // 내 댓글 관리 
    // 내 댓글 관리
    public List<CommentDTO> selectAllmyComment(Map<String, Integer> pageMap) {
		System.out.println("모든 게시글 조회하기 - selectAllmypost 메소드 실행 : " + pageMap);
		List<CommentDTO> list = sqlSession.selectList("myComment.myPostSelect", pageMap);
		System.out.println("조회결과 : " + list);
		return list;
	}
    // 내 댓글 총 개수
    public int myCommentCount(Map<String, Integer> pageMap) {
		System.out.println("내 게시글 총 개수 조회 - myCommentCount 메소드 실행");
		int count = sqlSession.selectOne("myComment.myCommentCount",pageMap);
		System.out.println("[판페DAO] 내 댓글 수 : "+count);
		return count;
	}
    // // 내 리뷰 관리 
    // 내 리뷰 관리
    public List<ReviewDTO> selectAllmyReview(Map<String, Integer> pageMap) {
    	System.out.println("내 모든 리뷰 조회하기 - selectAllmyReview 메소드 실행 : " + pageMap);
    	List<ReviewDTO> list = sqlSession.selectList("myReview.myReviewSelect", pageMap);
    	System.out.println("조회결과 : " + list);
    	return list;
    }
    // 내 리뷰 총 개수
    public int myReviewCount(Map<String, Integer> pageMap) {
    	System.out.println("내 게시글 총 개수 조회 - myReviewCount 메소드 실행");
    	int count = sqlSession.selectOne("myReview.myReviewCount",pageMap);
    	System.out.println("[판페DAO] 내 리뷰 수 : "+count);
    	return count;
    }
    
}
