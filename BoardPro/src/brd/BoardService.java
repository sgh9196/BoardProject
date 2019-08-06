package brd;

import java.util.List;

public class BoardService {
	
	public BoardDAO boardDAO;	
	
	public BoardService() {
		boardDAO = new BoardDAO();
	}
	
	public List<ArticleVO> listArticles()  {
		
		List<ArticleVO> articlesList = boardDAO.getArticleList();
		return articlesList;
		
	}
	
	public void addArticle(ArticleVO articleVO) {
		boardDAO.insertNewArticle(articleVO);
	}
	
	public ArticleVO viewArticle(int articleNO) {
		
		ArticleVO article = null;
		article = boardDAO.selectArticle(articleNO);
		return article;
		
	}
	
	public void modArticle(ArticleVO article) {
		boardDAO.updateArticle(article);
	}
	
	public int addReply(ArticleVO article) {
		return boardDAO.insertNewArticle(article);
	}
	
	public void removeArticle(int articleNO) {
		boardDAO.deleteArticle(articleNO);
	}
	
}