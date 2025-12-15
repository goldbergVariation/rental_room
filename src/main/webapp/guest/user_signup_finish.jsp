<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>利用者の登録完了</title>

<jsp:include page="/common/head.jsp" />	
</head>

<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">
    
    <h1>《管理者の新規登録完了》</h1>
    <h2>利用者新規登録を完了しました</h2>
    
    <form action="user_login.jsp" method="post">
    	<input type="submit" value="ログイン画面へ">
    </form>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>