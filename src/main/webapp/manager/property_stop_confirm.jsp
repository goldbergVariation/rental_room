<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>物件掲載停止確認</title>
<jsp:include page="/common/head.jsp" />
<style type="text/css">
.info {
	text-align: left;
	white-space: normal; /* 自動折り返し */
	line-height: 1.6; /* 読みやすくするため行間調整（任意） */
	max-width: 500px;
}
</style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

	<h1>物件掲載停止の確認</h1>

	<img src="/rental_room/images/${param.imageName}" width="250" alt="not found">
	<table border="1" style="border-collapse: collapse; text-align: center;">
		<tr>
			<th>物件名</th>
			<td><c:out value="${param.name}"/></td>
		</tr>
		<tr>
			<th>賃料(共益費込)</th>
			<td><fmt:formatNumber value="${param.price}" pattern="#,###" />円</td>
		</tr>
		<tr>
			<th>間取り</th>
			<td><c:out value="${param.layout}"/></td>
		</tr>
		<tr>
			<th>地区</th>
			<td><c:out value="${param.city}"/></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><c:out value="${param.address}"/></td>
		</tr>
		<tr>
			<th>ペット</th>
			<td><c:out value="${param.pet}"/></td>
		</tr>
		<tr>
			<th>詳細</th>
			<td class="info"><c:out value="${param.info}"/></td>
		</tr>
	</table>

	<h2>
		こちらの物件の掲載を停止します。<br>
		よろしいですか？
	</h2>
	
	<form action="/rental_room/manager/PropertyStop.action" method="post">
			<input type="hidden" name="id" value="${param.id}"> 
			<input type="submit" value="はい">
	</form>

	<form action="/rental_room/guest/top.jsp" method="post">
			<input type="submit" value="いいえ">
	</form>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>