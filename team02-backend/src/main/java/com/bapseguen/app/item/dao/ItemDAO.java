package com.bapseguen.app.item.dao;

import org.apache.ibatis.session.SqlSession;
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
}
