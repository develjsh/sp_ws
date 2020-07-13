package com.springweb.pd.model;

import java.sql.SQLException;
import java.util.List;

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


	public int insertPd(PdDTO dto) throws SQLException {
		return pdDao.insertPd(dto);
	}
		
	public List<PdDTO> selectAll() throws SQLException{
		return pdDao.selectAll();
	}
	
	public PdDTO selectByNo(int no) throws SQLException {
		return pdDao.selectByNo(no);
	}
	
	public int updatePd(PdDTO dto) throws SQLException {
		return pdDao.updatePd(dto);	
	}
	
	public int deletePd(int no) throws SQLException {
		return pdDao.deletePd(no);
	}
	
}
