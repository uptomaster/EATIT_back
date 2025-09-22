package com.bapseguen.app.community;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.community.dao.CommunityDAO;
import com.bapseguen.app.dto.FaqDTO;
import com.bapseguen.app.dto.view.InquiryDetailDTO;
import com.google.gson.Gson;

public class FaqSearchController {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("q");
		CommunityDAO dao = new CommunityDAO();

		List<FaqDTO> results = dao.faqSearch(query);

		resp.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(results, resp.getWriter()); // ✅ JSON으로 반환
	}
}
