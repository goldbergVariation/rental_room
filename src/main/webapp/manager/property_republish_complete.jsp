<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>物件掲載再開完了</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">

	<h1>物件掲載再開</h1>
	<p> 物件掲載の再開が完了しました。 </p>
	
	<!-- エヴァの時だけgifが動く -->
	<c:if test="${param.id=='101'}">
	
	<style>
    body {
        background-image: url('${pageContext.request.contextPath}/images/警戒2.jpg');
        background-size: cover;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-position: center;
        color: white;
    }
	</style>
	
	<div style="text-align:center">
	<p>エヴァ初号機発進！！</p>
	<img src="${pageContext.request.contextPath}/images/出撃.gif" alt="アニメーション" width="450" height="300">
	<input type="button" value="戻る" 
               onclick="location.href='${pageContext.request.contextPath}/guest/top.jsp'">
	</div>
	</c:if>
</main>

<!-- エヴァの時だけフッターを無くす -->
<c:if test="${param.id != '101'}">
	<jsp:include page="/common/footer.jsp"/>
</c:if>

</body>
</html>