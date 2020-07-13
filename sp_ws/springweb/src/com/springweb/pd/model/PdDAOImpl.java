package com.springweb.pd.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.springweb.db.ConnectionPoolMgr2;

public class PdDAOImpl implements PdDAO{
	private ConnectionPoolMgr2 pool;
	
	public PdDAOImpl(){
		System.out.println("PdDAOImpl 생성자 호출!");
	}
	
	//DI - setter에 의한 종속객체 주입
	public void setPool(ConnectionPoolMgr2 pool) {
		this.pool = pool;
		
		System.out.println("setter호출, PdDAOImpl-setPool()");
	}


	public int insertPd(PdDTO dto) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3.
			String sql="insert into pd(no, pdname, price)" + 
					" values (pd_seq.nextval, ?,?)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, dto.getPdName());
			ps.setInt(2, dto.getPrice());
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("상품 등록 결과 cnt="+cnt+", 매개변수 dto="+dto);
			
			return cnt;
		}finally {
			pool.dbClose(con, ps);
		}
	}
	
	public List<PdDTO> selectAll() throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<PdDTO> list = new ArrayList<PdDTO>();
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from pd order by no desc";
			ps=con.prepareStatement(sql);
			
			//4
			rs=ps.executeQuery();
			while(rs.next()) {
				int no=rs.getInt("no");
				String pdname=rs.getString("pdname");
				int price=rs.getInt("price");
				Timestamp regdate=rs.getTimestamp("regdate");
				
				PdDTO dto = new PdDTO(no, pdname, price, regdate);
				list.add(dto);
			}
			System.out.println("상품 목록 결과 list.size="+list.size());
			
			return list;
		}finally {
			pool.dbClose(con, ps, rs);
		}
	}
	
	public PdDTO selectByNo(int no) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		PdDTO dto=new PdDTO();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from pd where no=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			//4
			rs=ps.executeQuery();
			if(rs.next()) {
				String pdName=rs.getString("pdname");
				int price=rs.getInt("price");
				Timestamp regdate=rs.getTimestamp("regdate");
				
				dto.setNo(no);
				dto.setPdName(pdName);
				dto.setPrice(price);
				dto.setRegdate(regdate);				
			}
			System.out.println("상세보기 결과 dto="+dto+", 매개변수 no="+no);
			
			return dto;
		}finally {
			pool.dbClose(con, ps, rs);
		}
	}
	
	public int updatePd(PdDTO dto) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=pool.getConnection();
			
			String sql="update pd" + 
					" set pdname=?, price=?" + 
					" where no=?";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, dto.getPdName());
			ps.setInt(2, dto.getPrice());
			ps.setInt(3, dto.getNo());
			
			int cnt=ps.executeUpdate();
			System.out.println("상품 수정 결과, cnt="+cnt
					+", 매개변수 dto="+dto);
			
			return cnt;
		}finally {
			pool.dbClose(con, ps);
		}
	}
	
	public int deletePd(int no) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=pool.getConnection();
			
			String sql="delete pd" + 
					" where no=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			int cnt=ps.executeUpdate();
			System.out.println("상품 삭제 결과, cnt="+cnt
					+", 매개변수 no="+ no);
			
			return cnt;
		}finally {
			pool.dbClose(con, ps);
		}
	}
	
	
	
	
}


