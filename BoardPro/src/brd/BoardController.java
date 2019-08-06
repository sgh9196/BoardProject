package brd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BoardService boardService;
	ArticleVO articleVO;

	public void init() throws ServletException {
		boardService = new BoardService();
		articleVO = new ArticleVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		String nextPage = "";
		HttpSession session1;
		HttpSession session2;
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getPathInfo();
		System.out.println("action : " + action);
		
		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		
		try {
			
			if(action.equals("/*") || action.equals("/listArticles.do")) {
				
				articlesList = boardService.listArticles();
				replyListSort(articlesList);
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
				
			}
			else if(action.equals("/articlesForm.do")) {
				nextPage = "/board01/articleForm.jsp";
			}
			else if(action.equals("/addArticle.do")) {
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				articleVO.setParentNO(0);
				articleVO.setId("Shin");
				articleVO.setTitle(title);
				articleVO.setContent(content);
				
				boardService.addArticle(articleVO);
				
				nextPage = "/board/listArticles.do";

			}
			else if(action.equals("/viewArticle.do")) {
				
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				
				articleVO = boardService.viewArticle(articleNO);
				request.setAttribute("article", articleVO);
				
				nextPage = "/board01/viewArticle.jsp";
				
			}
			else if(action.equals("/modArticle.do")) {
				
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				articleVO.setArticleNO(articleNO);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				
				boardService.modArticle(articleVO);
				
				PrintWriter out = response.getWriter();
				out.print("<script>" + " alert('글을 수정했습니다.');" + " location.href='"
							+ request.getContextPath()
							+ "/board/viewArticle.do?articleNO="
							+ articleNO + "';" + "</script>"
						
				);
				
				return;
			}
			else if(action.equals("/removeArticle.do")) {
				
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				boardService.removeArticle(articleNO);
				
				PrintWriter out = response.getWriter();
				out.print("<script>" + "  alert('글을 삭제했습니다.');" 
							+ " location.href='" + request.getContextPath()
							+ "/board/listArticles.do';" + "</script>");
				
				return;
			}
			else if(action.equals("/replyForm.do")) {
				
				int articleNO = Integer.parseInt(request.getParameter("articleNO"));
				session1 = request.getSession();
				session1.setAttribute("articleNO", articleNO);
				
				int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				session2 = request.getSession();
				session2.setAttribute("parentNO", parentNO);
				
				nextPage = "/board01/replyForm.jsp";
			}
			else if(action.equals("/addReply.do")) {
				
				session1 = request.getSession();
				int articleNO = (Integer) session1.getAttribute("articleNO");
				session1.removeAttribute("articleNO");
				
				session2 = request.getSession();
				int parentNO = (Integer) session2.getAttribute("parentNO");
				session2.removeAttribute("parentNO");
				
				//int parentNO = Integer.parseInt(request.getParameter("parentNO"));
				
				System.out.println("parentNO >> " + parentNO);
				
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				
				articleVO.setParentNO((parentNO == 0) ? articleNO : parentNO); // parentNO 받아와서 넣어야함
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setId("lee");
				
				int articleNum = boardService.addReply(articleVO);
				
				PrintWriter out = response.getWriter();
				
				out.println("<script>" + " alert('답글을 추가했습니다.');" 
								+ " location.href = '"
								+ request.getContextPath()
								+ "/board/viewArticle.do?articleNO="
								+ articleNum + "';" + "</script>"
								
					
				);  
				
				return;
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Next > " + nextPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
	
	public void replyListSort(List<ArticleVO> articlesList) {
		
		for(int i=0; i<articlesList.size(); i++) {
			
			int articleNO = articlesList.get(i).getArticleNO();
			
			for(int k=0; k<articlesList.size(); k++) {
				
				int parentNO = articlesList.get(k).getParentNO();
				
				if(articleNO == parentNO) {
					
					articleVO = articlesList.get(k);
					articlesList.remove(k);
					articlesList.add(i+1, articleVO);
					
				}
				
			}
		}
		
	}

}