package com.mybatis.pd.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PdServiceImpl implements PdService{
	private PdDAO pdDao;
	
	public PdServiceImpl() {
		System.out.println("PdServiceImpl 생성자 호출!");
	}
	
	//DI - setter에 의한 종속객체 주입
	public void setPdDao(PdDAO pdDao) {
		this.pdDao = pdDao;
		
		System.out.println("setter 호출, PdServiceImpl-setPdDao()");
	}


	public int insertPd(PdDTO dto){
		return pdDao.insertPd(dto);
	}
		
	
	public List<PdDTO> selectAll(Map<String, String> map){
		return pdDao.selectAll(map);
	}
	
	public PdDTO selectByNo(int no){
		return pdDao.selectByNo(no);
	}
	
	public int updatePd(PdDTO dto){
		return pdDao.updatePd(dto);	
	}
	
	public int deletePd(int no){
		return pdDao.deletePd(no);
	}

	public List<CommentVO> selectCmt() {
		return pdDao.selectCmt();
	}
	
}
