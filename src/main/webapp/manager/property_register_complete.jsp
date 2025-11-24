<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>物件登録完了</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>
<main class="main-content">

	<h2> 登録が完了しました。 </h2>
	
	<form action="/rental_room/manager/property_register.jsp" method="post" >
		<input type="submit" value="物件登録">
	</form>
</main>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>