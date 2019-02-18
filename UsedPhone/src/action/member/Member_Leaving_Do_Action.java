package action.member;

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
import dao.G_MemberDAO;

@WebServlet("/member_leaving_result.do")
public class Member_Leaving_Do_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String 	 ages = request.getParameter("ages"); 	// 연령대 파라미터
		String reason = request.getParameter("reason"); // 탈퇴사유 파라미터
		String id = request.getParameter("id");
		
		Map<String, String> map = new HashMap<>();
		map.put("ages", ages);
		map.put("reason", reason); // 맵에 담아서 DB에 저장 (VO는 필요없어서 생성안함)
		map.put("id", id);
		
		String membertype = String.valueOf(session.getAttribute("membertype")); // 세션에서 회원타입 가져옴
		
		if ( membertype.equals("일반") ) {
			id = String.valueOf(session.getAttribute("memberID")); // 세션에서 아이디 가져옴 (탈퇴할떄 필요함)
			
			G_MemberDAO.getInstance().insertLeavingReason( map );
			G_MemberDAO.getInstance().deleteUserData( id );
			
			session.invalidate(); // 세션에 저장된 값 전부 제거
		}
		else if ( membertype.equals("사업자") ) {
			id = String.valueOf(session.getAttribute("memberID")); // 세션에서 아이디 가져옴 (탈퇴할떄 필요함)

			B_MemberDAO.getInstance().insertLeavingReason( map );
			B_MemberDAO.getInstance().deleteUserData( id );
			
			session.invalidate(); // 세션에 저장된 값 전부 제거
		}
		
		response.sendRedirect("phonelist.do");
	}

}
