package com.bapseguen.app.sellerMyPage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bapseguen.app.Execute;
import com.bapseguen.app.Result;
import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.img.dao.ItemImageDAO;
import com.bapseguen.app.item.dao.ItemDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FoodEditOkController implements Execute {

    @Override
    public Result execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDAO itemDAO = new ItemDAO();
        ItemWithImgDTO itemWithImgDTO = new ItemWithImgDTO();
        Result result = new Result();
        ItemImageDAO fileDAO = new ItemImageDAO();
        
        final String UPLOAD_PATH = request.getSession().getServletContext().getRealPath("/") + "upload/";
        final int FILE_SIZE = 1024 * 1024 * 5; // 5MB
        
        MultipartRequest multipartRequest = new MultipartRequest(request, UPLOAD_PATH, FILE_SIZE, "utf-8", new DefaultFileRenamePolicy());
        
        // 기존 파일 삭제
//        int itemNumber = Integer.parseInt(multipartRequest.getParameter("itemNumber"));
//        List<ItemImageDTO> existingFiles = fileDAO.select(itemNumber);
//        for( ItemImageDTO : existingFiles) {
//        	ItemImageDTO oldFile = new ItemImageDTO(UPLOAD_PATH, file.getFileSystemName());
//            if(oldFile.exists()) {
//                oldFile.delete();
//            }
//        }
        fileDAO.delete(itemNumber);
        
        // 상품 수정
        itemWithImgDTO.setItemNumber(itemNumber);
        itemWithImgDTO.setItemType(multipartRequest.getParameter("itemType"));
        itemWithImgDTO.setItemName(multipartRequest.getParameter("itemName"));
        itemWithImgDTO.setItemPrice(multipartRequest.getParameter("itemPrice"));
        itemWithImgDTO.setItemContent(multipartRequest.getParameter("itemContent"));
        itemWithImgDTO.setItemQuantity(Integer.parseInt(multipartRequest.getParameter("itemQuantity")));
        itemWithImgDTO.setItemExpireDate(multipartRequest.getParameter("itemExpireDate"));
        itemWithImgDTO.setBusinessNumber(multipartRequest.getParameter("businessNumber"));
        itemWithImgDTO.setItemUpdatedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        
        itemDAO.update(itemWithImgDTO);
        
        // 새로운 파일 업로드
        Enumeration<String> fileNames = multipartRequest.getFileNames();
        while(fileNames.hasMoreElements()) {
            String name = fileNames.nextElement();
            String fileSystemName = multipartRequest.getFilesystemName(name);
            String fileOriginalName = multipartRequest.getOriginalFileName(name);
            
            if(fileSystemName == null) continue;
            
            FileDTO fileDTO = new FileDTO();
            fileDTO.setFileSystemName(fileSystemName);
            fileDTO.setFileOriginalName(fileOriginalName);
            fileDTO.setItemNumber(itemNumber);
            
            fileDAO.insert(fileDTO);
        }
        
        result.setPath("/item/itemListOk.it");
        result.setRedirect(false);
        return result;
    }
}