<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ登録完了</title>

<jsp:include page="/common/head.jsp" />  <!-- ✅ CSSなど共通設定を読み込む -->

</head>

<body>    
<jsp:include page="/common/header.jsp"/>
<main class="main-content">	<!-- 装飾設定の読み込み -->
    
		<h2>♪ 口コミ登録が完了しました ♪</h2>
		
		<form action="/rental_room/guest/top.jsp" method="post">
    	<input type="submit" value="物件検索へ戻る">
    	</form>

</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>