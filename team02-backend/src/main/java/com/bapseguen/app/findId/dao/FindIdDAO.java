package com.bapseguen.app.findId.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bapseguen.config.MyBatisConfig;

public class FindIdDAO {
	public SqlSession sqlSession;
	public FindIdDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	public String findId(String name, String phoneInput) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("name", name);
	    map.put("generalPhoneNumber", phoneInput);
	    map.put("sellerPhoneNumber",  phoneInput);
	    return sqlSession.selectOne("member.findId", map);
	}
	
}
