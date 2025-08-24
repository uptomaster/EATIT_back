package com.bapseguen.app.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemDTO;
import com.bapseguen.app.dto.view.ItemSnapshotDTO;
import com.bapseguen.config.MyBatisConfig;

public class ItemDAO {
    private SqlSession sqlSession;

    public ItemDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /** 단건 스냅샷 조회 (가격/재고/판매상태/가게번호) */
    public ItemSnapshotDTO selectSnapshot(int itemNumber) {
        return sqlSession.selectOne("item.snapshot", itemNumber);
    }
    
    /** 가게/타입별 아이템 목록 */
    public List<ItemDTO> list(String businessNumber, String itemType, Integer offset, Integer limit) {
        Map<String, Object> p = new HashMap<>();
        p.put("businessNumber", businessNumber);
        p.put("itemType", itemType);
        p.put("offset", offset);
        p.put("limit", limit);
        return sqlSession.selectList("item.list", p);
    }

    /** 총 개수 */
    public int count(String businessNumber, String itemType) {
        Map<String, Object> p = new HashMap<>();
        p.put("businessNumber", businessNumber);
        p.put("itemType", itemType);
        return sqlSession.selectOne("item.count", p);
    }
    
}
