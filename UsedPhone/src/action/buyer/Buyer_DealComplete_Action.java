package action.buyer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.B_MemberDAO;
import dao.BuyerDAO;
import dao.G_MemberDAO;
import dao.PhoneDAO;
import vo.B_MemberVO;
import vo.BuyerVO;
import vo.G_MemberVO;

@WebServlet("/dealcomplete.do")
public class Buyer_DealComplete_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String b_id = request.getParameter("b_id");	// 구매자 아이디
		String g_id = request.getParameter("g_id"); // 판매자 아이디
		int p_idx = Integer.parseInt(request.getParameter("p_idx")); // 상품 고유번호
		int hopeprice = Integer.parseInt(request.getParameter("hopeprice")); // 거래체결가격
		
		System.out.println("거래체결가격 : " + hopeprice);
		System.out.println("제품번호 : " + p_idx);
		System.out.println("판매자 아이디 : " + g_id);
		System.out.println("구매자 아이디 : " + b_id);
		
		
		// Buyer 테이블에서 거래완료된 row 상태를 '완료' 로 바꿈
		BuyerVO vo = new BuyerVO();
		vo.setB_id(b_id);
		vo.setG_id(g_id);
		vo.setHopeprice(hopeprice);
		vo.setP_idx(p_idx);
		BuyerDAO.getInstance().dealComplete( vo );
		
		
		// Phone 테이블에서 거래완료된 row의 상태를 '완료' 로 바꿈 (메인화면에서 상태보여주기위함)
		PhoneDAO.getInstance().dealComplete( p_idx );
		
		// GENERAL_MEMBER 의 지갑에 돈 넣어줌
		Map<String, Object> mapG = new HashMap<>();
		mapG.put("completionprice", hopeprice);
		mapG.put("g_id", g_id);
		G_MemberDAO.getInstance().getMoney( mapG );
		
		// BUSINESS_MEMBER 의 지갑에서 돈 빼감
		Map<String, Object> mapB = new HashMap<>();
		mapB.put("completionprice", hopeprice);
		mapB.put("b_id", b_id);
		B_MemberDAO.getInstance().dropMoney( mapB );
 		
		// 현재 세션에 접속중인 계정 정보 새로고침
		String type  = (String)session.getAttribute("membertype"); // 현재 접속한 회원 회원타입받아옴
		String 	  id = (String)session.getAttribute("memberID");   // 현재 접속한 회원 아이디 받아옴
		
		session.removeAttribute("wallet"); // 세션 지갑 remove
		if ( type.equals("일반") ) {
			
			// 세션에 다시 등록시킴 (지갑 새로고침때문에)
			G_MemberVO voG = G_MemberDAO.getInstance().getUserData( id );
			session.setAttribute("wallet", voG.getG_wallet()); // 보유한 돈
		}
		
		else if ( type.equals("사업자") ) {
			
			// 세션에 다시 등록시킴 (지갑 새로고침때문에)
			B_MemberVO voB = B_MemberDAO.getInstance().getUserData( id );
			session.setAttribute("wallet", voB.getB_wallet()); // 보유한 돈
		}
		
		response.sendRedirect("phonelist.do");
	}

}
