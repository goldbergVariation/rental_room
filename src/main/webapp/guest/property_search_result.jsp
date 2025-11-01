<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>物件検索結果</title>
<jsp:include page="/common/head.jsp" />
</head>
<body>
<jsp:include page="/common/header.jsp"/>


<h1><span style="font-family:'Shippori Mincho',serif;">物件検索結果</span></h1>

<c:choose>
	<c:when test="${empty properties_vacant and properties_vacant.size() == 0}">
		<h2>0件ヒットしました。</h2>
	</c:when>

	<c:when test="${properties_vacant.size() >= 0}">
	<h2><c:out value="${properties_vacant.size()}"/>件ヒットしました。</h2>
		<table border="1" style="border-collapse: collapse; text-align: center;">
		  <tr>
		    <th>物件名</th>
		    <th>画像</th>
		    <th>地区</th>
		    <th>間取り</th>
		    <th>賃料(共益費込)</th>
		    <th>ペット</th>
		  </tr>
		  <c:forEach var="property" items="${properties_vacant}">
		    <tr>
		      <td><a href="<c:url value='/guest/PropertyInfo.action'> <c:param name='id' value='${property.id}'/> </c:url>"> <c:out value="${property.name}"/></a> </td>
		      <td><a href="<c:url value='/guest/PropertyInfo.action'> <c:param name='id' value='${property.id}'/> </c:url>"> <img src="/rental_room/images/${property.imageName}" width="150" alt="not found"> </a> </td>
		      <td><c:out value="${property.city}"/> </td>
		      <td><c:out value="${property.layout}"/></td>
		      <td><fmt:formatNumber value="${property.price}" pattern="#,###" />円</td> <td><c:out value="${property.pet}"/> </td>
		    </tr>
		  </c:forEach>
		</table>
	</c:when>

	<c:when test="${empty properties_all and properties_all.size() == 0}">
		<h2>0件ヒットしました。</h2>
	</c:when>

	<c:when test="${properties_all.size() >= 0}">
	<h2><c:out value="${properties_all.size()}"/>件ヒットしました。</h2>
		<table border="1" style="border-collapse: collapse; text-align: center;">
		  <tr>
		    <th>物件名</th>
		    <th>画像</th>
		    <th>ステータス</th>
		    <th>地区</th>
		    <th>間取り</th>
		    <th>賃料(共益費込)</th>
		    <th>ペット</th>
		  </tr>
		  <c:forEach var="property" items="${properties_all}">
		    <tr>
		      <td><a href="<c:url value='/guest/PropertyInfo.action'> <c:param name='id' value='${property.id}'/> </c:url>"> <c:out value="${property.name}"/></a> </td>
		      <td><a href="<c:url value='/guest/PropertyInfo.action'> <c:param name='id' value='${property.id}'/> </c:url>"> <img src="/rental_room/images/${property.imageName}" width="150" alt="not found"> </a> </td>
			  <c:choose>
			  	<c:when test="${property.status == '掲載停止'}"> 
			  		<td style="color:red"><strong><c:out value="${property.status}"/></strong></td>
			  	</c:when>
			  	<c:otherwise> 
			  		<td><c:out value="${property.status}"/></td>
		        </c:otherwise>
		      </c:choose>
		      <td><c:out value="${property.city}"/> </td>
		      <td><c:out value="${property.layout}"/></td>
		      <td><fmt:formatNumber value="${property.price}" pattern="#,###" />円</td> <td><c:out value="${property.pet}"/> </td>
		    </tr>
		  </c:forEach>
		</table>
	</c:when>
</c:choose>


<a href="/rental_room/guest/top.jsp">物件検索へ戻る</a><br>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>