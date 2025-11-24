<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>周辺地域の口コミ登録</title>
<jsp:include page="/common/head.jsp" />

</head>

<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">

<h1>★ 周辺地域の口コミ登録 ★</h1>

<!-- ★口コミ投稿フォーム -->
<c:if test="${not empty sessionScope.account}">
    <form action="UserReviewCheck.action" method="post">
    
    	<input type="hidden" name="userId" value="${sessionScope.account.id}"> 
    	<input type="hidden" name="nickName" value="${sessionScope.account.nickName}">
    	
        <!-- propertyIdだけ送信、ユーザー情報はセッションから取得 -->
        <input type="hidden" name="propertyId" value="${param.propertyId}">

        <h3><label for="review">周辺地域の口コミ：</label></h3>
        
        <textarea name="review" id="review" cols="50" rows="6" maxlength="300" required>${param.review}</textarea><br>
        
        ※1～300文字<br>
        ※1物件につき、利用者様が登録できるのは1件までです<br><br>

        <button type="submit">登録</button><br><br>
    </form>

	<form action="/rental_room/guest/PropertyInfo.action?id=${param.propertyId}" method="post"> 
		<button type="submit">物件詳細へ戻る</button>
	</form>
</c:if>

</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>

