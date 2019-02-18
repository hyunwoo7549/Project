package action.buyer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.BuyerDAO;
import dao.PhoneDAO;
import vo.BuyerVO;

@WebServlet("/deposit_waiting.do")
public class Buyer_DepositWaiting_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String b_id = request.getParameter("b_id");	// 구매자 아이디
		String g_id = request.getParameter("g_id"); // 판매자 아이디
		int p_idx = Integer.parseInt(request.getParameter("p_idx")); // 상품 고유번호
		int hopeprice = Integer.parseInt(request.getParameter("hopeprice")); // 구입희망가격
		
		System.out.println(b_id);
		System.out.println(g_id);
		System.out.println(p_idx);
		System.out.println(hopeprice);
		
		
		BuyerVO vo = new BuyerVO();
		vo.setB_id(b_id);
		vo.setG_id(g_id);
		vo.setHopeprice(hopeprice);
		vo.setP_idx(p_idx);
		
		// 판매자가 입금요청 버튼누르면 BUYER테이블 상태를 '입금대기'로 변경
		BuyerDAO.getInstance().depositWaiting( vo );
		
		// 판매자가 입금요청 버튼누른 PHONE 상태를 '입금대기'로 변경
		PhoneDAO.getInstance().depositWaiting( vo );
		
		
				
		// 거래 성사되지못한 구매자들의 상태를 '미체결' 로 변경
		BuyerDAO.getInstance().failtodeal( vo );
		
		response.sendRedirect("phone_status.do");
	}

}
