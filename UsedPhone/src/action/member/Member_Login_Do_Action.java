package action.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.B_MemberDAO;
import dao.G_MemberDAO;
import vo.B_MemberVO;
import vo.G_MemberVO;

@WebServlet("/member_login_check.do")
public class Member_Login_Do_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String type = request.getParameter("membertype");
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd", pwd);
		
		String resultId = null; // DB검사하고 받아올 ID변수
		String msg = ""; // ajax 콜백함수로 내보내서 보여줄 메세지
		
		if ( type.equals("일반") ) {
			resultId = G_MemberDAO.getInstance().searchUserData( map );
			
			if ( resultId == null ) {
				msg = "no";
			} else {
				G_MemberVO vo = G_MemberDAO.getInstance().getUserData( resultId );	
				session.removeAttribute("memberID");
				session.setAttribute("memberID", vo.getG_id()); // set아이디
				session.setAttribute("memberpwd", vo.getG_pwd()); // set 비밀번호
				session.setAttribute("memberVO", vo); // set회원정보
				session.setAttribute("membertype", type); // set회원타입
				session.setAttribute("g_idx", vo.getG_idx()); // 회원번호
				session.setAttribute("wallet", vo.getG_wallet()); // 보유한 돈
				if (vo.getMaster() == 0) {
					type = "master";
					msg = "master";
					session.removeAttribute("membertype");
					session.setAttribute("membertype", type);
				} else {
					msg = "yes";
					session.setAttribute("membertype", type);
				}
			}
		}
		
		else if ( type.equals("사업자") ) {
			resultId = B_MemberDAO.getInstance().searchUserData( map );
			
			if ( resultId == null ) {
				msg = "no";
			} else {
				msg = "yes";
				B_MemberVO vo = B_MemberDAO.getInstance().getUserData( resultId );	
				session.removeAttribute("memberID");
				session.setAttribute("memberID", vo.getB_id()); // set아이디
				session.setAttribute("memberpwd", vo.getB_pwd()); // set 비밀번호
				session.setAttribute("memberVO", vo); // set회원정보
				session.setAttribute("membertype", type); // set회원타입
				session.setAttribute("b_idx", vo.getB_idx()); // 회원번호
				session.setAttribute("wallet", vo.getB_wallet()); // 보유한 돈
			}
		}
		
		String resultStr = String.format
				("[ {'msg' : '%s'}, {'type' : '%s'}, {'id' : '%s'} ]",
						msg, type, session.getAttribute("memberID"));
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().println( resultStr );
	}

}
