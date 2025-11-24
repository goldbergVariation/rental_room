<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト確認</title>
<jsp:include page="/common/head.jsp" />

<title>ログアウト</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">
    
	<h1>《ログアウト確認 》</h1> 
	<h2>ログアウトしますか？</h2>
    	<form action="ManagerLogout.action" method="post">
    	<input type="submit" value="はい">
       </form>
       <br>
        <form action="/rental_room/guest/top.jsp" method="post">
    	<input type="submit" value="いいえ">
       </form>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>