package com.team3.cs.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.team3.cs.dao.CsFileDAO;

@Service
public class csFileServiceImpl implements csFileService {
	
	@Inject
	private CsFileDAO dao;

	@Override
	public Map<String, Object> selectFileInfo(int idx) throws Exception {
		return dao.selectFileInfo(idx);
	}
	
	
}
