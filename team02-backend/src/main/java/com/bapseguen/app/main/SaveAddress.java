package com.bapseguen.app.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveAddressServlet
 */
public class SaveAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  private static final String KAKAO_API_KEY = "8514b0d5ac16b8fc127cdd93a6bddb58";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");

	    // 요청 본문 읽기
	    StringBuilder sb = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        sb.append(line);
	    }
	    String requestBody = sb.toString();
	    System.out.println("받은 주소 데이터: " + requestBody);

	    // 단순 String 처리로 roadAddress 추출
	    String roadAddress = "";
	    int idx = requestBody.indexOf("\"roadAddress\"");
	    if (idx >= 0) {
	        int start = requestBody.indexOf("\"", idx + 14) + 1; // 값 시작 위치
	        int end = requestBody.indexOf("\"", start);           // 값 끝 위치
	        roadAddress = requestBody.substring(start, end);
	    }

	    if (roadAddress.isEmpty()) {
	        // fallback: jibunAddress
	        idx = requestBody.indexOf("\"jibunAddress\"");
	        if (idx >= 0) {
	            int start = requestBody.indexOf("\"", idx + 15) + 1;
	            int end = requestBody.indexOf("\"", start);
	            roadAddress = requestBody.substring(start, end);
	        }
	    }

	    if (roadAddress.isEmpty()) {
	        resp.setContentType("application/json; charset=UTF-8");
	        resp.getWriter().print("{\"success\":false,\"message\":\"주소가 비어 있습니다.\"}");
	        return;
	    }

	    // 카카오 API 호출
	    String query = java.net.URLEncoder.encode(roadAddress, "UTF-8");
	    String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json?query=" + query;

	    URL url = new URL(apiUrl);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Authorization", "8514b0d5ac16b8fc127cdd93a6bddb58");

	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    StringBuilder result = new StringBuilder();
	    while ((line = br.readLine()) != null) {
	        result.append(line);
	    }
	    br.close();

	    // JSON 파싱 없이도 "x":"127.0.0..." , "y":"37.5..." 부분만 추출
	    String responseStr = result.toString();
	    String x = "", y = "";
	    int xIdx = responseStr.indexOf("\"x\":\"");
	    if (xIdx >= 0) {
	        int start = xIdx + 5;
	        int end = responseStr.indexOf("\"", start);
	        x = responseStr.substring(start, end);
	    }
	    int yIdx = responseStr.indexOf("\"y\":\"");
	    if (yIdx >= 0) {
	        int start = yIdx + 5;
	        int end = responseStr.indexOf("\"", start);
	        y = responseStr.substring(start, end);
	    }

	    if (x.isEmpty() || y.isEmpty()) {
	        resp.setContentType("application/json; charset=UTF-8");
	        resp.getWriter().print("{\"success\":false,\"message\":\"좌표를 찾을 수 없습니다.\"}");
	        return;
	    }

	    resp.setContentType("application/json; charset=UTF-8");
	    resp.getWriter().print("{\"success\":true,\"lat\":\"" + y + "\",\"lng\":\"" + x + "\"}");
	}
}