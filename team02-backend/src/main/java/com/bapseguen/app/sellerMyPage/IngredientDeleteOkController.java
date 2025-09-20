package com.bapseguen.app.sellerMyPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.sellerMyPage.dao.SellerMyPageDAO;

public class IngredientDeleteOkController implements Execute{

	@Override
	public Result execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Result result = new Result();
		 System.out.println("FoodDeleteOkController 진입 성공");
		 
		 int itemNumber = Integer.parseInt(request.getParameter("itemNumber"));

		 
		SellerMyPageDAO sellerDAO = new SellerMyPageDAO();   
		ItemImageDAO ItemImageDAO = new ItemImageDAO();   

       // 1. 파일 삭제 (있는 경우만)
       String uploadPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
       List<ItemImageDTO> images = ItemImageDAO.select(itemNumber);
       for (ItemImageDTO image : images) {
           File file = new File(uploadPath, image.getItemImageSystemName());
           if (file.exists()) {
               file.delete();
               System.out.println("파일 삭제: " + file.getName());
           }
       }

       // 2. 이미지 DB 삭제
       ItemImageDAO.delete(itemNumber);

       // 3. 게시글 삭제
       sellerDAO.deleteIngredient(itemNumber);

        result.setPath(request.getContextPath() + "/sellerMyPage/storeIngre.se");
        result.setRedirect(true);
	 
       return result;
	}
	
}
