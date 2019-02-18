package vo;

/** 일반회원 정보 */
public class G_MemberVO {
	private int master;			// 회원가입시 자동으로 1로 지정	
	private String g_idx; 		// 일반회원 고객번호
	private String g_id; 		// 일반회원 아이디
	private String g_pwd; 		// 일반회원 비밀번호
	private String g_phone; 	// 일반회원 연락처
	private int g_wallet;		// 일반회원 보유한 머니 (처음에 0)

	public G_MemberVO() {}

	public String getG_idx() {
		return g_idx;
	}

	public void setG_idx(String g_idx) {
		this.g_idx = g_idx;
	}

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getG_pwd() {
		return g_pwd;
	}

	public void setG_pwd(String g_pwd) {
		this.g_pwd = g_pwd;
	}

	public String getG_phone() {
		return g_phone;
	}

	public void setG_phone(String g_phone) {
		this.g_phone = g_phone;
	}

	public int getG_wallet() {
		return g_wallet;
	}

	public void setG_wallet(int g_wallet) {
		this.g_wallet = g_wallet;
	}

	public int getMaster() {
		return master;
	}

	public void setMaster(int master) {
		this.master = master;
	}
	
	
}
