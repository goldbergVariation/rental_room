<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
<jsp:include page="/common/head.jsp" />
</head>

<body>
	<%@include file="/common/header.jsp"%>
	<main class="main-content">

		<h1>《 マイページ 》</h1>

		<table class="center-table">
			<tr>
				<th>ログインID</th>
				<td><c:out value="${account.loginId}" /></td>
			</tr>
			<tr>
				<th>ニックネーム</th>
				<td><c:out value="${account.nickName}" /></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<c:choose>
					<c:when test="${account.email != null and not empty account.email}">
						<td><c:out value="${account.email}" /></td>
					</c:when>
					<c:otherwise>
						<td>未設定</td>
					</c:otherwise>
				</c:choose>
			</tr>
		</table>
		<br> 

		<form action="/rental_room/user/mypage/ReviewList.action" method="post">
			<button type="submit">口コミ一覧</button> 
		</form>
		<br>
		<form action="/rental_room/user/member_cancel_confirm.jsp" method="post">
			<button type="submit">退会処理</button> 
		</form>
		
		<%// <h3>Eメール変更</h3>%>
		<%//<h3>パスワード変更 </h3> %>

	</main>
	<%@include file="/common/footer.jsp"%>
</body>
</html>
