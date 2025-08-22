package com.bapseguen.app.join.dao;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.app.dto.GeneralMemberDTO;
import com.bapseguen.app.dto.MemberDTO;
import com.bapseguen.app.dto.SellerMemberDTO;
import com.bapseguen.app.dto.StoreDTO;
import com.bapseguen.config.MyBatisConfig;

public class JoinDAO {
    public SqlSession sqlSession;

    public JoinDAO() {
        sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
    }

    public boolean checkId(String memberId) {
        return (Integer) sqlSession.selectOne("member.checkId", memberId) < 1;
    }

    //일반 회원가입
    public int joinGeneral(MemberDTO member, GeneralMemberDTO general) {
        sqlSession.insert("member.joinMember", member); 
        int generalMemberNumber = member.getMemberNumber();
        general.setMemberNumber(generalMemberNumber);
        sqlSession.insert("member.joinGeneral", general);
        return generalMemberNumber;
    }

    // 판매자 회원가입 
    public int joinSeller(MemberDTO member, SellerMemberDTO seller, StoreDTO store) {
        sqlSession.insert("member.joinMember", member);
        int sellerMemberNumber = member.getMemberNumber();

        seller.setMemberNumber(sellerMemberNumber);
        sqlSession.insert("member.joinSeller", seller);

        store.setMemberNumber(sellerMemberNumber);
        sqlSession.insert("member.joinStore", store);
        return sellerMemberNumber;
    }
}
