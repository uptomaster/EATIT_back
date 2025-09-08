package com.bapseguen.app.postReport.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.PostReportDTO;
import com.bapseguen.config.MyBatisConfig;

public class PostReportDAO {
	private SqlSession sqlSession;
	public PostReportDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public Integer findAuthor(int postNumber) {
	    return sqlSession.selectOne("postReport.findAuthor", postNumber);
	}
	
    public boolean reportOnce(int postNumber, int memberNumber, String reasonCode) {
        	// 트랜잭션 전용 세션(수동 커밋)
    	SqlSession tx = MyBatisConfig.getSqlSessionFactory().openSession(false);
        try  {
            // 1) 한 건씩 처리
            tx.selectOne("postReport.lockPostForUpdate", postNumber);

            // 2) 이미 신고했는지 확인
            Map<String, Object> map = new HashMap<>();
            map.put("postNumber", postNumber);
            map.put("memberNumber", memberNumber);
            Integer exists = tx.selectOne("postReport.alreadyReport", map);
            if (exists != null && exists > 0) {
                tx.rollback();
                return false; // 이미 신고
            }

            // 3) 신고 INSERT
            PostReportDTO postReportDTO = new PostReportDTO();
            postReportDTO.setPostNumber(postNumber);
            postReportDTO.setMemberNumber(memberNumber);
            postReportDTO.setPostReportReason(reasonCode); // 'ADV','BADWORDS','PORN','PERSONAL','ETC' 중 하나
            tx.insert("postReport.insertReport", postReportDTO); // dto.postReportNumber 채워짐

            // 4) 글 테이블의 누적 신고수 동기화(= 실제 COUNT)
            tx.update("postReport.syncPostCountFromReports", postNumber);

            // 5) 방금 삽입된 신고행의 카운트도 동일 값으로 세팅
            Map<String, Object> map2 = new HashMap<>();
            map2.put("postReportNumber", postReportDTO.getPostReportNumber());
            map2.put("postNumber", postNumber);
            tx.update("postReport.syncInsertedReportRowCount", map2);

            // 6) 커밋
            tx.commit();
            return true;
        } catch (RuntimeException | Error e) {
            try { tx.rollback(); } catch (Exception ignore) {}
            throw e; // 예외 숨기지 말고 재던지기(원인 추적)
        } finally {
            try { tx.close(); } catch (Exception ignore) {}
        }
    }
}
