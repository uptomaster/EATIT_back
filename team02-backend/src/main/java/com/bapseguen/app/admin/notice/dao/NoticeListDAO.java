package com.bapseguen.app.admin.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.view.NoticeListDTO;
import com.bapseguen.config.MyBatisConfig;

public class NoticeListDAO {
    private SqlSession sqlSession;

    public NoticeListDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    public int countList() {
        return sqlSession.selectOne("noticeList.countList");
    }

    public List<NoticeListDTO> selectList(int startRow, int rowCount) {
        Map<String, Object> map = new HashMap<>();
        map.put("startRow", startRow);
        map.put("rowCount", rowCount);
        return sqlSession.selectList("com.bapseguen.app.dto.view.noticeList.selectList", map);
    }
}
