package com.team3.cs.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.team3.cs.dto.askVO;
import com.team3.cs.dto.csFileVO;
import com.team3.cs.dto.csVO;

@Repository
public class csDAOImpl implements csDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String Namespace = "com.team3.mapper.csMapper";
	
	@Override
	public List<askVO> selectAsk(Map<String, Object> map) throws Exception {		
		return sqlSession.selectList(Namespace+".selectAsk", map);
	}

	@Override
	public int countAsk() throws Exception {
		return (Integer)sqlSession.selectOne(Namespace+".countAsk");
	}

	@Override
	public List<askVO> searchAsk(Map<String, Object> map) {
		return sqlSession.selectList(Namespace+".searchAsk", map);
	}

	@Override
	public int countSearchAsk(String keyword) throws Exception {
		return (Integer)sqlSession.selectOne(Namespace+".countSearchAsk", keyword);
	}
	
	@Override
	public List<askVO> findAsk(Map<String, Object> map) throws Exception {
		return sqlSession.selectList(Namespace+".findAsk", map);
	}

	@Override
	public int countFindAsk(int type) throws Exception {
		return (Integer)sqlSession.selectOne(Namespace+".countFindAsk", type);
	}

	@Override
	public void writeCS(csVO csVO) throws Exception {
		sqlSession.insert(Namespace+".insertCS", csVO);		
	}
	
	@Override
	public int maxCount() throws Exception {
		return (Integer)sqlSession.selectOne(Namespace+".maxCount");
	}

	@Override
	public List<csVO> selectCS(Map<String, Object> map) throws Exception {		
		return sqlSession.selectList(Namespace+".selectCS", map);
	}
	
	@Override
	public int countCS() throws Exception {
		return (Integer)sqlSession.selectOne(Namespace+".countCS");
	}

	@Override
	public csVO viewCS(int cs_idx) throws Exception {
		
		return sqlSession.selectOne(Namespace+".readCS", cs_idx);
	}

	@Override
	public void updateCS(csVO csVO) throws Exception {
		sqlSession.update(Namespace+".updateCS", csVO);
	}

	@Override
	public void deleteCS(int cs_idx) throws Exception {
		sqlSession.delete(Namespace+".deleteCS", cs_idx);
		
	}

	@Override
	public void replyCS(csVO csVO) throws Exception {
		sqlSession.insert(Namespace+".replyCS", csVO);
	}	
	
	@Override
	public List<Map<String, Object>> selectCsFile(int cs_idx) {
		return sqlSession.selectList(Namespace+".selectFileList", cs_idx);
	}

	@Override
	public void insertFile(csFileVO csFileVO) throws Exception {
		sqlSession.insert(Namespace+".insertCsFile", csFileVO);		
	}

	//============================ admin ??????  ============================
	// ?????? ?????? ??????
	@Override
	public List<csVO> csList(Map<String, Object> map) {
		return sqlSession.selectList(Namespace+".csList", map);
	}
	// ?????? ?????? ???
	@Override
	public int csCount(Map<String, Object> map) {
		return sqlSession.selectOne(Namespace+".csCount",map);
	}
	// cs ????????? ?????????
	@Override
	public List<csVO> cs_typeList(Map<String, Object> map) {
		return sqlSession.selectList(Namespace+".cs_typeList",map);
	}
	// cs ????????? ?????? ???
	@Override
	public int cs_typeCount(Map<String, Object> map) {
		return sqlSession.selectOne(Namespace+".cs_typeCount",map);
	}

}
