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

@WebServlet("/member_update_pwd_result.do")
public class Member_Update_Pwd_Do_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("utf-8");
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String 		 membertype = String.valueOf(session.getAttribute("membertype")); // 세션 회원타입
		String 		 	     id = String.valueOf(session.getAttribute("memberID")); // 세션 회원아이디
		String 		   inputPwd = request.getParameter("inputpwd"); // 변경할 비밀번호
		
		if ( membertype.equals("일반") ) {
			G_MemberVO vo = new G_MemberVO();
			vo.setG_id(id); // 아이디와
			vo.setG_pwd(inputPwd); // 변경할 비밀번호 담아서 쿼리문으로 보냄
			
			G_MemberDAO.getInstance().updateUserPwd( vo );
			
		} else if ( membertype.equals("사업자") ) {
			B_MemberVO vo = new B_MemberVO();
			vo.setB_id(id); // 아이디와
			vo.setB_pwd(inputPwd); // 변경할 비밀번호 담아서 쿼리문으로 보냄
			
			B_MemberDAO.getInstance().updateUserPwd( vo );
		}
		
		response.sendRedirect("member_mypage_menu.do");
	}

}
