package com.mybatis.pd.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PdService {
	public int insertPd(PdDTO dto);
	
	
	public List<PdDTO> selectAll(Map<String, String> map);
	
	public PdDTO selectByNo(int no);
	
	public int updatePd(PdDTO dto);
	
	public int deletePd(int no);
	
	public List<CommentVO> selectCmt();
	
}
