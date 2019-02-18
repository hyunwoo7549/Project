package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import service.MyBatisConnector;
import vo.Free_BoardVO;

public class Free_BoardDAO {
private static Free_BoardDAO single = null;
	
	public static Free_BoardDAO getInstance() {
		if(single == null)
			single = new Free_BoardDAO();
		return single;
	}
	
	SqlSessionFactory factory = null;
	
	public Free_BoardDAO() {
		factory = MyBatisConnector.getInstance().getSqlSessionFactory();
	}
	
	/**------------------------ 게시물 등록 ------------------------*/
	public void insert_Board( Free_BoardVO board ) {
		
		SqlSession sqlSession = factory.openSession();
		sqlSession.insert( "free_board.free_board_insert", board );
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	/**------------------------ 전체 게시물 불러오기 ------------------------*/
	public List<Free_BoardVO> select_AllBoard() {
		List<Free_BoardVO> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList( "free_board.free_board_listAll" );
		sqlSession.close();
		
		return list;
	}
	
	/**------------------------ 게시물 검색 ------------------------*/
	 public List<Free_BoardVO> select_Search_Board ( Map<String, String> map ) {
		List<Free_BoardVO> list = null;
		
		SqlSession sqlSession = factory.openSession();
		list = sqlSession.selectList( "free_board.free_board_list", map );
		sqlSession.close();
		
		return list;
	}
	
	/**------------------------ 게시물 상세보기 ------------------------*/
	public Free_BoardVO select_One ( int fb_idx ) {
		
		Free_BoardVO vo = null;
		SqlSession sqlSession = factory.openSession();
		vo = sqlSession.selectOne( "free_board.free_board_one", fb_idx );
		sqlSession.close();
		
		return vo;
	}
	
	/**------------------------ 게시물 조회수 증가 ------------------------*/
	public int update_View ( int fb_idx ) {
		
		int viewNum = 0;
		SqlSession sqlSession = factory.openSession();
		viewNum = sqlSession.update( "free_board.free_board_viewnum", fb_idx );
		sqlSession.commit();
		sqlSession.close();
		
		return viewNum;
	}	

	/**------------------------ 게시물 삭제 ------------------------*/
	public void delete_Board ( int fb_idx ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.delete("free_board.free_board_delete", fb_idx);
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	/**------------------------ 게시물 수정 ------------------------*/
	public void update_Board ( Free_BoardVO vo ) {
		SqlSession sqlSession = factory.openSession();
		sqlSession.update("free_board.free_board_update", vo );
		sqlSession.commit();
		sqlSession.close();
		
	}
	
	/**------------------------ 게시물 갯수 구하기 ------------------------*/
	public int selectCount() {
		SqlSession sqlSession = factory.openSession();
		int count = (int)sqlSession.selectOne("free_board_count");
		sqlSession.close();
		return count;
	}

	// 오버로딩된 select 메서드
	public List<Free_BoardVO> select(HashMap<String, String> map) {
		List<Free_BoardVO> list = null;
		
		SqlSession sqlSession = factory.openSession();
		
		list = sqlSession.selectList("free_board_list_page", map);
		sqlSession.close();
		
		
		return list;
	}
}

