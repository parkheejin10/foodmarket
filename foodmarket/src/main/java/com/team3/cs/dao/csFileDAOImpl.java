package com.team3.cs.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class csFileDAOImpl implements csFileDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.team3.mapper.csMapper";
	
	@Override
	public Map<String, Object> selectFileInfo(int idx) {
		return sqlSession.selectOne(Namespace+".selectFileInfo", idx);
	}
}
