package com.mybatis.pd.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.mybatis.config.AbstractRepository;

public class PdDAOImpl extends AbstractRepository implements PdDAO{
	private String namespace="com.mybatis.mapper.pd.";
	
	public int insertPd(PdDTO dto){
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		System.out.println("insert 전 dto="+dto);
		try {
			int cnt=sqlSession.insert(namespace+"insertPd", dto);
			System.out.println("insert 후 dto="+dto); //no 값이 있다
			if(cnt>0) {
				sqlSession.commit();
			}else {
				sqlSession.rollback();
			}
			
			return cnt;
		}finally {
			sqlSession.close();
		}
	}
	
	public List<PdDTO> selectAll(Map<String, String> map){
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		
		try {
			List<PdDTO> list
				=sqlSession.selectList(namespace+"selectAll", map);
			
			return list;
		}finally {
			sqlSession.close();
		}
	}
	
	
	public PdDTO selectByNo(int no){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			PdDTO pdDto
			= sqlSession.selectOne(namespace+"selectByNo", no);
			return pdDto;
		}finally {
			sqlSession.close();
		}
	}
	
	public int updatePd(PdDTO dto){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			int cnt = sqlSession.update(namespace+"updatePd", dto);
			return cnt;
		}finally {
			sqlSession.close();
		}
		
	}
	
	public int deletePd(int no){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			int cnt = sqlSession.delete(namespace+"deletePd", no);
			return cnt;		
		}finally {
			sqlSession.close();
		}
		
	}
	public List<CommentVO> selectCmt(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			List<CommentVO> list
				=sqlSession.selectList(namespace+"selectComment2");
			return list;
		}finally {
			sqlSession.close();
		}
	}
	
}


