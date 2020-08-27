package com.team3.cs.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team3.cs.dto.CsFileVO;

@Component("fileUtils")
public class FileUtils {
	private static final String filePath = "D:\\team\\";

	public List<CsFileVO> parseInsertFileInfo(CsFileVO csFileVO, HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		List<CsFileVO> list = new ArrayList<CsFileVO>();
		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs();
		}
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if (multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				csFileVO.setOrg_filename(originalFileName);
				csFileVO.setStr_filename(storedFileName);
				csFileVO.setFile_size(multipartFile.getSize());
				list.add(csFileVO);
			}
		}
		return list;
	}

}
