<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>답글쓰기</title>

<script type="text/javascript">
	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
</script>

</head>
<body>

	<h1 style="text-align:center"></h1>
	<form name="frmReply" method="post" action="${contextPath}/board/addReply.do">
		
		<table align="center">
			
			<tr>
				<td align="right">글쓴이:&nbsp;</td>
				<td><input type="text" size="5"  value="lee" disabled /></td>
			</tr>
			
			<tr>
				<td align="right">글제목:&nbsp;</td>
				<td><input type="text" size="67" maxlength="100" name="title" /></td>
			</tr>
			
			<tr>
				<td align="right">글내용 : &nbsp; </td>
				<td><textarea name="content" rows="10" maxlength="4000" cols="65"></textarea></td>
			</tr>
			
			<tr>
				<td align="right"></td>
				<td>
					<input type="submit" value="답글반영하기" />
					<input type="button" value="취소" onClick="backToList(this.form)" />
				</td>
			</tr>
			
		</table>
		
	</form>

</body>
</html>