<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>管理者新規登録の確認画面</title>
</head>

<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">

<h1>《管理者の新規登録》</h1>
<table class="center-table">
	<tr>
		<td>ログインID：<c:out value="${requestScope.loginId}"/></td>
	</tr>
	<tr>
		<td>ニックネーム：<c:out value="${requestScope.nickName}"/></td>
	</tr>
	<tr>
		<td>メールアドレス：<c:out value="${requestScope.email}"/></td>
	</tr>
</table>

<p>この内容で登録しますか？</p>

<form action="ManagerSignup.action" method="post"><!--postリクエスト-->
    <input type="hidden" name="loginId" value="${requestScope.loginId}">
    <input type="hidden" name="nickName" value="${requestScope.nickName}">
    <input type="hidden" name="password" value="${requestScope.password}">
    <input type="hidden" name="email" value="${requestScope.email}">

    <input type="submit" value="はい"> 
</form>

<br>

<form action="manager_signup.jsp" method="get">
    <input type="submit" value="いいえ">
</form>

</main>

<jsp:include page="/common/footer.jsp"/>

</body>
</html>