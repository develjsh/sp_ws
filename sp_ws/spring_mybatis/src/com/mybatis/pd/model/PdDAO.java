package com.mybatis.pd.model;

import java.util.List;
import java.util.Map;

public interface PdDAO {
	int insertPd(PdDTO dto);
		
	public List<PdDTO> selectAll(Map<String, String> map);
	
	public PdDTO selectByNo(int no);
	
	public int updatePd(PdDTO dto);
	
	public int deletePd(int no);
}
