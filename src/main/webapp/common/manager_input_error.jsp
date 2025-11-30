<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/css/manager_style.css' />">
<link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@500&display=swap" rel="stylesheet">
<title>入力エラー</title>
</head>
<body>

<main class="main-content">

  <h1>《入力内容の確認のお願い》</h1>

    <c:if test="${error_message == 'empty'}">
      <h3>未入力の項目があります。全ての項目を入力してください。</h3>
    </c:if>

    <c:if test="${error_message == 'duplicate'}">
      <h3>このログインIDは使えません。別のログインIDを使用してください。</h3>
    </c:if>

    <c:if test="${error_message == 'wrong'}">
      <h3>入力に誤りがあります。もう一度確認をお願いします。</h3>
    </c:if>
    
    <c:if test="${error_message == 'withdrawal'}">
      <h3>退会済みのアカウントです。新規登録をお願いします。</h3>
    </c:if>
    
    <c:if test="${error_message == 'commenterror'}">
      <h3>周辺地域の口コミが未入力です。入力をお願いします。</h3>
    </c:if>
    
    <c:if test="${error_message == 'review_duplicate'}">
      <h3>同じ物件には周辺地域の口コミは1つしか登録できません。</h3>
    </c:if>
    
  <a href="${forward_page}">${button}戻る</a>
</main>

</body>
</html>