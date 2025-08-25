package com.bapseguen.app.item.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.ItemImageDTO;
import com.bapseguen.app.dto.view.ItemSnapshotDTO;
import com.bapseguen.app.dto.view.ItemWithImgDTO;
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
    public List<ItemWithImgDTO> list(String businessNumber, String itemType, Integer offset, Integer limit) {
        Map<String, Object> p = new HashMap<>();
        p.put("businessNumber", businessNumber);
        p.put("itemType", itemType);
        p.put("offset", offset);
        p.put("limit", limit);
        return sqlSession.selectList("item.list", p);
    }

    /** 특정 가게/타입별 총 개수 */
    public int count(String businessNumber, String itemType) {
        Map<String, Object> p = new HashMap<>();
        p.put("businessNumber", businessNumber);
        p.put("itemType", itemType);
        return sqlSession.selectOne("item.count", p);
    }

    /** 메인 storeList.jsp 용 전체 아이템 목록 (음식/재료 구분) */
    public List<ItemWithImgDTO> selectAllItems(String itemType, Integer offset, Integer limit) {
        Map<String, Object> p = new HashMap<>();
        p.put("itemType", itemType);
        p.put("offset", offset);
        p.put("limit", limit);
        return sqlSession.selectList("item.selectAllItems", p);
    }

    /** 메인 storeList.jsp 전체 개수 */
    public int countAllItems(String itemType) {
        return sqlSession.selectOne("item.countAllItems", itemType);
    }

    /** 상품 상세 조회 */
    public ItemWithImgDTO selectItemDetail(int itemNumber) {
        return sqlSession.selectOne("item.selectItemDetail", itemNumber);
    }

    /** 상품 이미지 목록 */
    public List<ItemImageDTO> selectItemImages(int itemNumber) {
        return sqlSession.selectList("item.selectItemImages", itemNumber);
    }

    /** 목록 검색 */
    public List<ItemWithImgDTO> searchItems(Map<String,Object> params) {
        return sqlSession.selectList("item.searchItems", params);
    }

    /** 검색 개수 세기 */
    public int countSearchItems(Map<String,Object> params) {
        return sqlSession.selectOne("item.countSearchItems", params);
    }


}
