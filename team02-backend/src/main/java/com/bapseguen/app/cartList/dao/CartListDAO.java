package com.bapseguen.app.cartList.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
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

    /** 장바구니에 메뉴 담기 (단순 insert) */
    public void insertCartItem(CartItemDTO dto) {
        sqlSession.insert("cartList.addItem", dto);
    }

    /** 회원 장바구니 아이템 목록 조회 */
    public List<CartItemDTO> selectCartItems(CartDTO dto) {
        return sqlSession.selectList("cartList.selectItemsList", dto);
    }

    /** 장바구니 번호로 아이템 조회 (상세: 이름, 이미지 포함) */
    public List<CartItemDTO> selectCartItemsByCartNo(int cartNumber) {
        return sqlSession.selectList("cartList.selectCartItems", cartNumber);
    }

    /** 회원 OPEN 장바구니 삭제 */
    public void deleteOpenCartByMember(Integer memberNumber) {
        sqlSession.delete("cartList.deleteOpenCartByMember", memberNumber);
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

    /** OPEN 장바구니 확보 (없으면 생성 후 번호 반환) */
    public Integer ensureOpenCart(CartDTO dto) {
        Integer cartNumber = selectOpenCartNumberByMember(dto);
        if (cartNumber == null) {
            insertCartIfNotExists(dto);
            cartNumber = selectOpenCartNumberByMember(dto);
        }
        return cartNumber;
    }

    /** 카트번호로 사업자번호 조회 */
    public String selectCartBusinessNumberByCartNumber(Integer cartNumber) {
        return sqlSession.selectOne("cartList.cartBNByCartNo", cartNumber);
    }

    /** 카트에 담긴 아이템 개수 */
    public Integer selectCartItemCountByCartNumber(Integer cartNumber) {
        return sqlSession.selectOne("cartList.countItems", cartNumber);
    }

    /** cartItemNumber → cartNumber */
    public Integer selectCartNumberByCartItemNumber(Integer cartItemNumber) {
        return sqlSession.selectOne("cartList.cartNoByCartItemNo", cartItemNumber);
    }

    /** UPSERT: 동일 상품이면 수량 증가, 없으면 새로 삽입 */
    public void addOrUpdateCartItem(CartItemDTO item) {
        int exists = sqlSession.selectOne("cartList.existsCartItem", item);
        System.out.println("[DEBUG] addOrUpdateCartItem: cart=" + item.getCartNumber()
                + ", item=" + item.getItemNumber()
                + ", qty=" + item.getCartItemQuantity()
                + ", exists=" + exists);

        if (exists > 0) {
            sqlSession.update("cartList.increaseQtyIfExists", item);
        } else {
            sqlSession.insert("cartList.insertCartItem", item);
        }
    }
}
