package com.bapseguen.app.cartList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.view.ItemSnapshotDTO;
import com.bapseguen.app.item.dao.ItemDAO;

public class CartListChangeStoreConfirmController implements Execute {
	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Result result = new Result();
		HttpSession session = request.getSession();

		Integer memberNumber = (Integer) session.getAttribute("memberNumber");
		if (memberNumber == null) {
			result.setPath(request.getContextPath() + "/member/login.me");
			result.setRedirect(true);
			return result;
		}

		int itemNumber = parseInt(request.getParameter("itemNumber"), -1);
		int quantity = parseInt(request.getParameter("quantity"), 1);
		if (itemNumber <= 0 || quantity <= 0) {
			session.setAttribute("cartError", "잘못된 요청입니다.");
			result.setPath(request.getContextPath() + "/cartList/view.cl");
			result.setRedirect(true);
			return result;
		}

		ItemDAO itemDAO = new ItemDAO();
		ItemSnapshotDTO snap = itemDAO.selectSnapshot(itemNumber);
		if (snap == null) {
			session.setAttribute("cartError", "존재하지 않는 상품입니다.");
			result.setPath(request.getContextPath() + "/cartList/view.cl");
			result.setRedirect(true);
			return result;
		}

		// 화면에 표시할 데이터 바인딩
		request.setAttribute("itemNumber", itemNumber);
		request.setAttribute("quantity", quantity);
		request.setAttribute("snap", snap);

		result.setPath(request.getContextPath() + "/app/cartList/changeStoreConfirm.jsp");
		result.setRedirect(false); // forward
		return result;
	}

	private int parseInt(String s, int def) {
		if (s == null)
			return def;
		s = s.trim();
		if (s.isEmpty())
			return def;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return def;
		}
	}
}
