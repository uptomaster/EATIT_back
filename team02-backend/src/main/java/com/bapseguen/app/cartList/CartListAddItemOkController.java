package com.bapseguen.app.cartList;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.dto.view.ItemSnapshotDTO;
import com.bapseguen.app.item.dao.ItemDAO;

// 장바구니에 메뉴를 담으면 보여질 화면.
public class CartListAddItemOkController implements Execute {

	@Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        HttpSession session = request.getSession();
        
        Integer memberNumber = (Integer)request.getSession().getAttribute("memberNumber");
        if(memberNumber == null) {
        	// 회원번호가 없으면 로그인페이지로 경로 설정
        	result.setPath("/member/login.me"); 
        	result.setRedirect(true);
        	return result;
        }
        
        // 파라미터 파싱
        int itemNumber = parseInt(request.getParameter("itemNumber"), -1);
        int quantity = parseInt(request.getParameter("quantity"), 1);
        
        if (itemNumber <= 0 || quantity <= 0) {
            session.setAttribute("cartError", "잘못된 요청입니다.");
            result.setPath("/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 아이템 스냅샷 조회(가격,재고,판매상태,가게번호)
        ItemDAO itemDAO = new ItemDAO();
        ItemSnapshotDTO snap = itemDAO.selectSnapshot(itemNumber);
        if(snap == null || !"Y".equalsIgnoreCase(null)) {
        	// 재고가 없을때는 판매 불가함
        	session.setAttribute("Error", "판매중이 아닌 상품입니다.");
        	result.setPath("/cartList/view.cl");
        	result.setRedirect(true); // 새로고침해야됨
        	return result;
        }
        
        if(snap.getItemQuantity() != null && quantity > snap.getItemQuantity()) {
        	// 메뉴의 수량이 존재하면서, 구매하려는 수량이 재고보다 많을때 => 구매 안됨.
        	session.setAttribute("Error", "재고가 부족합니다.");
        	result.setPath("/cartList/view.cl");
        	result.setRedirect(true);
        	return result;
        }
        
        
        //담으려는 itemNumber가 어느 가게의 상품인지 서버가 확정하기 위해서
        String newBusinessNumber = snap.getBusinessNumber();
        
        // 사용자가 개발자도구로 가격 조작 → 무효
        // 나중에 상품 가격이 바뀌어도 장바구니/주문 당시 가격을 그대로 보존(회계/정산의 기준)
        Integer itemPrice = snap.getItemPrice();

        // 가게번호/가격은 필수 무결성 값 → 없으면 에러 처리
        if (newBusinessNumber == null || newBusinessNumber.isBlank()) {
            session.setAttribute("cartError", "가게 정보가 올바르지 않습니다.");
            result.setPath("/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }
        if (itemPrice == null || itemPrice <= 0) {
            session.setAttribute("cartError", "가격 정보가 올바르지 않습니다.");
            result.setPath("/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }
        
        
        // OPEN 상태의 회원의 카트 확인
        CartListDAO cartDAO = new CartListDAO();
        CartDTO cartDTO = new CartDTO();
        
        cartDTO.setMemberNumber(memberNumber);
        
        // 내 OPEN 장바구니 유무 확인
        Integer cartNumber = cartDAO.selectOpenCartNumberByMember(cartDTO);
        
        // 카트가 없으면 생성
        if(cartNumber == null) {
        	// OPEN 상태의 장바구니가 없는 경우 -> 새 Store 생성
        	CartDTO newCartDTO = new CartDTO();
        	newCartDTO.setBusinessNumber(newBusinessNumber); // 담으려는 메뉴의 가게 사업자번호
        	// 이후 이 장바구니에는 같은 가게의 상품만 담게 될 것.
        	newCartDTO.setMemberNumber(memberNumber);
        	
        	cartDAO.insertCartIfNotExists(newCartDTO);
        	cartNumber = cartDAO.selectOpenCartNumberByMember(cartDTO);
        }
        else {
        	// OPEN 상태의 장바구니가 있는 경우 -> 가게는 하나만 담을 수 있음
        	
        	// 회원의 OPEN상태 장바구니의 항목
        	List<CartItemDTO> currentItems = cartDAO.selectCartItems(cartDTO); 
        	
        	if(currentItems == null || currentItems.isEmpty()) {
        		// 장바구니가 비워져있으면 가게 전환
        		CartDTO change = new CartDTO();
        		change.setCartNumber(cartNumber);
        		change.setBusinessNumber(newBusinessNumber);
        		cartDAO.updateCartBusinessNumber(change);
        	}
        	else {
        		// 장바구니가 비워져있지 않으면, 첫 항목의 가게로 추론한다.
        		int existingItemNo = currentItems.get(0).getItemNumber();
        		ItemSnapshotDTO firstSnap = itemDAO.selectSnapshot(existingItemNo);
        		String currentBN = (firstSnap != null) ? firstSnap.getBusinessNumber() : null;
        		
        		 if (currentBN == null || !currentBN.equals(newBusinessNumber)) {
                     // ⚠ 다른 가게 상품 → 확인 페이지로 유도
                     String redirect = request.getContextPath()
                             + "/cartList/changeStoreConfirm.cl"
                             + "?itemNumber=" + itemNumber
                             + "&quantity=" + quantity;
                     result.setPath(redirect);
                     result.setRedirect(true);
                     return result;
                 }
        		 // 같은 가게면 전환 안하고 그대로 진행.
        	}
        }
        
        
        // 장바구니에 담기. cartItem은 cart의 하위 요소(리스트로)
        CartItemDTO cartItem = new CartItemDTO();
        cartItem.setCartNumber(cartNumber);
        cartItem.setItemNumber(itemNumber);
        cartItem.setCartItemQuantity(quantity);
        cartItem.setCartItemPrice(itemPrice);
        
        cartDAO.insertCartItem(cartItem);
        
        
        
        
        // helper 사용해서 재사용하기
		private int parseInt(String s, int def) {
			if (s == null) return def;
			s = s.trim();
			if (s.isEmpty()) return def;
			try { return Integer.parseInt(s);
			} catch (NumberFormatException e) {
				return def;
			}
		}
		
	}
}

