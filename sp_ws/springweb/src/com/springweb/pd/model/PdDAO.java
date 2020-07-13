package com.springweb.pd.model;

import java.sql.SQLException;
import java.util.List;

public interface PdDAO {
	int insertPd(PdDTO dto) throws SQLException;
	
	public List<PdDTO> selectAll() throws SQLException;
	
	public PdDTO selectByNo(int no) throws SQLException;
	
	public int updatePd(PdDTO dto) throws SQLException;
	
	public int deletePd(int no) throws SQLException;
	
}
