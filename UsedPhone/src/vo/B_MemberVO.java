package vo;

/** 사업자회원 정보 */
public class B_MemberVO {
	private int b_idx;				 // 사업자회원 고객번호
	private String b_id;			 // 사업자회원 아이디
	private String b_pwd;			 // 사업자회원 비밀번호
	private String b_phone;			 // 사업자회원 연락처
	private String b_businessnumber; // 사업자회원 사업자번호
	private String b_mutualname;	 // 사업자회원 상호명
	private int b_wallet;			 // 사업자회원 보유한 머니 (처음에 x만원지급)
	private int master;
	
	public B_MemberVO() {}

	public int getB_idx() {
		return b_idx;
	}

	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}

	public String getB_id() {
		return b_id;
	}

	public void setB_id(String b_id) {
		this.b_id = b_id;
	}

	public String getB_pwd() {
		return b_pwd;
	}

	public void setB_pwd(String b_pwd) {
		this.b_pwd = b_pwd;
	}

	public String getB_phone() {
		return b_phone;
	}

	public void setB_phone(String b_phone) {
		this.b_phone = b_phone;
	}

	public String getB_businessnumber() {
		return b_businessnumber;
	}

	public void setB_businessnumber(String b_businessnumber) {
		this.b_businessnumber = b_businessnumber;
	}

	public String getB_mutualname() {
		return b_mutualname;
	}

	public void setB_mutualname(String b_mutualname) {
		this.b_mutualname = b_mutualname;
	}

	public int getB_wallet() {
		return b_wallet;
	}

	public void setB_wallet(int b_wallet) {
		this.b_wallet = b_wallet;
	}

	public int getMaster() {
		return master;
	}

	public void setMaster(int master) {
		this.master = master;
	}
	
	

}
