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
import vo.BuyerVO;

@WebServlet("/phone_status.do")
public class Phone_Progress_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** -------------- 현재 구매 / 판매가능한 단말기보기 --------------*/
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String membertype = (String) session.getAttribute("membertype");
		System.out.println("멤버타입체크 : " + membertype);
		
		if ( membertype.equals("일반") ) {
			String g_id = (String) session.getAttribute("memberID");
			System.out.println("현재 접속한 회원의 아이디 : " + g_id);
			
			List<BuyerVO> list = BuyerDAO.getInstance().G_getMyProgress( g_id );
			
			request.setAttribute("list", list);
			
			RequestDispatcher disp = request.getRequestDispatcher("shop/phone_status_Gmember.jsp");
			disp.forward(request, response);
		}
		
		else if ( membertype.equals("사업자") ) {
			String b_id = (String) session.getAttribute("memberID");
			System.out.println("현재 접속한 회원의 아이디 : " + b_id);
			
			List<BuyerVO> list = BuyerDAO.getInstance().B_getMyProgress( b_id );
						
			request.setAttribute("list", list);
			
			RequestDispatcher disp = request.getRequestDispatcher("shop/phone_status_Bmember.jsp");
			disp.forward(request, response);
		}
		
	}

}
