<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>

<head>
<title><c:out value="${property.name}"/></title>
<jsp:include page="/common/head.jsp" />
<meta charset="UTF-8">
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
</head>

<body>
<jsp:include page="/common/header.jsp" />

	<% //管理者のみ物件掲載ステータスを変更可能  %>
	<c:if test="${not empty sessionScope.account and sessionScope.account.role == '管理者' and property.status != '掲載停止'}">
		<form action="/rental_room/manager/property_stop_confirm.jsp" method="post">
			<input type="hidden" name="id" value="${property.id}"> 
			<input type="hidden" name="name" value="${property.name}"> 
			<input type="hidden" name="imageName" value="${property.imageName}"> 
			<input type="hidden" name="layout" value="${property.layout}"> 
			<input type="hidden" name="price" value="${property.price}"> 
			<input type="hidden" name="info" value="${property.info}"> 
			<input type="hidden" name="pet" value="${property.pet}"> 
			<input type="hidden" name="city" value="${property.city}"> 
			<input type="hidden" name="address" value="${property.address}"> 
			<table border="1"><tr><td>物件の掲載を停止する</td><td><input type="submit" value="物件掲載停止"></td></tr></table>
		</form>
	</c:if>


	<h1><c:out value="${property.name}"/></h1>

	<img src="/rental_room/images/${property.imageName}" width="350" alt="not found">

	<table border="1" style="border-collapse: collapse; text-align: center;">
		<% //管理者のみ物件掲載ステータスを表示  %>
 		<c:if test="${not empty sessionScope.account and sessionScope.account.role == '管理者'}">
			<tr> <th>ステータス</th>
				<c:choose>
					<c:when test="${property.status == '掲載停止'}">
						<td style="color:red"><strong><c:out value="${property.status}"/></strong></td>
					</c:when>
					<c:otherwise>
						<td><c:out value="${property.status}"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
		</c:if>
		<tr> <th>賃料(共益費込)</th> <td><fmt:formatNumber value="${property.price}" pattern="#,###" />円</td> </tr>
		<tr> <th>間取り</th> <td><c:out value="${property.layout}"/></td> </tr> <tr>
		<tr> <th>住所</th> <td><c:out value="${property.city}${property.address}"/></td> </tr>
		<tr> <th>ペット</th> <td><c:out value="${property.pet}"/></td> </tr>
		<tr> <th>詳細</th> <td class="info"><c:out value="${property.info}"/></td> </tr>
	</table>

	<c:if test="${not empty reviews}">
		<h2>物件周辺情報 利用者口コミ</h2>
		<table border="1" style="border-collapse: collapse; text-align: center;">
			<c:forEach var="review" items="${reviews}" varStatus="st">
				<tr> <td style="text-align:left;">ニックネーム：　<c:out value="${nickNames[st.index]}"/></td> </tr>
				<tr> <td><c:out value="${review.comment}"/></td> </tr>
			</c:forEach>
		</table>
	</c:if>

<jsp:include page="/common/footer.jsp" />
</body>
</html>