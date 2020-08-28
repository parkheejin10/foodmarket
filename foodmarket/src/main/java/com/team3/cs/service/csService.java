package com.team3.cs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.team3.cs.dto.askVO;
import com.team3.cs.dto.csFileVO;
import com.team3.cs.dto.csVO;

public interface csService {
	public List<askVO> selectAsk(Map<String, Object> map) throws Exception;
	public int countAsk() throws Exception;
	public List<askVO> searchAsk(Map<String, Object> map) throws Exception;
	public int countSearchAsk(String keyword) throws Exception;
	public List<askVO> findAsk(Map<String, Object> map) throws Exception;
	public int countFindAsk(int type) throws Exception;
	public void writeCS(HttpServletRequest request, csVO csVO, csFileVO csFileVO) throws Exception;
	public int maxCount() throws Exception;
	public List<csVO> selectCS(Map<String, Object> map) throws Exception;
	public int countCS() throws Exception;
	public Map<String, Object> viewCS(int cs_idx) throws Exception;
	public void updateCS(csVO csVO) throws Exception;
	public void deleteCS(int cs_idx) throws Exception;
	public void replyCS(csVO csVO) throws Exception;
	public List<csVO> csList(Map<String, Object> map);
	public int csCount(Map<String, Object> map);
	public int cs_typeCount(Map<String, Object> map);
	public List<csVO> cs_typeList(Map<String, Object> map);
}