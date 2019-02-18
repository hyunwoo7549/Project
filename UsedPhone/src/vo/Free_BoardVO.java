package vo;

public class Free_BoardVO {
	private String 		rowNum; 		// 게시물 순서
	private int			fb_idx;			// 자유게시판 번호
	private String		fb_title;		// 자유게시판 제목
	private String		fb_writer;		// 자유게시판 작성자
	private String 		fb_uploadDate;	// 자유게시판 작성일
	private String		fb_content;		// 자유게시판 내용
	private int			fb_viewNum;		// 자유게시판 조회수
	private String 		fb_pwd; 	 	// 게시글 비밀번호
	
	public Free_BoardVO() {}

	public int getFb_idx() {
		return fb_idx;
	}

	public void setFb_idx(int fb_idx) {
		this.fb_idx = fb_idx;
	}

	public String getFb_title() {
		return fb_title;
	}

	public void setFb_title(String fb_title) {
		this.fb_title = fb_title;
	}

	public String getFb_writer() {
		return fb_writer;
	}

	public void setFb_writer(String fb_writer) {
		this.fb_writer = fb_writer;
	}

	public String getFb_uploadDate() {
		return fb_uploadDate;
	}

	public void setFb_uploadDate(String fb_uploadDate) {
		this.fb_uploadDate = fb_uploadDate;
	}

	public String getFb_content() {
		return fb_content;
	}

	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}

	public int getFb_viewNum() {
		return fb_viewNum;
	}

	public void setFb_viewNum(int fb_viewNum) {
		this.fb_viewNum = fb_viewNum;
	}

	public String getFb_pwd() {
		return fb_pwd;
	}

	public void setFb_pwd(String fb_pwd) {
		this.fb_pwd = fb_pwd;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	
}

