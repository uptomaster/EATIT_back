 package com.bapseguen.app.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		case "/community/writeFreeBoardOk.co":
			System.out.println("자유게시판 게시글 작성완료 요청");
			result = new WriteFreeBoardOkController().execute(request, response);
			break;
			
			
		case "/community/promoBoardListOk.co":
			System.out.println("홍보게시판 목록 페이지 처리 요청");
			result = new PromoBoardListOkController().execute(request, response);
			break;
		case "/community/promoBoardReadOk.co":
			System.out.println("홍보게시판 상세 페이지 처리 요청");
			result = new PromoBoardReadOkController().execute(request, response);
			break;
			
			
		case "/community/recipeListOk.co":
			System.out.println("레시피게시판 목록페이지 처리 요청");
			result = new RecipeListOkController().execute(request, response);
			break;
		case "/community/recipeBoardReadOk.co":
			System.out.println("레시피게시판 상세 페이지 처리 요청");
			result = new RecipeBoardReadOkController().execute(request, response);
			break;
			
			
		case "/community/postUpdate.co":
			System.out.println("게시글 수정 페이지 이동 요청");
			result = new PostUpdateController().execute(request, response);
			break;
		case "/community/postUpdateOk.co":
			System.out.println("게시글 수정 완료 요청");
			result = new PostUpdateOkController().execute(request, response);
			break;	
		case "/community/postDeleteOk.co":
			System.out.println("게시글 삭제 완료 요청");
			result = new PostDeleteOkController().execute(request, response);
			break;	
		case "/community/postlike.co":
			System.out.println("게시글 추천 요청");
			result = new PostLikeController().execute(request, response);
			break;	
		case "/community/postViewCount.co":
			System.out.println("게시글 추천 요청");
			result = new PostViewCountController().execute(request, response);
			break;			
			
			
			
		case "/community/faqListOk.co":
			System.out.println("faq 목록 페이지 처리 요청");
			result = new FaqController().execute(request, response);
			break;

		case "/community/faqReadOk.co":
			System.out.println("faq 목록 페이지 처리 요청");
			result = new FaqReadOkController().execute(request, response);
			break;

		case "/community/inquiryListOk.co":
			System.out.println("inquiry 목록 페이지 처리 요청");
			result = new InquiryController().execute(request, response);
			break;

		case "/community/inquiryReadOk.co":
			System.out.println("inquiry 상세 페이지 처리 요청");
			result = new InquiryReadOkController().execute(request, response);
			break;
			
			
			
			



			
		}

		/*
		 * RequestDispatcher dispatcher =
		 * request.getRequestDispatcher(result.getPath());
		 * 
		 * if (dispatcher == null) { System.out.println("Dispatcher is null! Path = " +
		 * result.getPath()); } else { dispatcher.forward(request, response); }
		 */
		
		if (result != null && result.getPath() != null) {
		    RequestDispatcher dispatcher = request.getRequestDispatcher(result.getPath());
		    dispatcher.forward(request, response);
		} else {
		    System.out.println("Forward/Redirect 필요 없음. result: " + result);
		}
	}

}
