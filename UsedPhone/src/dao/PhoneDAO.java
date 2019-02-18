package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.BuyerVO;
import vo.PhoneVO;

public class PhoneDAO {
	private static PhoneDAO single = null;
	SqlSessionFactory factory = null;
	
	public static PhoneDAO getInstance() {
		if(single == null)
			single = new PhoneDAO();
		return single;
	}
	
	public PhoneDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	/** ----------------------- 클릭한 제조회사 리스트 조회 (완성) ----------------------- */
	public List<PhoneVO> select ( String p_company ) {
		List<PhoneVO> list = null;
		SqlSession sqlSession = factory.openSession();
		
		if ( p_company.equals("all") ) {
			list = sqlSession.selectList( "phone.p_listall", p_company );
		}
		else {
			list = sqlSession.selectList( "phone.p_list", p_company );
		}
		
		sqlSession.close();
		
		return list;
	}
	
	/** ----------------------- 상품 등록 (완성) ----------------------- */
	public int p_insert ( PhoneVO vo ) {
		int res = 0;
		SqlSession sqlSession = factory.openSession();
		res = sqlSession.insert( "phone.p_insert", vo );
		sqlSession.commit();
		sqlSession.close();
		
		return res;
	}
	
	/** ----------------------- 상품 삭제 (만들어야함) ----------------------- */
	/*public void delete ( String p_idx ) {
		int res;
		SqlSession sqlSession = factory.openSession();
		res = sqlSession.delete("product.product_delete", p_idx);
		System.out.println("삭제결과 : " + res);
		sqlSession.commit();
		sqlSession.close();
	} */
	
	/** ----------------------- 클릭한 상품 상세보기 (완성) ----------------------- */
	public PhoneVO selectOne ( int p_idx ) {
		PhoneVO vo = null;
		
		SqlSession sqlSession = factory.openSession();
		vo = (PhoneVO) sqlSession.selectOne( "phone.p_selectone", p_idx );
		sqlSession.close();
		
		return vo;
	}
	
	/** ----------------------- 거래완료된상품 상태 '완료'로 바꾸기 ----------------------- */
	public void dealComplete( int p_idx ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "phone.p_dealcomplete", p_idx );
		sqlSession.commit();
		sqlSession.close();
	}

	/** ----------------------- 판매자가 입금요청 버튼누르면 PHONE테이블 상품상태를 '거래중'으로 변경 ----------------------- */
	// PhoneVO지만 BuyerVO 이용해서도 상태변경은 가능
	public void depositWaiting ( BuyerVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "phone.p_depositwaiting", vo );
		sqlSession.commit();
		sqlSession.close();
	}

	
	/** ----------------------- 메인화면 페이징 (전체게시물 개수가져오기) ----------------------- */
	public int selectCount() {
		SqlSession sqlSession = factory.openSession();
		int count = (int)sqlSession.selectOne("phone.p_count");
		sqlSession.close();
		
		return count;
	}

	/** ----------------------- 오버로딩된 select 메서드 ----------------------- */
	public List<PhoneVO> select ( HashMap<String, String> map ) {
		List<PhoneVO> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList("phone.p_listall_paging", map);
		sqlSession.close();
		
		return list;
	}
}













