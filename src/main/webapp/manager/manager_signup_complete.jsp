<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>管理者新規登録完了</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">
    <h1>《管理者の新規登録完了》</h1>
    
    <h2>管理者新規登録を完了しました</h2>

	<br>
    <form action="/rental_room/guest/manager_login.jsp" method="post">
    	<input type="submit" value="ログイン画面へ">
    </form>
</main> 

<jsp:include page="/common/footer.jsp"/>
</body>
</html>