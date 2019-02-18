package action.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member_search.do")
public class Member_Search_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디, 비밀번호찾기 페이지로 이동하기 전에 할일 여기서 함
		
		RequestDispatcher disp = request.getRequestDispatcher("member/member_search.jsp");
		disp.forward(request, response);
	}

}
