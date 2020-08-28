package com.team3.cs.dao;

import java.util.List;
import java.util.Map;

import com.team3.cs.dto.askVO;
import com.team3.cs.dto.csFileVO;
import com.team3.cs.dto.csVO;

public interface csDAO {
	public List<askVO> selectAsk(Map<String, Object> map) throws Exception;
	public int countAsk() throws Exception;
	public List<askVO> searchAsk(Map<String, Object> map) throws Exception;
	public int countSearchAsk(String keyword) throws Exception;
	public List<askVO> findAsk(Map<String, Object> map) throws Exception;
	public int countFindAsk(int type) throws Exception;
	public void writeCS(csVO csVO) throws Exception;
	public int maxCount() throws Exception;
	public List<csVO> selectCS(Map<String, Object> map) throws Exception;
	public int countCS() throws Exception;
	public csVO viewCS(int cs_idx) throws Exception;
	public void updateCS(csVO csVO) throws Exception;
	public void deleteCS(int cs_idx) throws Exception;
	public void replyCS(csVO csVO) throws Exception;
	public List<csVO> csList(Map<String, Object> map);
	public int csCount(Map<String, Object> map);
	public int cs_typeCount(Map<String, Object> map);
	public List<csVO> cs_typeList(Map<String, Object> map);
	public List<Map<String, Object>> selectCsFile(int cs_idx);
	public void insertFile(csFileVO csFileVO) throws Exception;
}
