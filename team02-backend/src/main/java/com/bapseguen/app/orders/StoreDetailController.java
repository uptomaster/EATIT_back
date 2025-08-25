package com.bapseguen.app.orders;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.item.dao.ItemDAO;

public class StoreDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("▶ StoreDetailController 진입"); // 콘솔 로그

		Result result = new Result();
		ItemDAO itemDAO = new ItemDAO();

		// -------------------- 파라미터 --------------------
		int itemNumber = -1;
		try {
			itemNumber = Integer.parseInt(request.getParameter("itemNumber"));
		} catch (NumberFormatException e) {
			// itemNumber가 없거나 잘못된 경우 → 목록으로 리다이렉트
			result.setPath(request.getContextPath() + "/orders/storeList.or");
			result.setRedirect(true);
			return result;
		}

		// -------------------- DAO 호출 --------------------
		ItemWithImgDTO item = itemDAO.selectItemDetail(itemNumber); // 상품 상세
		List<ItemImageDTO> images = itemDAO.selectItemImages(itemNumber); // 상품 이미지들

		if (item == null) {
			// 없는 상품 번호라면 목록으로 이동
			result.setPath(request.getContextPath() + "/orders/storeList.or");
			result.setRedirect(true);
			return result;
		}

		// -------------------- request 바인딩 --------------------
		request.setAttribute("item", item);
		request.setAttribute("images", images);

		// -------------------- 뷰 지정 --------------------
		result.setPath("/app/orders/storeDetail.jsp");
		result.setRedirect(false); // forward (request 데이터 유지)
		return result;
	}
}
