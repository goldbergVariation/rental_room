<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ削除確認</title>
<jsp:include page="/common/head.jsp" />
<style>
.review {
	text-align: left;
	white-space: normal; /* 自動折り返し */
	line-height: 1.6; /* 行間 */
	max-width: 350px; /* 最大幅制御 */
	word-break: break-word; /* 長い単語でも折り返す */
	overflow-wrap: break-word; /* 古いブラウザ互換 */
}
</style>
</head>

<body>
	<%@include file="/common/header.jsp"%>
	<main class="main-content">

		<h1>《 口コミ削除確認 》</h1>
		<h2>この口コミを削除しますか？</h2>

		<table class="center-table">
			<tr>
				<th>物件名</th>
				<th>口コミ</th>
				<th>投稿日時</th>
			</tr>
			<tr>
				<td style="text-align: left;" class="review"><c:out value="${param.propertyName}"/></td>
				<td style="text-align: left;" class="review"><c:out value="${param.comment}" /></td>
				<td style="text-align: left;"><c:out value="${param.createdAt}" /></td>
			</tr>
		</table>
		<br>
		
		<form action="/rental_room/user/mypage/ReviewDelete.action" method="post">
			<input type="hidden" name="reviewId" value="${param.reviewId}"> 	
			<input type="submit" value="削除">
		</form>
		<br>

		<form action="/rental_room/user/mypage/ReviewList.action" method="post">
			<input type="submit" value="口コミ一覧へ">
		</form>

	</main>
	<%@include file="/common/footer.jsp"%>
</body>
</html>
