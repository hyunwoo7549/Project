package action.buyer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.B_MemberDAO;
import dao.BuyerDAO;
import vo.BuyerVO;

@WebServlet("/apply_purchase.do")
public class Buyer_ApplyPurchase_Action extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* 사업자회원 매입신청하기 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 현재시간구하기 (매입한 현재시간 등록하기위해 사용)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		
		
		int p_idx = Integer.parseInt(request.getParameter("p_idx")); 	    // 제품고유번호
		String g_id = request.getParameter("g_id"); 				   		// 상품 등록자
		String b_id = request.getParameter("b_id"); 				   		// 상품 매입신청자
		String p_image_s = request.getParameter("p_image_s");		   		// 미리보기이미지
		String p_name = request.getParameter("p_name");			   			// 제품명
		int hopeprice = Integer.parseInt(request.getParameter("hopeprice"));// 희망매입가
		String regidate = sdf.format(calendar.getTime()); 		   			// 등록일시
		String status = "대기";												// 거래상태(처음에 대기)
				
		System.out.println("제품고유번호 : " + p_idx);
		System.out.println("상품등록자 : " + g_id);
		System.out.println("상품매입신청자 : " + b_id);
		System.out.println("이미지명 : " + p_image_s);
		System.out.println("상품명 : " + p_name);
		System.out.println("희망매입가 : " + hopeprice);
		System.out.println("등록시간 : " + regidate);
		
		BuyerVO vo = new BuyerVO();
		vo.setP_idx(p_idx);
		vo.setG_id(g_id);
		vo.setB_id(b_id);
		vo.setP_image_s(p_image_s);
		vo.setP_name(p_name);
		vo.setHopeprice(hopeprice);
		vo.setRegidate(regidate);
		vo.setStatus(status);
		
		// Buyer 테이블에 인서트
		BuyerDAO.getInstance().b_insert( vo );
		
		RequestDispatcher disp = request.getRequestDispatcher("phonelist.do");
		disp.forward(request, response);
		
	}

}
