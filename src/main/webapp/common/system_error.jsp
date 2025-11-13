<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>システムエラー</title>
</head>
<body>

<jsp:include page="/common/header.jsp"/>
<main class="main-content">

<h1>《システムエラー》</h1>

<c:choose>
    <c:when test="${empty error_message}">
    	<h3>システムエラーが発生しました。再度お試しください。</h3>
	  	<a href="/rental_room/guest/top.jsp">トップページへ戻る</a>
    </c:when>
    <c:otherwise>
		<c:if test="${error_message == 'empty_loginid'}">
		  <h3>システムエラーが発生しました。お手数ですが、ログインから操作をお願いいたします。</h3>
		</c:if>
		
		<c:if test="${error_message == 'empty_propertyid'}">
		  <h3>システムエラーが発生しました。お手数ですが、物件検索から操作をお願いいたします。</h3>
		</c:if>
		
		<c:if test="${error_message == 'empty_comment'}">
		  <h3>システムエラーが発生しました。お手数ですが、周辺地域の口コミ登録から操作をお願いいたします。</h3>
		</c:if>
	   
	  	<a href="${forward_page}">${button}戻る</a>
    </c:otherwise>
</c:choose>

</main>
<jsp:include page="/common/footer.jsp"/>

</body>
</html>