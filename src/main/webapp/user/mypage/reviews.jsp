<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ一覧</title>
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
					<th>物件ID</th>
					<th>物件名</th>
					<th>口コミ</th>
					<th>投稿日時</th>
					<th>削除</th>
				</tr>
				<c:forEach var="review" items="${reviews}">
					<tr>
						<td style="text-align: left;" class="review">
							<fmt:formatNumber value="${review.id}" pattern="00000" var="reviewId"/>	
							<c:out value="${reviewId}" />
						</td>
						<td style="text-align: left;" class="review"><c:out value="${review.propertyName}" /></td>
						<td style="text-align: left;" class="review"><c:out value="${review.comment}" /></td>
						<td style="text-align: left;"><c:out value="${review.createdAt}" /></td>
						<td>
							<form action="/rental_room/user/mypage/review_delete_confirm.jsp" method="post">
								<input type="hidden" name="reviewId" value="<c:out value='${review.id}'/>">
								<input type="hidden" name="comment" value="<c:out value='${review.comment}'/>">
								<input type="hidden" name="propertyName" value="<c:out value='${review.propertyName}'/>">
								<input type="hidden" name="propertyId" value="<c:out value='${review.propertyId}'/>">
								<input type="hidden" name="createdAt" value="<c:out value='${review.createdAt}'/>">
								<input type="submit" value="削除">
							</form>
						</td>
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
