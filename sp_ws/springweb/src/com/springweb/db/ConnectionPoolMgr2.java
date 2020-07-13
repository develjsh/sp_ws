package com.springweb.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolMgr2 {
	//톰캣이 구현한 DBCP이용
	private DataSource ds;
	
	public ConnectionPoolMgr2() {
		try {
			Context ctx = new InitialContext();
			ds=(DataSource) ctx.lookup("java:/comp/env/jdbc/oracledb");
			System.out.println("DataSource 생성됨!");
		} catch (NamingException e) {
			System.out.println("DataSource 생성 실패!");
			e.printStackTrace();
		}		
	}
	
	public ConnectionPoolMgr2(String name) {
		try {
			Context ctx = new InitialContext();
			ds=(DataSource) ctx.lookup("java:/comp/env/"+name);
			System.out.println("DataSource 생성됨!");
		} catch (NamingException e) {
			System.out.println("DataSource 생성 실패!");
			e.printStackTrace();
		}		
	}
	
	public Connection getConnection() throws SQLException {
		Connection con=ds.getConnection();
		System.out.println("db연결 여부 con="+con);
		
		return con;
	}
	
	public void dbClose(Connection con, PreparedStatement ps,
			ResultSet rs) throws SQLException {
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close();		
	}
	
	public void dbClose(Connection con, PreparedStatement ps) 
			throws SQLException {
		if(ps!=null) ps.close();
		if(con!=null) con.close();		
	}
}
