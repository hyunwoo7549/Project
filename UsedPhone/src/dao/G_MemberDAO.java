package dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.B_MemberVO;
import vo.G_MemberVO;

public class G_MemberDAO {
	private static G_MemberDAO single = null;
	SqlSessionFactory factory = null;
	
	public static G_MemberDAO getInstance() {
		if ( single == null ) {
			single = new G_MemberDAO();
		}
		return single;
	}
	
	public G_MemberDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	/**------------------------ 아이디 중복체크 (일반회원 / 완료) ------------------------*/
	public boolean checkId ( String paramId ) {
		boolean result = true;
		String id;
		
		SqlSession sqlSession = factory.openSession();
		id = sqlSession.selectOne("g_member.checkId", paramId.toLowerCase());
		System.out.println("g결과 : " + id);
		if (id != null) {
			result = false;
		}
		
		sqlSession.close();
		
		return result;
	}
	
	/**------------------------ 회원가입정보 저장 (일반회원 / 완료) ------------------------*/
	public void insertUserData ( G_MemberVO vo ) {
		int res = 0;
		SqlSession sqlSession = factory.openSession();
		res = sqlSession.insert( "g_member.insertUserData", vo );
		// System.out.println("인서트 성공여부 : " + res);
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	/**------------------------ 회원정보 있는지 조회 (일반회원 / 완료) ------------------------*/
	public String searchUserData ( Map<String, String> map ) {
		String resultId = "";
		
		SqlSession sqlSession = factory.openSession();
		resultId = sqlSession.selectOne( "g_member.searchUserData", map );
		sqlSession.close();
		
		return resultId;
	}
	
	
	/**------------------------ 회원정보 있으면 정보 얻어오기 (일반회원 / 완료 / 로그인, 회원정보있는지 확인) ------------------------*/
	public G_MemberVO getUserData ( String resultId ) {
		G_MemberVO vo = new G_MemberVO();
		
		SqlSession sqlSession = factory.openSession();
		vo = (G_MemberVO)sqlSession.selectOne( "g_member.getUserData", resultId );
		sqlSession.close();
		
		return vo;
	}

	
	/**------------------------ 회원정보수정 (일반회원 / 완료) ------------------------*/
	public void updateUserData ( G_MemberVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "g_member.updateUserData", vo );
		sqlSession.commit();
		sqlSession.close();
	}
	

	/**------------------------ 비밀번호수정 (일반회원 / 완료) ------------------------*/
	public void updateUserPwd( G_MemberVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "g_member.updateUserPwd", vo );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**------------------------ 비밀번호찾기 (일반회원 / 완료) ------------------------*/
	public String searchUserPwd ( Map<String, String> map ) {
		String resultPwd = "";
		
		SqlSession sqlSession = factory.openSession();
		resultPwd = sqlSession.selectOne( "g_member.searchUserPwd", map );
		sqlSession.close();
		
		return resultPwd;
	}
	
	/**------------------------ 회원탈퇴 (일반회원 / 완료) ------------------------*/
	public void deleteUserData ( String id ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.delete( "g_member.deleteUserData", id );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**------------------------ 탈퇴한 회원 연령대와 사유저장 (일반회원 / 완료) ------------------------*/
	public void insertLeavingReason ( Map<String, String> map ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert( "g_member.insertLeavingReason", map );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/**------------------------ 물건팔면 지갑에 돈 들어옴 (일반회원 / 완료) ------------------------*/
	public void getMoney ( Map<String, Object> map2 ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "g_member.getMoney", map2 );
		sqlSession.commit();
		sqlSession.close();
	}
	
}
