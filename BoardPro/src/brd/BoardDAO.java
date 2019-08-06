package brd;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	private DataSource dataFactory;
	private Connection con;
	private PreparedStatement pstmt;
	
	
	/* DataBase Connection */
	public BoardDAO() {
		
		try {
			
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/* Get : Board Table List  */
	public List<ArticleVO> getArticleList() {
		
		List<ArticleVO> articleList = new ArrayList<ArticleVO>();
		
		try {
			
			con = dataFactory.getConnection();
			String query = "SELECT * FROM t_Board ORDER BY parentNO ASC, articleNO DESC";
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date writedate = rs.getDate("writedate");
				String id = rs.getString("id");
				
				articleList.add(new ArticleVO(articleNO, parentNO, title, content, writedate, id));
				
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch(Exception e) { 
			e.printStackTrace();
		}
		
		return articleList;
		
	}
	
	public ArticleVO selectArticle(int articleNO) {
		
		ArticleVO article = new ArticleVO();
		
		try {
			
			con = dataFactory.getConnection();
			String query = "SELECT * FROM t_Board WHERE articleNO = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			article.setArticleNO(rs.getInt("articleNO"));
			article.setParentNO(rs.getInt("parentNO"));
			article.setTitle(rs.getString("title"));
			article.setContent(rs.getString("content"));
			article.setWritedate(rs.getDate("writedate"));
			article.setId(rs.getString("id"));
			
			rs.close(); pstmt.close(); con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return article;
		
	}
	
	public int getNewArticleNO() {
		
		try {
			
			con = dataFactory.getConnection();
			String query ="SELECT MAX(articleNO) FROM t_Board";
			pstmt = con.prepareStatement(query);
			
			ResultSet rs = pstmt.executeQuery(query);
			
			if(rs.next()) return (rs.getInt(1) + 1);
			
			rs.close(); pstmt.close(); con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int insertNewArticle(ArticleVO article) {
		
		int articleNO = getNewArticleNO();
		
		try {
			
			con = dataFactory.getConnection();
			
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			
			String query = "INSERT INTO t_Board(articleNO, parentNO, title, content, id)";
			query += " VALUES(?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, parentNO);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, id);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return articleNO;
		
	}
	
	public void updateArticle(ArticleVO article) {
		
		int articleNO = article.getArticleNO();
		String title = article.getTitle();
		String content = article.getContent();
		
		try {
			
			con = dataFactory.getConnection();
			String query = "UPDATE t_Board SET title=?, content=? WHERE articleNO=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, articleNO);
			
			pstmt.executeUpdate();
			pstmt.close(); con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteArticle(int articleNO) {
		
		try {
			
			con = dataFactory.getConnection();
			
			String query = "DELETE FROM t_Board WHERE articleNO=? OR parentNO=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, articleNO);
			pstmt.setInt(2, articleNO);
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

/* 
CREATE TABLE t_Board(
	articleNO INT(11) PRIMARY KEY,
	parentNO INT(11) DEFAULT 0,
	title VARCHAR(500) NOT NULL,
	content VARCHAR(4000),
	writedate DATETIME DEFAULT CURRENT_TIMESTAMP,
	id VARCHAR(10)
);

INSERT INTO t_Board VALUES(1, 0, '테스트글입니다.', '테스트글입니다', NOW(), 'hong');
INSERT INTO t_Board VALUES(2, 0, '안녕하세요.', '상품 후기입니다.', NOW(), 'hong');
INSERT INTO t_Board VALUES(3, 2, '답변입니다.', '상품 후기에 대한 답변입니다.', NOW(), 'hong');
INSERT INTO t_Board VALUES(4, 0, '김유신입니다.', '김유신 테스트글입니다.', NOW(), 'kim');
INSERT INTO t_Board VALUES(5, 2, '답변입니다.', '상품좋습니다.', NOW(), 'lee');
INSERT INTO t_Board VALUES(6, 2, '상품 후기입니다.', '이순신씨의 상품 사용 후기를 올립니다.', NOW(), 'lee');
*/