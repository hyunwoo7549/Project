package action.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.B_MemberDAO;
import dao.G_MemberDAO;
import vo.B_MemberVO;
import vo.G_MemberVO;

@WebServlet("/member_join_result.do")
public class Member_Join_Do_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String membertype = request.getParameter("membertype"); // 회원가입유형 받아옴
		
		if ( membertype.equals("일반") ) {
			G_MemberVO vo = new G_MemberVO();
			vo.setG_id(request.getParameter("id"));
			vo.setG_pwd(request.getParameter("pwd"));
			vo.setG_phone(request.getParameter("phone_number"));
			vo.setG_wallet(0); // 회원가입시 지갑에 0원임
			vo.setMaster(1); // 회원가입시 자동으로 1로 지정 (0번은 관리자임)
			
			G_MemberDAO.getInstance().insertUserData( vo );
			
		} else if ( membertype.equals("사업자") ) {
			B_MemberVO vo = new B_MemberVO();
			vo.setB_id(request.getParameter("id"));
			vo.setB_pwd(request.getParameter("pwd"));
			vo.setB_phone(request.getParameter("phone_number"));
			vo.setB_businessnumber(request.getParameter("b_number"));
			vo.setB_mutualname(request.getParameter("b_mutualname"));
			vo.setB_wallet(1000000); // 회원가입시 지갑에 100만원 넣어줌
			vo.setMaster(2);
			
			B_MemberDAO.getInstance().insertUserData( vo );
		} 
		
		response.sendRedirect("phonelist.do");
	}

}
