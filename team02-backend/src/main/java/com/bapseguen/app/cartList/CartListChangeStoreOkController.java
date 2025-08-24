package com.bapseguen.app.cartList;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.dto.view.ItemSnapshotDTO;
// ItemDAO/스냅샷 DAO/매퍼는 너희가 구성한 위치에 맞춰 패키지 경로를 맞춰줘.
import com.bapseguen.app.item.dao.ItemDAO;

public class CartListChangeStoreOkController {

    public Result execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Result result = new Result();
        HttpSession session = request.getSession(false);

        // 0) 로그인 확인
        Integer memberNumber = (session != null) ? (Integer) session.getAttribute("memberNumber") : null;
        if (memberNumber == null) {
            if (session != null) session.setAttribute("loginRequired", "로그인이 필요합니다.");
            result.setPath(request.getContextPath() + "/member/login.me");
            result.setRedirect(true);
            return result;
        }

        // 1) 파라미터 파싱
        int itemNumber = parseInt(request.getParameter("itemNumber"), -1);
        int qty        = parseInt(request.getParameter("quantity"), 1);
        if (itemNumber <= 0 || qty <= 0) {
            session.setAttribute("cartError", "잘못된 요청입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 2) 담으려는 상품 스냅샷 조회 (가게/가격/판매상태/재고 등)
        ItemDAO itemDAO = new ItemDAO();
        ItemSnapshotDTO snap = itemDAO.selectSnapshot(itemNumber);
        if (snap == null) {
            session.setAttribute("cartError", "상품 정보를 찾을 수 없습니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        // 판매 가능 여부 / 재고 체크 (필요 시 너희 스키마에 맞춰 필드명 수정)
        if (!"Y".equals(snap.getSellState())) {
            session.setAttribute("cartError", "판매 중이 아닌 상품입니다.");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }
        Integer stock = snap.getItemQuantity(); // null이면 재고 미관리로 간주
        if (stock != null && qty > stock) {
            session.setAttribute("cartError", "재고가 부족합니다. (잔여: " + stock + ")");
            result.setPath(request.getContextPath() + "/cartList/view.cl");
            result.setRedirect(true);
            return result;
        }

        String newBusinessNumber = snap.getBusinessNumber();
        Integer itemPrice = snap.getItemPrice();
        if (itemPrice == null) itemPrice = 0; // 가격 미전달 대비(정합성 위해 서버 조회를 원칙으로)

        CartListDAO cartDAO = new CartListDAO();

        // 3) 현재 회원의 OPEN 카트 조회
        CartDTO criteria = new CartDTO();
        criteria.setMemberNumber(memberNumber);
        Integer cartNumber = cartDAO.selectOpenCartNumberByMember(criteria);

        if (cartNumber == null) {
            // (A) OPEN 카트 없음 → 새 가게로 장바구니 생성
            CartDTO create = new CartDTO();
            create.setMemberNumber(memberNumber);
            create.setBusinessNumber(newBusinessNumber);
            cartDAO.insertCartIfNotExists(create);
            cartNumber = cartDAO.selectOpenCartNumberByMember(criteria);
            if (cartNumber == null) {
                session.setAttribute("cartError", "장바구니 생성에 실패했습니다.");
                result.setPath(request.getContextPath() + "/cartList/view.cl");
                result.setRedirect(true);
                return result;
            }
        } else {
            // (B) OPEN 카트 존재 → 기존 아이템 전부 삭제 후 가게 전환
            CartDTO clearDto = new CartDTO();
            clearDto.setCartNumber(cartNumber);
            cartDAO.deleteCartAllItems(clearDto);

            CartDTO setStoreDto = new CartDTO();
            setStoreDto.setCartNumber(cartNumber);
            setStoreDto.setBusinessNumber(newBusinessNumber);
            cartDAO.updateCartBusinessNumber(setStoreDto);
        }

        // 4) 새 가게 상품 담기
        CartItemDTO itemDto = new CartItemDTO();
        itemDto.setCartNumber(cartNumber);
        itemDto.setItemNumber(itemNumber);
        itemDto.setCartItemPrice(itemPrice);
        itemDto.setCartItemQuantity(qty);
        cartDAO.insertCartItem(itemDto);

        // 5) 완료: 장바구니 화면으로
        session.setAttribute("cartToast", "가게를 변경하고 상품을 담았습니다.");
        result.setPath(request.getContextPath() + "/cartList/view.cl");
        result.setRedirect(true);
        return result;
    }

    private int parseInt(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
