package com.bapseguen.app.community;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

/**
 * Servlet implementation class communityFrontController
 */
public class CommunityFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		
		String target = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("CommunityFrontController 현재 경로 : " + target);
		Result result = new Result();
		
		switch (target) {
		case "/community/communityMainOk.co":
			System.out.println("공지사항/이벤트 목록 페이지 처리 요청");
			result = new CommunityMainOkController().execute(request, response);
			break;
		case "/community/viewOwnPostOk.co":
			System.out.println("공지사항/이벤트 게시글 상세 페이지 처리 요청");
			result = new ViewOwnPostReadOkController().execute(request, response);
			break;	
		case "/community/freeBoardListOk.co":
			System.out.println("자유게시판 목록 페이지 처리 요청");
			result = new FreeBoardListOkController().execute(request, response);
			System.out.println(result);
			break;
		case "/community/freeBoardReadOk.co":
			System.out.println("자유게시판 상세 페이지 처리 요청");
			result = new FreeBoardReadOkController().execute(request, response);
			break;
		case "/community/writeFreeBoard.co":
			System.out.println("자유게시판 게시글 작성페이지 이동 요청");
			result = new WriteFreeBoardController().execute(request, response);
			break;
		case "/community/writeFreeBoardOK.co":
			System.out.println("자유게시판 게시글 작성완료 요청");
			result = new WriteFreeBoardOKController().execute(request, response);
			break;	
		case "/community/postDeleteOK.co":
			System.out.println("게시글 삭제 완료 요청");
			result = new PostDeleteOKController().execute(request, response);
			break;
		case "/community/postUpdate.co":
			System.out.println("게시글 수정 페이지 이동 요청");
			result = new PostUpdateController().execute(request, response);
			break;	
		case "/community/customerServiceListOk.co":
			System.out.println("고객센터 목록 페이지 처리 요청");
			result = new CustomerServiceListOkController().execute(request, response);
			break;
			
			
			
			
		case "/community/promoBoardListOk.co":
			System.out.println("홍보게시판 목록 페이지 처리 요청");
			result = new PromoBoardListOkController().execute(request, response);
			break;
//		case "/community/writePromoBoard.co":
//			System.out.println("홍보게시판 게시글 작성페이지 이동 요청");
//			result = new WritePromoBoardController().execute(request, response);
//			break;	
//		case "/community/writePromoOKBoard.co":
//			System.out.println("홍보게시판 게시글 작성완료 요청");
//			result = new WritePromoBoardOKController().execute(request, response);
//			break;
			
			
		case "/community/recipeListOk.co":
			System.out.println("레시피게시판 목록페이지 처리 요청");
			result = new RecipeListOkController().execute(request, response);
			break;
//		case "/community/writeRecipeBoard.co":
//			System.out.println("홍보게시판 게시글 작성페이지 이동 요청");
//			result = new WriteRecipeBoardController().execute(request, response);
//			break;
//		case "/community/writeRecipeOKBoard.co":
//			System.out.println("홍보게시판 게시글 작성완료 요청");
//			result = new WriteRecipeBoardOKController().execute(request, response);
//			break;
		}

		if (result != null) {
			if (result.isRedirect()) {
				response.sendRedirect(result.getPath());
			} else {
				request.getRequestDispatcher(result.getPath()).forward(request, response);
			}
		}
	}

}
