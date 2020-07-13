package com.will.herb.member.model;

public interface MemberDAO {
	public int insertMember(MemberVO vo);
	public int selectDup(String userid);
	String selectPwd(String userid);
	MemberVO selectMember(String userid);
	public int updateMember(MemberVO vo);
	public int deleteMember(String userid);
}
