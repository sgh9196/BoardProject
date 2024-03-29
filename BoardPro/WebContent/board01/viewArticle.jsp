<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"
%>

<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>

<c:set var='contextPath' value='${pageContext.request.contextPath}' />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>글 상세 정보</title>

<script type="text/javascript">
	
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
	
	function fn_enable(obj) {
		document.getElementById("i_title").disabled = false;
		document.getElementById("i_content").disabled = false;
		document.getElementById("tr_btn_modify").style.disply = "block";
		document.getElementById("tr_btn").style.disply = "none";
	}
	
	function fn_modify_article(obj) {
		obj.action = "${contextPath}/board/modArticle.do";
		obj.submit();
	}
	
	function fn_remove_article(url,articleNO){
		
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		
		var articleNOInput = document.createElement("input");
		articleNOInput.setAttribute("type","hidden");
		articleNOInput.setAttribute("name","articleNO");
		articleNOInput.setAttribute("value", articleNO);
		 
		form.appendChild(articleNOInput);
		document.body.appendChild(form);
		form.submit();
		
	}
	
	function fn_reply_form(url, articleNO){
		
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		
		var parentNOInput = document.createElement("input");
		parentNOInput.setAttribute("type","hidden");
		parentNOInput.setAttribute("name","articleNO");
		parentNOInput.setAttribute("value", articleNO);
		 
		form.appendChild(parentNOInput);
		document.body.appendChild(form);
		form.submit();

	}
	
</script>

</head>
<body>

	<form name='frmArticle' method='post' action='${contextPath}'>
		
		<table border='0' align='center'>
			<tr>
				<td width='150' align='center' bgcolor='#FF9933'>
					글번호
				</td>
				<td>
					<input type='text' value='${article.articleNO}' disabled />
					<input type='hidden' name='articleNO' value='${article.articleNO}' /> 
				</td>
			</tr>
			
			<tr>
				<td width='150' align='center' bgcolor='#FF9933'>
					작성자 아이디
				</td>
				<td>
					<input type='text' value='${article.id}' name='writer' disabled />
				</td>
			</tr>
			
			<tr>
				<td width='150' align='center' bgcolor='#FF9933'>
					제목
				</td>
				<td>
					<input type='text' value='${article.title}' name='title' id='i_title' disabled />
				</td>
			</tr>
			
			<tr>
				<td width='150' align='center' bgcolor='#FF9933'>
					내용
				</td>
				<td>
					<textarea rows='20' cols='60' name='content' id='i_content' disabled>${article.content}</textarea>
				</td>
			</tr>
			
			<tr>
				<td width='20%' align='center' bgcolor='#FF9933'>
					등록일자
				</td>
				<td>
					<input type='text' value='<fmt:formatDate value='${article.writedate}' />' disabled /> 
				</td>
			</tr>
			
			<tr id='tr_btn_modify'>
				<td colspan='2' align='center'>
					<input type='button' value='수정반영하기' onClick='fn_modify_article(frmArticle)' />
					<input type='button' value='취소' onClick='backToList(frmArticle)' />
				</td>
			</tr>

			<tr id="tr_btn">
				<td colspan=2 align=center>
					<input type='button' value="수정하기" onClick="fn_enable(this.form)">
					<input type='button' value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
					<input type='button' value="리스트로 돌아가기" onClick="backToList(this.form)"> 
					<input type='button' value="답글쓰기" onClick="fn_reply_form('${contextPath}/board/replyForm.do?parentNO='+${article.parentNO}, ${article.articleNO})">
				</td>
			</tr>

		</table>
		
	</form>

</body>
</html>