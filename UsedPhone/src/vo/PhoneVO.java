package vo;

public class PhoneVO {
	private String g_id;		// 등록자 이름 
	private int p_idx;			// 제품 고유번호
	private int g_idx; 			// 고객번호
	private String p_company; 	// 제조회사
	private String p_name; 		// 모델명
	private int p_price; 	    // 희망매입가
	private String p_text; 	 	// 하자내용
	private String p_image_s; // 미리보기이미지
	private String p_image_1; // 이미지 1
	private String p_image_2; // 이미지 2
	private String p_image_3; // 이미지 3
	private String p_image_4; // 이미지 4
	private String regidate;  // 등록일자
	private String status;	  // 거래상태
	
	public PhoneVO() {}

	public int getP_idx() {
		return p_idx;
	}

	public void setP_idx(int p_idx) {
		this.p_idx = p_idx;
	}

	public int getG_idx() {
		return g_idx;
	}

	public void setG_idx(int g_idx) {
		this.g_idx = g_idx;
	}

	public String getP_company() {
		return p_company;
	}

	public void setP_company(String p_company) {
		this.p_company = p_company;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getP_price() {
		return p_price;
	}

	public void setP_price(int p_price) {
		this.p_price = p_price;
	}

	public String getP_text() {
		return p_text;
	}

	public void setP_text(String p_text) {
		this.p_text = p_text;
	}

	public String getP_image_s() {
		return p_image_s;
	}

	public void setP_image_s(String p_image_s) {
		this.p_image_s = p_image_s;
	}

	public String getP_image_1() {
		return p_image_1;
	}

	public void setP_image_1(String p_image_1) {
		this.p_image_1 = p_image_1;
	}

	public String getP_image_2() {
		return p_image_2;
	}

	public void setP_image_2(String p_image_2) {
		this.p_image_2 = p_image_2;
	}

	public String getP_image_3() {
		return p_image_3;
	}

	public void setP_image_3(String p_image_3) {
		this.p_image_3 = p_image_3;
	}

	public String getP_image_4() {
		return p_image_4;
	}

	public void setP_image_4(String p_image_4) {
		this.p_image_4 = p_image_4;
	}

	public String getRegidate() {
		return regidate;
	}

	public void setRegidate(String regidate) {
		this.regidate = regidate;
	}

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
