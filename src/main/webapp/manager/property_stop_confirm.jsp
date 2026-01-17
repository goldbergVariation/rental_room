<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>物件掲載停止確認</title>
<style type="text/css">
.info {
	text-align: left;
	white-space: normal; /* 自動折り返し */
	line-height: 1.6; /* 読みやすくするため行間調整（任意） */
	max-width: 500px;
}
.btn {
    display: inline-block;
    padding: 6px 12px;
    background: rgb(58, 115, 160);
    color: #fff;
    text-decoration: none;
    border-radius: 4px;
}
.btn:hover {
  background: rgb(10, 70, 128);
}
</style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">
	<h1>物件掲載停止の確認</h1>

	<img src="/rental_room/images/${property.imageName}" width="250" alt="not found">
	<table class="center-table">
		<tr>
			<th>物件名</th>
			<td><c:out value="${property.name}"/></td>
		</tr>
		<tr>
			<th>賃料(共益費込)</th>
			<td><fmt:formatNumber value="${property.price}" pattern="#,###" />円</td>
		</tr>
		<tr>
			<th>間取り</th>
			<td><c:out value="${property.layout}"/></td>
		</tr>
		<tr>
			<th>地区</th>
			<td><c:out value="${property.city}"/></td>
		</tr>
		<tr>
			<th>住所</th>
			<td><c:out value="${property.address}"/></td>
		</tr>
		<tr>
			<th>ペット</th>
			<td><c:out value="${property.pet}"/></td>
		</tr>
		<tr>
			<th>詳細</th>
			<td class="info" style="text-align:left;"><c:out value="${property.info}"/></td>
		</tr>
	</table>

	<h2>
		こちらの物件の掲載を停止します。<br>
		よろしいですか？
	</h2>
	
	<form action="/rental_room/manager/PropertyStop.action" method="post">
			<input type="hidden" name="id" value="${property.id}"> 
			<input type="submit" value="はい">
	</form>
	
    <br>
	<a href="javascript:history.back()" class="btn">いいえ(詳細画面に戻る)</a>
    <br>
    <br>

	<form action="/rental_room/guest/top.jsp" method="post">
			<input type="submit" value="検索画面に戻る">
	</form>
	
</main>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>