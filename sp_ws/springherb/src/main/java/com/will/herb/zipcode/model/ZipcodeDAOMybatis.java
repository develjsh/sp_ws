package com.will.herb.zipcode.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipcodeDAOMybatis implements ZipcodeDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String namespace="config.mybatis.mapper.oracle.zipcode.";
	
	public List<ZipcodeVO> selectZipcode(String dong) {
		return sqlSession.selectList(namespace + "selectAll", dong);
	}
	
	
	
}
