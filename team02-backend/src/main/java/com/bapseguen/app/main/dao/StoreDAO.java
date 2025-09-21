package com.bapseguen.app.main.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.view.ItemWithImgDTO;
import com.bapseguen.app.dto.view.MainStoreListDTO;
import com.bapseguen.config.MyBatisConfig;

public class StoreDAO {

    public List<MainStoreListDTO> getAllStores() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            return session.selectList("main.getAllStores");
        }
    }
    
    public List<ItemWithImgDTO> getAllIngredientsWithStore() {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            return session.selectList("main.getAllIngredientsWithStore");
        }
    }
}
