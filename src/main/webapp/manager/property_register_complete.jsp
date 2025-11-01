<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>物件登録完了</title>
<jsp:include page="/common/head.jsp" />
</head>
<body>
<jsp:include page="/common/header.jsp"/>

	<p> 登録が完了しました。 </p>
	
	<form action="/rental_room/manager/property_register.jsp" method="post" >
		<input type="submit" value="物件登録">
	</form>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>