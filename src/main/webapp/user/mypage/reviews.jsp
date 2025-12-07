<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
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

		<h1>口コミ一覧</h1>

		<c:if test="${not empty reviews}">
			<table class="center-table">
				<tr>
					<th>物件名</th>
					<th>口コミ</th>
				</tr>
				<c:forEach var="review" items="${reviews}">
					<tr>
						<td style="text-align: left;" class="review"><c:out value="${review.propertyName}" /></td>
						<td style="text-align: left;" class="review"><c:out value="${review.comment}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${empty reviews}">
			<h2>まだ口コミはありません。</h2>
		</c:if>
		<br>

		<form action="/rental_room/user/mypage.jsp" method="post">
			<button type="submit">マイページへ</button>
		</form>

	</main>

	<%@include file="/common/footer.jsp"%>
</body>
</html>
