package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import service.MyBatisConnector;
import vo.BuyerVO;

public class BuyerDAO {
	private static BuyerDAO single = null;
	SqlSessionFactory factory = null;
	
	public static BuyerDAO getInstance() {
		if(single == null)
			single = new BuyerDAO();
		return single;
	}
	
	public BuyerDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	/** 안쓰이는듯 없앨수도 있음 **/
	/** ----------------------- 클릭한 제조회사 모델 리스트 조회 (완성) ----------------------- */
	public List<BuyerVO> select ( int idx ) {
		List<BuyerVO> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList( "buyer.p_listall2", idx );
		sqlSession.close();
		
		return list;
	}
	
	/** ----------------------- 매입신청후 DB에 등록 (완성) ----------------------- */
	public void b_insert ( BuyerVO vo ) {
		int res = 0;
		SqlSession sqlSession = factory.openSession();
		res = sqlSession.insert( "buyer.bu_insert", vo );
		System.out.println("인서트 성공여부 : " + res);
		
		sqlSession.commit();
		sqlSession.close();
	}

	/** ----------------------- 상품번호로 제품 현재 클릭한상품의 매입자 전체 리스트 가져옴 ----------------------- */
	public List<BuyerVO> setectBuyerAll ( int p_idx ) {
		List<BuyerVO> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList( "buyer.bu_selectBuyerAll", p_idx );
		sqlSession.close();
		
		return list;
	}
	
	/** ----------------------- 판매자가 입금요청 버튼누르면 BUYER테이블 해당 구매자의 상태를 '입금대기'로 변경 ----------------------- */
	public void depositWaiting ( BuyerVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "buyer.bu_depositwaiting", vo );
		sqlSession.commit();
		sqlSession.close();
	}
	
	/** ----------------------- 거래 성사되지못한 구매자들의 상태를 '미체결' 로 변경 ----------------------- */
	public void failtodeal ( BuyerVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "buyer.bu_failtodeal", vo );
		sqlSession.commit();
		sqlSession.close();
	}

	/** ----------------------- 거래 성사되면 '입금대기' 에서 '완료' 로 변경 (완성) ----------------------- */
	public void dealComplete ( BuyerVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update( "buyer.dealComplete", vo );
		sqlSession.commit();
		sqlSession.close();
	}
	
	
	// ------------------------------------------------------- //
	

	/** ----------------------- [판매자(일반회원)] 상태가 '완료' 인 모든정보 가져오기 (완성) ----------------------- */
	public List<BuyerVO> G_getMyDealHistory ( String g_id ) {
		List<BuyerVO> vo = null;
		
		SqlSession sqlSession = factory.openSession();
		HashMap<String,String> map = new HashMap<>();
		map.put("g_id", g_id);
		map.put("w1", "완료");
		map.put("w2", "완료");
		
		vo = sqlSession.selectList( "buyer.g_deal_progress", map );
		sqlSession.close();
		
		return vo;
	}
	
	/** ----------------------- [판매자(일반회원)] 상태가 '입금대기', '대기' 인 모든정보 가져오기 (완성) ----------------------- */
	public List<BuyerVO> G_getMyProgress ( String g_id ) {
		List<BuyerVO> vo = null;
		
		SqlSession sqlSession = factory.openSession();
		HashMap<String,String> map = new HashMap<>();
		map.put("g_id", g_id);
		map.put("w1", "입금대기");
		map.put("w2", "대기");
		
		vo = sqlSession.selectList( "buyer.g_deal_progress", map );
		sqlSession.close();
		
		return vo;
	}
	
	/** ----------------------- [구매자(사업자회원)] 상태가 '완료', '미체결' 인 모든정보 가져오기 (완성) ----------------------- */
	public List<BuyerVO> B_getMyDealHistory ( String b_id ) {
		List<BuyerVO> vo = null;
		
		SqlSession sqlSession = factory.openSession();
		HashMap<String,String> map = new HashMap<>();
		map.put("b_id", b_id);
		map.put("w1", "완료");
		map.put("w2", "미체결");
		
		vo = sqlSession.selectList( "buyer.b_deal_progress", map );
		sqlSession.close();
		
		return vo;
	}
	
	/** ----------------------- [구매자(사업자회원)] 상태가 '대기', '입금대기' 인 모든정보 가져오기 (완성) ----------------------- */
	public List<BuyerVO> B_getMyProgress ( String b_id ) {
		List<BuyerVO> vo = null;
		
		SqlSession sqlSession = factory.openSession();
		HashMap<String,String> map = new HashMap<>();
		map.put("b_id", b_id);
		map.put("w1", "입금대기");
		map.put("w2", "대기");
		
		vo = sqlSession.selectList( "buyer.b_deal_progress", map );
		sqlSession.close();
		
		return vo;
	}
	
}
