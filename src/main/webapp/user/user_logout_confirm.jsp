<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ログアウト確認</title>
	<jsp:include page="/common/head.jsp" />	<!-- ✅ CSSなど共通設定を読み込む -->
</head>

<body>
<%@include file="/common/header.jsp" %>
	<main class="main-content">	<!-- 装飾設定の読み込み -->
    
   	 <h1>《ログアウト確認 》</h1> 
    	<h2>ログアウトしますか？</h2>
    	<form action="UserLogout.action" method="post">
    	<input type="submit" value="はい">
       </form>
       <br>
    	<form action="/rental_room/guest/top.jsp" method="post">
    	<input type="submit" value="いいえ">
       </form>
	 </main>
    
<%@include file="/common/footer.jsp"%>
</body>
</html>
    