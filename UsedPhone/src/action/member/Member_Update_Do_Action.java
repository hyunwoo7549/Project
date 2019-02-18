package action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.B_MemberDAO;
import dao.G_MemberDAO;
import vo.B_MemberVO;
import vo.G_MemberVO;

@WebServlet("/member_update_result.do")
public class Member_Update_Do_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		/* 일반회원, 사업자회원 공통 파라미터 */
		String 		 membertype = String.valueOf(session.getAttribute("membertype")); // 세션 회원타입
		String 		 	     id = String.valueOf(session.getAttribute("memberID")); // 세션 회원아이디
		String 		phoneNumber = request.getParameter("phonenumber");
		
		if ( membertype.equals("일반") ) {
			G_MemberVO vo = new G_MemberVO();
			vo.setG_id(id);
			vo.setG_phone(phoneNumber);
			
			G_MemberDAO.getInstance().updateUserData( vo );
		}
		else if ( membertype.equals("사업자") ){
			B_MemberVO vo = new B_MemberVO();
			vo.setB_id(id);
			vo.setB_phone(phoneNumber);
			vo.setB_businessnumber(request.getParameter("businessnumber"));
			vo.setB_mutualname(request.getParameter("mutualname"));
			
			B_MemberDAO.getInstance().updateUserData( vo );
		}
		
		response.sendRedirect("member_mypage_menu.do");
	}

}
