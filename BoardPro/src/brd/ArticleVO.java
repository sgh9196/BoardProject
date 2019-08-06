package brd;

import java.sql.Date;

public class ArticleVO {
	
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private Date writedate;
	private String id;
	
	public ArticleVO() {}
	
	public ArticleVO(int articleNO, int parentNO, String title, String content, Date writedate, String id) {
		this.articleNO = articleNO;
		this.parentNO = parentNO;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.id = id;
	}
	
	public int getArticleNO() { return articleNO; }
	public void setArticleNO(int articleNO) { this.articleNO = articleNO; }
	
	public int getParentNO() { return parentNO; }
	public void setParentNO(int parentNO) { this.parentNO = parentNO; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public Date getWritedate() { return writedate; }
	public void setWritedate(Date writedate) { this.writedate = writedate; }
	
	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	@Override
	public String toString() {
		return "ArticleVO [articleNO=" + articleNO + ", parentNO=" + parentNO + ", title=" + title + ", content="
				+ content + ", writedate=" + writedate + ", id=" + id + "]";
	}
	
}