package com.will.herb.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUploadUtil {
	private Logger logger
		=LoggerFactory.getLogger(FileUploadUtil.class);
	
	@Resource(name="fileUploadProperties")
	Properties fileUploadProps;
	
	public List<Map<String, Object>> fileUpload(HttpServletRequest request) {
		//파일 업로드 처리 메서드		
		MultipartHttpServletRequest multiReq 
		= (MultipartHttpServletRequest) request;
	
		List<Map<String, Object>> resultList
			=new ArrayList<Map<String,Object>>();
		
		Map<String, MultipartFile> fileMap=multiReq.getFileMap();
		Iterator<String> iter=fileMap.keySet().iterator();
		while(iter.hasNext()) {
			String key=iter.next();
			
			MultipartFile tempFile=fileMap.get(key);
			//=> 업로드된 파일을 임시파일 형태로 제공
			
			//업로드 한 경우에 파일크기, 이름 구하기
			if(!tempFile.isEmpty()) {
				String originalFName = tempFile.getOriginalFilename();
				long fileSize=tempFile.getSize();
				
				//변경된 파일 명 구하기
				String fileName=getUniqueFileName(originalFName);
				
				//업로드 처리
				String upPath = getUploadPath(request);
				File file = new File(upPath, fileName);
				try {
					tempFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				logger.info("업로드 처리되었습니다.");
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("fileSize", fileSize);
				map.put("fileName", fileName);
				map.put("originalFileName", originalFName);
				
				resultList.add(map);
			}
		}//while
		
		return resultList;
	}
	
	public String getUploadPath(HttpServletRequest request) {
		String type=fileUploadProps.getProperty("file.upload.type");
		//=> test, deploy
		
		String uploadPath="";
		if(type.equals("test")) { //테스트시
			uploadPath=fileUploadProps.getProperty("file.upload.path.test");
		}else { //deploy-배포시
			uploadPath=fileUploadProps.getProperty("file.upload.path");
			//실제 물리적인 경로 구하기
			HttpSession session=request.getSession();
			uploadPath
				=session.getServletContext().getRealPath(uploadPath);
		}
		
		logger.info("type={}, uploadPath={}", type, uploadPath);
		
		return uploadPath;
	}
	
	public String getUniqueFileName(String originFName) {
		//변경된 파일명
		//ab.txt => ab_현재시간.txt
		int idx=originFName.lastIndexOf(".");
		String fname = originFName.substring(0, idx); //ab
		String ext = originFName.substring(idx); //.txt
		
		String fileName = fname+"_"+getTimeStamp()+ext;
		logger.info("변경된 파일명 = {}", fileName);
		
		return fileName;
	}
	
	public String getTimeStamp() {
		//현재 시간을 밀리초까지 지정해서 리턴
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date d = new Date();
		String str=sdf.format(d);
		
		return str;
	}
	
	
}






