package action.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDAO;
import dao.Free_BoardDAO;
import vo.CommentVO;
import vo.Free_BoardVO;

/**
 * Servlet implementation class FreeBoard_Comment_Modify_Action
 */
@WebServlet("/free_board_comment_modify_result.do")
public class FreeBoard_Comment_Modify_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		int com_idx = 0;
		if(request.getParameter("com_idx") != null){
			com_idx = Integer.parseInt(request.getParameter("com_idx"));}
		
		
		String com_comment = request.getParameter("com_comment");
		
		CommentVO commemt = new CommentVO();
		commemt.setCom_idx(com_idx);
		commemt.setCom_comment(com_comment);
		
		CommentDAO dao = CommentDAO.getInstance();
		dao.update_Comment(commemt);
		
		response.sendRedirect("free_board_main.do");
	}

}
