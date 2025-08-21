package com.bapseguen.app.cartList.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.config.MyBatisConfig;

public class CartListDAO {
    private final SqlSession sqlSession;

    public CartListDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true); // auto-commit
    }

    /** 회원의 OPEN 카트 번호 조회 */
    public Integer selectOpenCartNumberByMember(CartDTO dto) {
        return sqlSession.selectOne("cartList.selectOpenCartNumberByMember", dto);
    }

    /** 카트 없으면 생성 */
    public void insertCartIfNotExists(CartDTO dto) {
        sqlSession.insert("cartList.insertCartIfNotExists", dto);
    }

    /** 카트 아이템 담기 */
    public void insertCartItem(CartItemDTO dto) {
        sqlSession.insert("cartList.insertCartItem", dto);
    }

    /** 카트 아이템 목록 (memberNumber 기준) */
    public List<CartItemDTO> selectCartItems(CartDTO dto) {
        return sqlSession.selectList("cartList.selectCartItems", dto);
    }

    /** 카트 아이템 단건 삭제 */
    public void deleteCartItem(CartItemDTO dto) {
        sqlSession.delete("cartList.deleteCartItem", dto);
    }

    /** 카트 모든 아이템 삭제 */
    public void deleteCartAllItems(CartDTO dto) {
        sqlSession.delete("cartList.deleteCartAllItems", dto);
    }

    /** 사이드 뱃지 숫자 */
    public Integer selectCartItemCount(CartDTO dto) {
        return sqlSession.selectOne("cartList.selectCartItemCount", dto);
    }
}
