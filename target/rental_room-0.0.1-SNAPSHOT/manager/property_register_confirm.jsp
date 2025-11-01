<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<style>
.info {
    text-align: left;
    white-space: normal;   /* 自動折り返し */
    line-height: 1.6;      /* 行間 */
    max-width: 500px;      /* 最大幅制御 */
    word-break: break-word;     /* 長い単語でも折り返す */
    overflow-wrap: break-word;  /* 古いブラウザ互換 */
}
</style>
<title>物件登録確認</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

	<h1>物件登録確認</h1>

	<table border="1">
		<tr><th>物件名</th><td><c:out value="${property.name}"/></td></tr>
		<tr><th>賃料(共益費込)</th><td><fmt:formatNumber value="${property.price}" pattern="#,###" />円</td></tr>
		<tr><th>間取り</th><td><c:out value="${property.layout}"/></td></tr>
		<tr><th>ペット</th><td><c:out value="${property.pet}"/></td></tr>
		<tr><th>地区</th><td><c:out value="${property.city}"/></td></tr>
		<tr><th>区以降住所</th><td><c:out value="${property.address}"/></td></tr>
		<tr><th>物件詳細</th><td class="info"><c:out value="${property.info}"/></td></tr>
	</table>

	<p>
	登録画像 :<c:out value="${property.imageName}"/><br>
	<img src="${pageContext.request.contextPath}/images/${property.imageName}" width="200">
	</p>
	
	
	<p>この内容でよろしいですか</p>
	<form action="/rental_room/manager/PropertyRegister.action" method="post"  enctype="multipart/form-data">
		<!-- 隠しパラメータ -->
		<input type="hidden" name="name" 		value="<c:out value='${property.name}'/>">
		<input type="hidden" name="price" 		value="<c:out value='${property.price}'/>">
		<input type="hidden" name="info" 		value="<c:out value='${property.info}'/>">
    	<input type="hidden" name="layout" 		value="<c:out value='${property.layout}'/>">
		<input type="hidden" name="pet" 		value="<c:out value='${property.pet}'/>">
		<input type="hidden" name="city" 		value="<c:out value='${property.city}'/>">
		<input type="hidden" name="address" 	value="<c:out value='${property.address}'/>">
		<input type="hidden" name="image" accept="image/jpeg" 	value="<c:out value='${property.imageName}'/>">
		<input type="submit" value="はい">
	</form>

	<form action="/rental_room/manager/property_register.jsp" method="post" >
		<input type="submit" value="いいえ">
	</form>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>