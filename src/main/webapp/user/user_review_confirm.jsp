<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>口コミ登録内容の確認</title>
<jsp:include page="/common/head.jsp" />
<style>
.review {
    text-align: left;
    white-space: normal;   /* 自動折り返し */
    line-height: 1.6;      /* 行間 */
    max-width: 300px;      /* 最大幅制御 */
    word-break: break-word;     /* 長い単語でも折り返す */
    overflow-wrap: break-word;  /* 古いブラウザ互換 */
}
</style>
</head>

<body>
<jsp:include page="/common/header.jsp" />
<main class="main-content">	<!-- 装飾設定の読み込み -->

	<h1>口コミ登録内容の確認</h1>

	<!-- ★ 受け取ったパラメータを表示 -->
	<table class="center-table">
    	<tr>
        	<th>ニックネーム</th>
        	<td><c:out value="${param.nickName}"/></td>
    	</tr>
    	<tr>
        	<th>口コミ内容</th>
        	<td class="review"><c:out value="${param.review}"/></td>
	</table>

<h2>この内容で登録しますか？</h2>

<!-- 「はい」ボタンで登録実行 -->
<form action="UserReviewSignup.action" method="post">
    <input type="hidden" name="review" value="${param.review}">
    <input type="hidden" name="nickName" value="${param.nickName}">
    <input type="hidden" name="loginId" value="${sessionScope.account.id}">
    <input type="hidden" name="propertyId" value="${sessionScope.propertyId}">  <!-- ★これが必要 -->
    <input type="submit" value="はい"><br><br>
</form>


<!-- 「いいえ」ボタンで戻る -->
<form action="user_review.jsp" method="post">
    <input type="hidden" name="propertyId" value="${sessionScope.propertyId}">  
    <input type="hidden" name="review" value="${param.review}">  
    <input type="submit" value="いいえ">
</form>

</main>

<jsp:include page="/common/footer.jsp"/>
</body>
</html>
