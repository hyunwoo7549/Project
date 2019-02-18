package dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.B_MemberVO;
import vo.G_MemberVO;

public class B_MemberDAO {
	private static B_MemberDAO single = null;
	SqlSessionFactory factory = null;
	
	public static B_MemberDAO getInstance() {
		if ( single == null ) {
			single = new B_MemberDAO();
		}
		return single;
	}
	
	public B_MemberDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	/**------------------------ 아이디 중복체크 (사업자회원 / 완료)------------------------*/
	public boolean checkId ( String paramId ) {
		boolean result = true;
		String id;
		
		SqlSession sqlSession = factory.openSession();
		id = sqlSession.selectOne("b_member.checkId", paramId.toLowerCase());
		System.out.println("b결과 : " + id);
		if (id != null) {
			result = false;
		}
		
		sqlSession.close();
		
		return result;
	}
	
	/**------------------------ 회원가입정보 저장 (사업자회원 / 완료)------------------------*/
	public void insertUserData ( B_MemberVO vo ) {
		int res = 0;
		SqlSession sqlSession = factory.openSession();
		res = sqlSession.insert( "b_member.insertUserData", vo );
		System.out.println("인서트 성공여부 : " + res);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	/**------------------------ 회원정보 있는지 조회 (사업자회원 / 완료)------------------------*/
	public String searchUserData ( Map<String, String> map ) {
		String resultId = "";
		
		SqlSession sqlSession = factory.openSession();
		resultId = sqlSession.selectOne( "b_member.searchUserData", map );
		sqlSession.close();
		
		return resultId;
	}
	
	
	/**------------------------ 회원정보 있으면 정보 얻어오기 (사업자회원 / 완료 / 로그인, 회원정보있는지 확인) ------------------------*/
	public B_MemberVO getUserData ( String resultId ) {
		B_MemberVO vo = new B_MemberVO();
		
		SqlSession sqlSession = factory.openSession();
		vo = (B_MemberVO)sqlSession.selectOne( "b_member.getUserData", resultId );
		sqlSession.close();
		
		return vo;
	}

	
	/**------------------------ 회원정보수정 (사업자회원 / 완료) ------------------------*/
	public void updateUserData ( B_MemberVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "b_member.updateUserData", vo );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**------------------------ 비밀번호수정 (사업자회원 / 완료) ------------------------*/
	public void updateUserPwd(B_MemberVO vo) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "b_member.updateUserPwd", vo );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**------------------------ 비밀번호찾기 (사업자회원 / 완료) ------------------------*/
	public String searchUserPwd ( Map<String, String> map ) {
		String resultPwd = "";
		
		SqlSession sqlSession = factory.openSession();
		resultPwd = sqlSession.selectOne( "b_member.searchUserPwd", map );
		sqlSession.close();
		
		return resultPwd;
	}

	/**------------------------ 회원탈퇴 (사업자회원 / 완료) ------------------------*/
	public void deleteUserData ( String id ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.delete( "b_member.deleteUserData", id );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**------------------------ 탈퇴한 회원 연령대와 사유저장 (사업자회원 / 완료) ------------------------*/
	public void insertLeavingReason ( Map<String, String> map ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert( "b_member.insertLeavingReason", map );
		sqlSession.commit();
		sqlSession.close();
	}

	/**------------------------ 물건팔면 지갑서 돈 빼감 (일반회원 / 완료) ------------------------*/
	public void dropMoney ( Map<String, Object> map2 ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "b_member.dropMoney", map2 );
		sqlSession.commit();
		sqlSession.close();
	}


}
