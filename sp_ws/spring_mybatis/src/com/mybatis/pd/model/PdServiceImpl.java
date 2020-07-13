package com.mybatis.pd.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PdServiceImpl implements PdService{
	@Autowired
	private PdDAO pdDao;
	
	@Transactional
	public int insertPd(PdDTO dto){
		return pdDao.insertPd(dto);
	}
			
	public List<PdDTO> selectAll(Map<String, String> map){
		return pdDao.selectAll(map);
	}
	
	public PdDTO selectByNo(int no){
		return pdDao.selectByNo(no);
	}
	
	public int updatePd(PdDTO dto) {
		return pdDao.updatePd(dto);	
	}
	
	public int deletePd(int no) {
		return pdDao.deletePd(no);
	}

	
}
