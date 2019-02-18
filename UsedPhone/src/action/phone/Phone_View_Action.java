package action.phone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BuyerDAO;
import dao.PhoneDAO;
import vo.BuyerVO;
import vo.PhoneVO;

@WebServlet("/phone_view.do")
public class Phone_View_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		int p_idx = Integer.parseInt(request.getParameter("p_idx")); // 받아온 상품번호
		
		// 상품번호로 제품 상세내용 가져옴
		PhoneVO phoneVO = PhoneDAO.getInstance().selectOne( p_idx );
		
		// 상품번호로 제품 현재 클릭한상품의 매입자 '대기' 상태인 전체 리스트 가져옴 (이중 top3 뽑고, 매도 2번하는지 검사할거임)
		List<BuyerVO> list = BuyerDAO.getInstance().setectBuyerAll ( p_idx );
		
		
		
		// 넘어갈 다음페이지의 자바스크립트에서 2번 매입신청 못하게 막는 유효성검사때 필요함 (리스트로 검사후 boolean넘김)
		String memberID = (String)session.getAttribute("memberID");	 // 현재 로그인중인아이디
		boolean doNotBuyAgain = false;					 // 구매자목록에 아이디가 있으면 true로 변환
		
		for (int i=0; i < list.size(); i++) {
			if (memberID.equals(list.get(i).getB_id())) {
				doNotBuyAgain = true;
			}
		}
		
		// vo에 담아서 포워딩
		request.setAttribute("vo", phoneVO);
		request.setAttribute("list", list);
		request.setAttribute("doNotBuyAgain", doNotBuyAgain);
		
		RequestDispatcher disp = request.getRequestDispatcher("shop/phone_view.jsp");
		disp.forward(request, response);
	}
}










