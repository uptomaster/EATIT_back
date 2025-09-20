package com.bapseguen.app.store.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.StoreFavoriteDTO;
import com.bapseguen.config.MyBatisConfig;

public class MyStoreFavoriteDAO {

    private SqlSession sqlSession;

    public MyStoreFavoriteDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /** 특정 회원이 해당 가게를 찜했는지 여부 확인 */
    public boolean isFavorited(int memberNumber, String businessNumber) {
        StoreFavoriteDTO dto = new StoreFavoriteDTO();
        dto.setMemberNumber(memberNumber);
        dto.setBusinessNumber(businessNumber);

        Integer result = sqlSession.selectOne("myStoreFavorite.exists", dto);
        return result != null && result > 0;
    }
    
    /** 내 찜 목록 조회 (페이징 적용) */
    public List<StoreFavoriteDTO> selectAll(int memberNumber, int startRow, int endRow) {
        Map<String, Object> params = new HashMap<>();
        params.put("memberNumber", memberNumber);
        params.put("startRow", startRow);
        params.put("endRow", endRow);
        return sqlSession.selectList("myStoreFavorite.MyFavSelectAll", params);
    }

    /** 찜 추가 */
    public void insert(StoreFavoriteDTO dto) {
        sqlSession.insert("myStoreFavorite.MyFavInsert", dto);
    }

    /** 찜 해제 */
    public void delete(StoreFavoriteDTO dto) {
        sqlSession.delete("myStoreFavorite.MyFavDelete", dto);
    }

    /** 특정 회원의 찜 총 개수 */
    public int countByMember(int memberNumber) {
        return sqlSession.selectOne("myStoreFavorite.countByMember", memberNumber);
    }

    /** 찜 여부 확인 */
    public boolean exists(StoreFavoriteDTO dto) {
        Integer result = sqlSession.selectOne("myStoreFavorite.exists", dto);
        return result != null && result > 0;
    }
}
