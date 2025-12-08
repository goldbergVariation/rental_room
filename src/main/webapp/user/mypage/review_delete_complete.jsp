<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ削除完了</title>
<jsp:include page="/common/head.jsp" />
</head>

<body>
	<%@include file="/common/header.jsp"%>
	<main class="main-content">

		<h1>《 口コミ削除完了 》</h1>
		<h2>口コミを削除しました</h2>

		<br>

		<form action="/rental_room/user/mypage/ReviewList.action" method="post">
			<input type="submit" value="口コミ一覧へ">
		</form>

	</main>
	<%@include file="/common/footer.jsp"%>
</body>
</html>
