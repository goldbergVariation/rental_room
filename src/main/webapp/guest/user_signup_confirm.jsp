<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者の新規登録の確認</title>
<jsp:include page="/common/head.jsp" />	
</head>

<body>
<jsp:include page="/common/header.jsp" />	
<main class="main-content">	

<table class="center-table">
	<tr>
		<td>ログインID：<c:out value="${requestScope.loginId}"/></td>
	</tr>
	<tr>
		<td>ニックネーム：<c:out value="${requestScope.nickName}"/></td>
	</tr>
</table>

<p>この内容で登録しますか？</p>

<form action="UserSignup.action" method="post">
    <input type="hidden" name="loginid" value="<%= (String) request.getAttribute("loginId") %>">
    <input type="hidden" name="nickname" value="<%= (String) request.getAttribute("nickName") %>">
    <input type="hidden" name="password" value="<%= (String) request.getAttribute("password") %>">
    <input type="hidden" name="email" value="<%= (String) request.getAttribute("email") %>">

    <input type="submit" value="はい"> 
</form><br>

<form action="user_signup.jsp" method="get">
    <input type="submit" value="いいえ">
</form>

</main>

<jsp:include page="/common/footer.jsp" />

</body>
</html>