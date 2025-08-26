package com.bapseguen.app.banner.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.BannerDTO;
import com.bapseguen.config.MyBatisConfig;

public class BannerDAO {
    private final SqlSession sqlSession;

    public BannerDAO() {
        // auto-commit true
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    /** 배너 등록 */
    public void insert(BannerDTO dto) {
        sqlSession.insert("banner.insert", dto);
    }

    /** 배너 수정 */
    public void update(BannerDTO dto) {
        sqlSession.update("banner.update", dto);
    }

    /** 배너 삭제 */
    public void delete(int bannerNumber) {
        sqlSession.delete("banner.delete", bannerNumber);
    }

    /** 배너 상세 조회 */
    public BannerDTO detail(int bannerNumber) {
        return sqlSession.selectOne("banner.detail", bannerNumber);
    }

    /** 배너 목록 조회 (관리자용 전체) */
    public List<BannerDTO> list() {
        return sqlSession.selectList("banner.list");
    }

    /** 메인 노출용 (활성+마감일 유효) 상위 N개 */
    public List<BannerDTO> activeList(int limit) {
        return sqlSession.selectList("banner.activeList", limit);
    }
}
