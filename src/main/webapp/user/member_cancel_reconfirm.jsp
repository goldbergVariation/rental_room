<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者退会の最終確認</title>

<jsp:include page="/common/head.jsp" />  <!-- ✅ CSSなど共通設定を読み込む -->
</head>

<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">	<!-- 装飾設定の読み込み -->

	<h1>《退会手続きの前にご確認ください》</h1>

		<h3>退会されますと現在のログインIDは使用できなくなります。</h3>
		<h3>新規登録の際も使用できません。</h3>
	
		<h3>よろしいですか？</h3>

	<h3>
	
    <!--  	<a href="/rental_room/user/CancelUser.action">はい</a>
    	&nbsp;|&nbsp;
    	<a href="/rental_room/guest/top.jsp">いいえ</a>
    -->
    
    <!-- ボタン化 -->
    
    	<form action="/rental_room/user/CancelUser.action" method="post"> 
		<button type="submit">はい</button>
	</form>&nbsp;
    
       	
       	&nbsp;
    	<form action="/rental_room/guest/top.jsp" method="post"> 
		<button type="submit">いいえ</button>
	</form>
       	
    
       
	</h3>
 </main>

<jsp:include page="/common/footer.jsp"/>

</body>
</html>