package vo;

public class BuyerVO {
	private int bu_idx; 			// 테이블 시퀀스번호
	private int p_idx; 				// 제품번호
	private String g_id; 			// 상품등록자 아이디
	private String b_id; 			// 상품 매입신청자 아이디
	private String p_image_s;		// 미리보기이미지
	private String p_name;			// 상품명
	private int hopeprice;			// 희망매입가
	private String regidate;		// 등록일
	private String status;	 		// 거래 진행 상태
	
	public BuyerVO() {}

	public int getBu_idx() {
		return bu_idx;
	}

	public void setBu_idx(int bu_idx) {
		this.bu_idx = bu_idx;
	}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getP_image_s() {
		return p_image_s;
	}

	public void setP_image_s(String p_image_s) {
		this.p_image_s = p_image_s;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getHopeprice() {
		return hopeprice;
	}

	public void setHopeprice(int hopeprice) {
		this.hopeprice = hopeprice;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
