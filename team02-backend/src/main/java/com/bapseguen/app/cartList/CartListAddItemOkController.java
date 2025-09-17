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

public class CartListAddItemOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        HttpSession session = request.getSession();

        // 로그인 확인
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/login/login.lo");
            result.setRedirect(true);
            return result;
        }

        // 파라미터 파싱
        int itemNumber = parseInt(request.getParameter("itemNumber"), -1);
        int quantity = parseInt(request.getParameter("quantity"), 1);

        if (itemNumber <= 0 || quantity <= 0) {
            session.setAttribute("cartError", "잘못된 요청입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 아이템 스냅샷 조회
        ItemDAO itemDAO = new ItemDAO();
        ItemSnapshotDTO snap = itemDAO.selectSnapshot(itemNumber);

        if (snap == null) {
            session.setAttribute("cartError", "존재하지 않는 상품입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }
        if (!"Y".equalsIgnoreCase(snap.getItemSellState())) {
            session.setAttribute("cartError", "판매중이 아닌 상품입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }
        if (snap.getItemQuantity() != null &&
            (snap.getItemQuantity() <= 0 || quantity > snap.getItemQuantity())) {
            session.setAttribute("cartError", "재고가 부족합니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 가게번호 & 가격 검증
        String newBusinessNumber = snap.getBusinessNumber();
        Integer itemPrice = snap.getItemPrice();
        if (newBusinessNumber == null || newBusinessNumber.isBlank()) {
            session.setAttribute("cartError", "가게 정보가 올바르지 않습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }
        if (itemPrice == null || itemPrice <= 0) {
            session.setAttribute("cartError", "가격 정보가 올바르지 않습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // OPEN 상태 장바구니 확보
        CartListDAO cartDAO = new CartListDAO();
        CartDTO cartDTO = new CartDTO();
        cartDTO.setMemberNumber(memberNumber);

        Integer cartNumber = cartDAO.selectOpenCartNumberByMember(cartDTO);

        if (cartNumber == null) {
            // 장바구니 없으면 생성
            CartDTO newCartDTO = new CartDTO();
            newCartDTO.setMemberNumber(memberNumber);
            newCartDTO.setBusinessNumber(newBusinessNumber);
            cartDAO.insertCartIfNotExists(newCartDTO);
            cartNumber = cartDAO.selectOpenCartNumberByMember(cartDTO);
        } else {
            // 장바구니 가게번호 확인
            List<CartItemDTO> currentItems = cartDAO.selectCartItemsByCartNo(cartNumber);

            if (currentItems == null || currentItems.isEmpty()) {
                // 비어있으면 가게 변경 허용
                CartDTO change = new CartDTO();
                change.setCartNumber(cartNumber);
                change.setBusinessNumber(newBusinessNumber);
                cartDAO.updateCartBusinessNumber(change);
            } else {
                // 첫 상품의 가게번호와 비교
                int existingItemNo = currentItems.get(0).getItemNumber();
                ItemSnapshotDTO firstSnap = itemDAO.selectSnapshot(existingItemNo);
                String currentBN = (firstSnap != null) ? firstSnap.getBusinessNumber() : null;

                if (currentBN == null || !currentBN.equals(newBusinessNumber)) {
                    String redirect = request.getContextPath()
                            + "/cartList/changeStoreConfirm.cl?itemNumber=" + itemNumber
                            + "&quantity=" + quantity;
                    result.setPath(redirect);
                    result.setRedirect(true);
                    return result;
                }
            }
        }

        // 이미 담긴 수량 + 새 수량 ≤ 재고 체크
        Integer currentQty = cartDAO.selectCartItemQuantity(cartNumber, itemNumber);
        if (currentQty == null) currentQty = 0;
        int totalQty = currentQty + quantity;

        if (snap.getItemQuantity() != null && totalQty > snap.getItemQuantity()) {
            session.setAttribute("cartError", "재고보다 많이 담을 수 없습니다. (최대 " + snap.getItemQuantity() + "개)");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 장바구니 아이템 생성
        CartItemDTO cartItem = new CartItemDTO();
        cartItem.setCartNumber(cartNumber);
        cartItem.setItemNumber(itemNumber);
        cartItem.setCartItemQuantity(quantity);
        cartItem.setCartItemPrice(itemPrice);

        // UPSERT 실행
        cartDAO.addOrUpdateCartItem(cartItem);

        // 완료 후 뷰로 이동
        session.setAttribute("cartNotice", "장바구니에 담았습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);
        return result;
    }

    private int parseInt(String s, int def) {
        if (s == null) return def;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
