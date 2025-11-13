<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>利用者登録の退会確認</title>

<jsp:include page="/common/head.jsp" />  <!-- ✅ CSSなど共通設定を読み込む -->
</head>

<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">	<!-- 装飾設定の読み込み -->

	<h1>≪ 利用者登録の退会確認 ≫</h1>

	<h2>利用者退会手続きを行います。</h2>

	<h2>よろしいですか？</h2>

	<h2>
	
	<!--  
		<a href="/rental_room/user/member_cancel_reconfirm.jsp">はい</a>-->
    	<!--  <input type="submit" value="はい"> -->
    <!--	&nbsp;|&nbsp;
    	<a href="/rental_room/guest/top.jsp">いいえ</a>-->
    	<!--  <input type="submit" value="いいえ"> -->
	</h2>
	
	    	<form action="/rental_room/user/member_cancel_reconfirm.jsp" method="post"> 
		<button type="submit">はい</button>&nbsp;
	</form>
    
       	
       	&nbsp;
    	<form action="/rental_room/guest/top.jsp" method="post"> 
		<button type="submit">いいえ</button>
	</form>
	
	

</main>
<jsp:include page="/common/footer.jsp" />
</body>
</html>
