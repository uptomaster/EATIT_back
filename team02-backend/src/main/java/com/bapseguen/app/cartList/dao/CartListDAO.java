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

	// 사용자가 장바구니에 물건을 담거나 장바구니를 볼 때
	// 현재 회원이 "OPEN 상태 장바구니를 가지고있는지 확인할 때 사용.
	public Integer selectOpenCartNumberByMember(CartDTO dto) {
		return sqlSession.selectOne("cartList.open", dto);
	}

	/** 장바구니 없을 때 생성 */
	// 사용자가 처음 장바구니에 물건을 담을 때,
	// 아직 OPEN 상태 장바구니가 없으면 새로 만들어주는 경우.
	public void insertCartIfNotExists(CartDTO dto) {
		sqlSession.insert("cartList.createIfNone", dto);
	}

	/** 회원 장바구니 아이템 목록 조회 */
	// 로그인한 회원이 가진 OPEN 장바구니 항목 전부 조회
	public List<CartItemDTO> selectCartItems(CartDTO dto) {
		return sqlSession.selectList("cartList.selectItemsList", dto);
	}

	// 장바구니에 상품을 추가할 때 사용
	/** 장바구니에 메뉴 담기 (단순 insert) */
	public void insertCartItem(CartItemDTO dto) {
		sqlSession.insert("cartList.insertCartItem", dto);
	}

	/** 장바구니 번호로 아이템 조회 (상세: 이름, 이미지 포함) */
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

	/** UPSERT: 동일 상품이면 수량 증가, 없으면 새로 삽입 */
	public void addOrUpdateCartItem(CartItemDTO item) {
		int exists = sqlSession.selectOne("cartList.existsCartItem", item);
		System.out.println("[DEBUG] addOrUpdateCartItem: cart=" + item.getCartNumber() + ", item="
				+ item.getItemNumber() + ", qty=" + item.getCartItemQuantity() + ", exists=" + exists);

		if (exists > 0) {
			sqlSession.update("cartList.increaseQtyIfExists", item);
		} else {
			sqlSession.insert("cartList.insertCartItem", item);
		}
	}

	// ① 현재 회원의 OPEN 카트 아이템(가격 포함) 조회
	public List<CartItemDTO> selectCurrentCartItemsWithPrice(int memberNumber) {
		return sqlSession.selectList("cartList.selectCurrentCartItemsWithPrice", memberNumber);
	}

	// ② 결제 성공 시 현재 OPEN 카트 마감(CLOSED)
	public void closeCurrentCart(int memberNumber) {
		sqlSession.update("cartList.closeCurrentCart", memberNumber);
	}

}
