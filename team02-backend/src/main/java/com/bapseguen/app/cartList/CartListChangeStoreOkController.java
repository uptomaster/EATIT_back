package com.bapseguen.app.cartList;

import java.io.IOException;

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

// 다른 가게로 장바구니를 교체하는 컨트롤러
public class CartListChangeStoreOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Result result = new Result();
        HttpSession session = request.getSession();

        // 로그인 체크
        Integer memberNumber = (Integer) session.getAttribute("memberNumber");
        if (memberNumber == null) {
            result.setPath(request.getContextPath() + "/member/login.me");
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

        // 상품 스냅샷 조회
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

        if (snap.getItemQuantity() != null && (snap.getItemQuantity() <= 0 || quantity > snap.getItemQuantity())) {
            session.setAttribute("cartError", "재고가 부족합니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        String newBusinessNumber = snap.getBusinessNumber();
        Integer itemPrice = snap.getItemPrice();

        if (newBusinessNumber == null || newBusinessNumber.isBlank() || itemPrice == null || itemPrice <= 0) {
            session.setAttribute("cartError", "상품 정보가 올바르지 않습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 기존 장바구니 삭제
        CartListDAO cartDAO = new CartListDAO();
        cartDAO.deleteOpenCartByMember(memberNumber);

        // 새 장바구니 생성
        CartDTO newCart = new CartDTO();
        newCart.setMemberNumber(memberNumber);
        newCart.setBusinessNumber(newBusinessNumber);
        cartDAO.insertCartIfNotExists(newCart);

        // 새 장바구니 번호 가져오기
        Integer newCartNumber = cartDAO.selectOpenCartNumberByMember(newCart);
        if (newCartNumber == null) {
            session.setAttribute("cartError", "장바구니 생성 실패");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 새 장바구니에 아이템 추가
        CartItemDTO cartItem = new CartItemDTO();
        cartItem.setCartNumber(newCartNumber);
        cartItem.setItemNumber(itemNumber);
        cartItem.setCartItemQuantity(quantity);
        cartItem.setCartItemPrice(itemPrice);

        cartDAO.insertCartItem(cartItem);

        // 완료 알림
        session.setAttribute("cartNotice", "장바구니를 다른 가게로 교체했습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);
        return result;
    }

    // AddItemOkController와 동일한 parseInt helper
    private int parseInt(String s, int def) {
        if (s == null) return def;
        s = s.trim();
        if (s.isEmpty()) return def;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return def;
        }
    }
}
