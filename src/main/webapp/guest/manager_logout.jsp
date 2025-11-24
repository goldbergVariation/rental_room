<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ログアウト完了</title>
<link rel="stylesheet" href="<c:url value='/css/manager_style.css' />">
<link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@500&display=swap" rel="stylesheet">
</head>

<body>

<main class="main-content">
   	<h1>《ログアウトしました 》</h1> 
    <form action="/rental_room/guest/manager_login.jsp" method="post">
    <input type="submit" value="ログイン">
    </form>
</main>

</body>
</html>