package com.bapseguen.app.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Result;

/**
 * Servlet implementation class CommentFrontController
 */
public class CommentFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentFrontController() {
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
		System.out.println("현재 경로 : " + target);
		Result result = new Result();
		switch (target) {
		  case "/comment/writeOk.cm":
		    result = new CommentWriteOkController().execute(request, response);
		    break;
		  case "/comment/listOk.cm":
		    result = new CommentListOkController().execute(request, response);
		    break;
		  case "/comment/deleteOk.cm":
		    result = new CommentDeleteOkController().execute(request, response);
		    break;
		}
		
		
		
		
        if (result != null && result.getPath() != null) {
            if (result.isRedirect()) {
                response.sendRedirect(result.getPath());
            } else {
                request.getRequestDispatcher(result.getPath()).forward(request, response);
            }
        }
		
	}

}
