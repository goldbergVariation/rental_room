<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title><c:out value="${property.name}"/></title>
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
.review {
    text-align: left;
    white-space: normal;   /* 自動折り返し */
    line-height: 1.6;      /* 行間 */
    width: 300px;      /* 最大幅制御 */
    word-break: break-word;     /* 長い単語でも折り返す */
    overflow-wrap: break-word;  /* 古いブラウザ互換 */
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
<jsp:include page="/common/header.jsp" />
<main class="main-content">
	<h1><c:out value="${property.name}"/></h1>

	<% //管理者のみ物件掲載ステータスを変更可能  %>
	<!--掲載停止に変更 -->
	<c:if test="${not empty sessionScope.account and sessionScope.account.role == '管理者' and property.status == '空室'}">
		<form action="/rental_room/manager/property_stop_confirm.jsp" method="post">
			<input type="hidden" name="id" value="<c:out value='${property.id}'/>">
			<input type="hidden" name="name" value="<c:out value='${property.name}'/>"> 
			<input type="hidden" name="imageName" value="<c:out value='${property.imageName}'/>"> 
			<input type="hidden" name="layout" value="<c:out value='${property.layout}'/>"> 
			<input type="hidden" name="price" value="<c:out value='${property.price}'/>"> 
			<input type="hidden" name="info" value="<c:out value='${property.info}'/>"> 
			<input type="hidden" name="pet" value="<c:out value='${property.pet}'/>"> 
			<input type="hidden" name="city" value="<c:out value='${property.city}'/>"> 
			<input type="hidden" name="address" value="<c:out value='${property.address}'/>"> 
			<table  class="center-table"><tr><td style="width:150px"><h2>この物件の掲載を停止する</h2></td><td style="width:80px"><input type="submit" value="物件掲載停止"></td></tr></table>
		</form>
	</c:if>

	<!--再掲載に変更 -->
	<c:if test="${not empty sessionScope.account and sessionScope.account.role == '管理者' and property.status == '掲載停止'}">
		<form action="/rental_room/manager/property_republish_confirm.jsp" method="post">
			<input type="hidden" name="id" value="<c:out value='${property.id}'/>">
			<input type="hidden" name="name" value="<c:out value='${property.name}'/>"> 
			<input type="hidden" name="imageName" value="<c:out value='${property.imageName}'/>"> 
			<input type="hidden" name="layout" value="<c:out value='${property.layout}'/>"> 
			<input type="hidden" name="price" value="<c:out value='${property.price}'/>"> 
			<input type="hidden" name="info" value="<c:out value='${property.info}'/>"> 
			<input type="hidden" name="pet" value="<c:out value='${property.pet}'/>"> 
			<input type="hidden" name="city" value="<c:out value='${property.city}'/>"> 
			<input type="hidden" name="address" value="<c:out value='${property.address}'/>"> 
			<table  class="center-table"><tr><td style="width:150px"><h2>この物件の掲載を再開する</h2></td><td style="width:80px"><input type="submit" value="物件掲載再開"></td></tr></table>
		</form>
	</c:if>

	<br>
	<img src="/rental_room/images/${property.imageName}" width="350" alt="not found">

	<table  class="center-table">
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
		<tr> <th>住所</th>  <td class="info"><c:out value="${property.city}${property.address}"/></td> </tr>
		<tr> <th>ペット</th> <td><c:out value="${property.pet}"/></td> </tr>
		<tr> <th>詳細</th> <td class="info"style="text-align:left;"><c:out value="${property.info}"/></td> </tr>
	</table>
<br>

	<%--ここから口コミ登録用追記 --%>

    <!-- 利用者 -->
	<c:if test="${not empty sessionScope.account and sessionScope.account.role == '利用者'}">
    	<form action="/rental_room/user/user_review.jsp" method="get">
        	<input type="hidden" name="propertyId" value="${property.id}">
        	<button type="submit">周辺地域の口コミ登録</button>
    	</form>
	</c:if>

		
	<%--口コミ登録ボタンの追記終わり --%>	

	<c:if test="${not empty reviews}">
		<h2>物件周辺情報 利用者口コミ</h2>
		
		<table class="center-table">
			<c:forEach var="review" items="${reviews}" varStatus="st">
				<tr> <td style="text-align:left;">ニックネーム：　<c:out value="${nickNames[st.index]}"/></td> </tr>
				<tr> <td class="review" style="text-align:left;"><c:out value="${review.comment}"/></td> </tr>
			</c:forEach>
		</table>
	</c:if>
	<br>
	<a href="/rental_room/guest/top.jsp" class="btn">検索へ戻る</a>
    <br>
</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>