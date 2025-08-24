package com.bapseguen.app.cartList.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.config.MyBatisConfig;

public class CartListDAO {
	
    private SqlSession sqlSession;

    public CartListDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // auto-commit
    }

    /** 회원의 OPEN상태의 장바구니 번호 조회하기 */
    public Integer selectOpenCartNumberByMember(CartDTO dto) {
        return sqlSession.selectOne("cartList.open", dto);
    }

    /** 장바구니 없는 상태일때 생성하ㄱ;ㅣ */
    public void insertCartIfNotExists(CartDTO dto) {
        sqlSession.insert("cartList.createIfNone", dto);
    }

    /** 장바구니에 메뉴 담기 */
    public void insertCartItem(CartItemDTO dto) {
        sqlSession.insert("cartList.addItem", dto);
    }

    /** 장바구니 목록 조회하기 */
    public List<CartItemDTO> selectCartItems(CartDTO dto) {
        return sqlSession.selectList("cartList.selectItemsList", dto);
    }

    /** 장바구니에 담긴 메뉴 단건 삭제 */
    public void deleteCartItem(CartItemDTO dto) {
        sqlSession.delete("cartList.delItem", dto);
    }

    /** 장바구니에 담긴 모든 메뉴 삭제 */
    public void deleteCartAllItems(CartDTO dto) {
        sqlSession.delete("cartList.clear", dto);
    }

    /** 빈 OPEN 상태의 장바구니의 가게번호 변경(이미담긴 상태에서 다른 가게 담기) */
    public void updateCartBusinessNumber(CartDTO dto) {
        sqlSession.update("cartList.setStore", dto);
    }
    
    /** OPEN 장바구니 확보: 없으면 장바구니 생성 후 번호 반환하는 메소드 */
    public Integer ensureOpenCart(CartDTO dto) {
        Integer cartNumber = selectOpenCartNumberByMember(dto);
        if (cartNumber == null) {
            insertCartIfNotExists(dto);
            cartNumber = selectOpenCartNumberByMember(dto);
        }
        return cartNumber;
    }
    
    /** 카트 번호로 사업자번호 조회 */
    public String selectCartBusinessNumberByCartNumber(Integer cartNumber) {
        return sqlSession.selectOne("cartList.cartBNByCartNo", cartNumber);
    }

    /** 카트에 담긴 아이템 개수 */
    public Integer selectCartItemCountByCartNumber(Integer cartNumber) {
        return sqlSession.selectOne("cartList.countItems", cartNumber);
    }
    
}
