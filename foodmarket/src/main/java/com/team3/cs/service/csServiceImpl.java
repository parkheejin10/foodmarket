package com.team3.cs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.team3.cs.dao.csDAO;
import com.team3.cs.dto.askVO;
import com.team3.cs.dto.csFileVO;
import com.team3.cs.dto.csVO;
import com.team3.cs.util.fileUtils;

@Service
public class csServiceImpl implements csService {
	
	@Resource(name="fileUtils")
	private fileUtils fileUtils;
	
	@Inject
	private csDAO dao;
	
	@Override
	public List<askVO> selectAsk(Map<String, Object> map) throws Exception {
		return dao.selectAsk(map);
	}
	
	@Override
	public int countAsk() throws Exception {
		return dao.countAsk();
	}

	@Override
	public List<askVO> searchAsk(Map<String, Object> map) throws Exception {
		return dao.searchAsk(map);
	}

	@Override
	public int countSearchAsk(String keyword) throws Exception {
		return dao.countSearchAsk(keyword);
	}
	
	@Override
	public List<askVO> findAsk(Map<String, Object> map) throws Exception {
		return dao.findAsk(map);
	}

	@Override
	public int countFindAsk(int type) throws Exception {
		return dao.countFindAsk(type);
	}
	

	@Override
	public void writeCS(HttpServletRequest request, csVO csVO, csFileVO csFileVO) throws Exception {
		dao.writeCS(csVO);	
		
		List<csFileVO> list = fileUtils.parseInsertFileInfo(csFileVO, request);
		for(int i=0, size=list.size(); i<size; i++){
			dao.insertFile(list.get(i));
		}
	}

	@Override
	public int maxCount() throws Exception {
		return dao.maxCount();
	}

	@Override
	public List<csVO> selectCS(Map<String, Object> map) throws Exception {
		return dao.selectCS(map);
	}
	
	@Override
	public int countCS() throws Exception {
		return dao.countCS();
	}

	@Override
	public Map<String, Object> viewCS(int cs_idx) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("read", dao.viewCS(cs_idx));
		List<Map<String, Object>> list = dao.selectCsFile(cs_idx);
		resultMap.put("fileList", list);
		
		return resultMap;
	}

	@Override
	public void updateCS(csVO csVO) throws Exception {
		dao.updateCS(csVO);		
	}

	@Override
	public void deleteCS(int cs_idx) throws Exception {
		dao.deleteCS(cs_idx);		
	}

	@Override
	public void replyCS(csVO csVO) throws Exception {
		dao.replyCS(csVO);
	}
	
	//============================ admin ??????  ============================
	
	// cs?????? ?????????
	@Override
	public List<csVO> csList(Map<String, Object> map) {
		return dao.csList(map);
	}
	// ?????? ?????? ???
	@Override
	public int csCount(Map<String, Object> map) {
		return dao.csCount(map);
	}
	// cs ????????? ?????????
	@Override
	public List<csVO> cs_typeList(Map<String, Object> map) {
		return dao.cs_typeList(map);
	}
	// cs ????????? ?????? ???
	@Override
	public int cs_typeCount(Map<String, Object> map) {
		return dao.cs_typeCount(map);
	}
	

}
