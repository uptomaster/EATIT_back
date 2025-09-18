package com.bapseguen.app.cartList.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.config.MyBatisConfig;

public class CartListDAO {

    private SqlSession sqlSession;

    public CartListDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /** 회원 OPEN 장바구니 번호 조회 */
    public Integer selectOpenCartNumberByMember(CartDTO dto) {
        return sqlSession.selectOne("cartList.open", dto);
    }

    /** 장바구니 없을 때 생성 */
    public void insertCartIfNotExists(CartDTO dto) {
        sqlSession.insert("cartList.createIfNone", dto);
    }

    /** 회원 장바구니 아이템 목록 조회 */
    public List<CartItemDTO> selectCartItems(CartDTO dto) {
        return sqlSession.selectList("cartList.selectItemsList", dto);
    }

    /** 장바구니에 상품 단순 추가 */
    public void insertCartItem(CartItemDTO dto) {
        sqlSession.insert("cartList.insertCartItem", dto);
    }

    /** 장바구니 번호로 아이템 조회 (상세) */
    public List<CartItemDTO> selectCartItemsByCartNo(int cartNumber) {
        return sqlSession.selectList("cartList.selectCartItems", cartNumber);
    }

    /** 회원 OPEN 장바구니 삭제 */
    public void deleteOpenCartByMember(Integer memberNumber) {
        sqlSession.delete("cartList.deleteOpenCartByMember", memberNumber);
    }

    /** 장바구니번호로 사업자번호 조회 */
    public String selectCartBusinessNumberByCartNumber(Integer cartNumber) {
        return sqlSession.selectOne("cartList.cartBNByCartNo", cartNumber);
    }

    /** 장바구니 단건 삭제 */
    public void deleteCartItem(CartItemDTO dto) {
        sqlSession.delete("cartList.delItem", dto);
    }

    /** 장바구니 전체 비우기 */
    public void deleteCartAllItems(CartDTO dto) {
        sqlSession.delete("cartList.clear", dto);
    }

    /** 빈 OPEN 장바구니 가게번호 변경 */
    public void updateCartBusinessNumber(CartDTO dto) {
        sqlSession.update("cartList.setStore", dto);
    }

    /** UPSERT: 동일 상품이면 수량 증가, 없으면 삽입 */
    public void addOrUpdateCartItem(CartItemDTO item) {
        int exists = sqlSession.selectOne("cartList.existsCartItem", item);

        if (exists > 0) {
            sqlSession.update("cartList.increaseQtyIfExists", item);
        } else {
            sqlSession.insert("cartList.insertCartItem", item);
        }
    }

    /** 현재 회원의 OPEN 카트 아이템(가격 포함) */
    public List<CartItemDTO> selectCurrentCartItemsWithPrice(int memberNumber) {
        return sqlSession.selectList("cartList.selectCurrentCartItemsWithPrice", memberNumber);
    }

    /** 결제 성공 시 현재 OPEN 카트 마감 */
    public void closeCurrentCart(int memberNumber) {
        sqlSession.update("cartList.closeCurrentCart", memberNumber);
    }

    /** 장바구니 번호 기준 상세 목록 */
    public List<CartItemDTO> selectCartItems(int cartNumber) {
        return sqlSession.selectList("cartList.selectCartItems", cartNumber);
    }

    /** 회원 장바구니 총 금액 */
    public int sumAmountByMember(int memberNumber) {
        return sqlSession.selectOne("cartList.sumAmountByMember", memberNumber);
    }

    /** 장바구니 내 수량 변경 */
    public void updateQuantity(int memberNumber, int itemNumber, int quantity) {
        Map<String, Object> param = new HashMap<>();
        param.put("memberNumber", memberNumber);
        param.put("itemNumber", itemNumber);
        param.put("quantity", quantity);
        sqlSession.update("cartList.updateQuantity", param);
    }

    /** cartItemNumber 기준 수량 변경 */
    public void updateQuantityByCartItem(int cartItemNumber, int quantity) {
        Map<String, Object> param = new HashMap<>();
        param.put("cartItemNumber", cartItemNumber);
        param.put("quantity", quantity);
        sqlSession.update("cartList.updateQuantityByCartItem", param);
    }

    /** 같은 가게 내 추천상품 조회 */
    public List<ItemWithImgDTO> selectRecommendedItems(int memberNumber, String businessNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberNumber", memberNumber);
        params.put("businessNumber", businessNumber);
        return sqlSession.selectList("cartList.selectRecommendedItems", params);
    }

    /** 특정 ITEM_NUMBER → BUSINESS_NUMBER 가져오기 */
    public String findBusinessNumberByItem(int itemNumber) {
        return sqlSession.selectOne("cartList.findBusinessNumberByItem", itemNumber);
    }

    /** 특정 상품의 재고 수량 가져오기 */
    public Integer selectItemQuantity(int itemNumber) {
        return sqlSession.selectOne("cartList.selectItemQuantity", itemNumber);
    }

    /** 장바구니에 이미 담은 수량 가져오기 */
    public Integer selectCartItemQuantity(int cartNumber, int itemNumber) {
        Map<String, Object> param = new HashMap<>();
        param.put("cartNumber", cartNumber);
        param.put("itemNumber", itemNumber);
        return sqlSession.selectOne("cartList.selectCartItemQuantity", param);
    }

    /** 장바구니 담기 가능 여부 체크 */
    public boolean canAddToCart(int itemNumber, int requestQty) {
        Integer stock = sqlSession.selectOne("cartList.selectItemQuantity", itemNumber);
        return stock != null && stock >= requestQty;
    }
}
