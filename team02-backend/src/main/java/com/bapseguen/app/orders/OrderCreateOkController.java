package com.bapseguen.app.orders;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.cartList.dao.CartListDAO;
import com.bapseguen.app.dto.CartDTO;
import com.bapseguen.app.dto.CartItemDTO;
import com.bapseguen.app.dto.OrdersDTO;
import com.bapseguen.app.orders.dao.OrdersDAO;

public class OrderCreateOkController implements Execute {

   @Override
   public Result execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      Integer memberNumber = (Integer) request.getSession().getAttribute("memberNumber");
      if (memberNumber == null) {
         Result r = new Result();
         r.setRedirect(true);
         r.setPath(request.getContextPath() + "/login/login.lo");
         return r;
      }

      CartListDAO cartDAO = new CartListDAO();

      // ① 현재 장바구니 합계 (DB 기준 최신)
      List<CartItemDTO> items = cartDAO.selectCurrentCartItemsWithPrice(memberNumber);
      long total = 0L;
      if (items != null) {
         for (CartItemDTO it : items) {
            total += (long) it.getCartItemPrice() * it.getCartItemQuantity();
         }
      }
      if (total <= 0) {
         request.setAttribute("error", "장바구니가 비어 있습니다.");
         Result r = new Result();
         r.setRedirect(false);
         r.setPath("/app/cartList/shoppingList.jsp");
         return r;
      }

      // (선택) ② 현재 OPEN 카트의 가게번호 추출 (기존 DAO만 사용)
      String businessNumber = null;
      try {
         CartDTO cart = new CartDTO();
         cart.setMemberNumber(memberNumber);
         Integer cartNumber = cartDAO.selectOpenCartNumberByMember(cart);
         if (cartNumber != null) {
            businessNumber = cartDAO.selectCartBusinessNumberByCartNumber(cartNumber);
         }
      } catch (Exception ignore) {
         // 가게번호가 꼭 필요하지 않다면 무시 가능
      }

      // ③ 매번 새 orderId 발급
      String orderId = makeOrderId(memberNumber);

      // ④ READY 주문 INSERT
      OrdersDTO dto = new OrdersDTO();
      dto.setOrderId(orderId);
      dto.setOrdersMemberNumber(memberNumber);
      dto.setOrdersTotalAmount((int) Math.min(Integer.MAX_VALUE, total));
      dto.setOrdersPaymentStatus("READY");
      if (businessNumber != null) {
         dto.setBusinessNumber(businessNumber); // DTO/매퍼에 해당 컬럼이 있을 때만
      }

      OrdersDAO odao = new OrdersDAO();
      try {
         odao.insertOrder(dto);
      } catch (Exception e) {
         e.printStackTrace();
         request.setAttribute("error", "주문 생성 중 오류가 발생했습니다.");
         Result r = new Result();
         r.setRedirect(false);
         r.setPath("/app/cartList/shoppingList.jsp");
         return r;
      } finally {
         try {
            odao.close();
         } catch (Exception ignore) {
         }
      }

      // ⑤ 결제 준비로 forward (request attribute 유지)
      request.setAttribute("orderId", orderId);
      request.setAttribute("amount", dto.getOrdersTotalAmount());
      String name = (String) request.getSession().getAttribute("memberName");
      request.setAttribute("customerName", (name == null || name.isBlank()) ? "고객" : name);

      Result r = new Result();
      r.setRedirect(false);
      r.setPath("/orders/paymentReady.or");
      return r;
   }

   private static String makeOrderId(long memberNumber) {
      String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
      String rand = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
      String id = "ORD-" + ts + "-" + memberNumber + "-" + rand;
      return id.length() > 64 ? id.substring(0, 64) : id;
   }
}
