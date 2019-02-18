package action.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.B_MemberDAO;
import dao.G_MemberDAO;

@WebServlet("/member_search_result.do")
public class Member_Search_Do_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id"); // 받아온 아이디
		String phonenumber = request.getParameter("phoneNumber"); // 받아온 휴대폰번호
		String membertype = request.getParameter("membertype");
		String resultPwd = "";
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("phonenumber", phonenumber); // 맵에 담아서 보냄
		
		if ( membertype.equals("일반") ) {
			resultPwd = G_MemberDAO.getInstance().searchUserPwd( map );
		}
		
		else if ( membertype.equals("사업자") ) {
			resultPwd = B_MemberDAO.getInstance().searchUserPwd( map );
		}
		
		request.setAttribute("resultPwd", resultPwd);

		RequestDispatcher disp = request.getRequestDispatcher("member/member_search_result.jsp");
		disp.forward(request, response);
		
	}

}
