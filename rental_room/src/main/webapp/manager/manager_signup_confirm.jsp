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
	ログインID：<c:out value="${sessionScope.loginId}"/></td><br>
	使用者名：<c:out value="${sessionScope.nickName}"/><br>
	パスワード：<c:out value="${sessionScope.hidePass}"/><br>
	
<p>この内容で登録しますか？</p>


<form action="ManagerSignup.action" method="post"><!--postリクエスト-->
 <!-- 値を再送する（hiddenで送る） -->
    <input type="hidden" name="loginId" value="<%= (String) session.getAttribute("loginId") %>">
    <input type="hidden" name="nickName" value="<%= (String) session.getAttribute("nickName") %>">
    <input type="hidden" name="password" value="<%= (String) session.getAttribute("password") %>">

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