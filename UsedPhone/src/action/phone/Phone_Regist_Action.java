package action.phone;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/phone_regist.do")
public class Phone_Regist_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 단말기 등록페이지로 가기전에 할일 여기서함
		
		RequestDispatcher disp = request.getRequestDispatcher("shop/phone_regist.jsp");
		disp.forward(request, response);
	}

}
