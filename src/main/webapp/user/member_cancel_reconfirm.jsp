<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者退会の最終確認</title>

<jsp:include page="/common/head.jsp" />  
</head>

<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">	

	<h1>《退会手続きの前にご確認ください》</h1>

		<h3>退会されますと現在のログインIDは使用できなくなります。</h3>
		<h3>新規登録の際も使用できません。</h3>
	
		<h3>よろしいですか？</h3>

    <form action="/rental_room/user/CancelUser.action" method="post"> 
		<button type="submit">はい</button>
	</form>
    
    <br>
   	<form action="/rental_room/user/mypage.jsp" method="post"> 
		<button type="submit">いいえ</button>
	</form>
    
 </main>

<jsp:include page="/common/footer.jsp"/>

</body>
</html>