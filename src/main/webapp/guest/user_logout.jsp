<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト完了</title>

<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">	

   	<h1>《ログアウトしました 》</h1> 
    <form action="/rental_room/guest/user_login.jsp" method="post">
    <input type="submit" value="ログイン画面へ戻る">
    </form>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>