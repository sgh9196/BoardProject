<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"
	isELIgnored="false"
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기창</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>
</head>
<body>
	
	<h1 style="text-align:center">새 글 쓰기</h1>
	<form name='articleForm' method='post' action='${contextPath}/board/addArticle.do'>
		<table border='0' align='center'>
			
			<tr>
				<td align='right'>글제목 : </td>
				<td colspan='2'><input type='text' size='67' maxlegth='500' name='title' /></td>
			</tr>
			
			<tr>
				<td align='right'>글쓰기 : </td>
				<td colspan='2'><textarea name='content' rows='10' cols='65' maxlength='4000'></textarea></td>
			</tr>
			
			<tr>
				<td align='right'></td>
				<td colspan='2'>
					<input type='submit' value='글쓰기' />
					<input type='button' value='목록보기' onClick='backToList(this.form)' />
				</td>
			</tr>
			
		</table>
	</form>
	
</body>
</html>