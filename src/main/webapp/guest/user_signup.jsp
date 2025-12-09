<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>利用者新規登録</title>
<jsp:include page="/common/head.jsp" />
<style type="text/css">
	.input{
		width:250px;	
	}
</style>
</head>

<body>
<%@include file="/common/header.jsp" %>
<main class="main-content">

	<h2>《 利用者新規登録 》</h2>
	<form id="registerForm" action="/rental_room/guest/UserCheck.action" method="post">
    <p>ログインID<br>
        <input type="text" class="input" name="loginid" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" placeholder="4～20文字、半角英数字のみ、記号不可" required>
    </p>

    <p>ニックネーム<br>
        <input type="text" class="input" name="nickname" minlength="1" maxlength="20" placeholder="1～20文字" required>
    </p>

    <p>メールアドレス<br>
        <input type="email" class="input" id="email" name="email" minlength="4" maxlength="50" required>
    </p>

    <p>パスワード<br>
        <input type="password" class="input" id="password" name="password" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" placeholder="4～20文字、半角英数字のみ、記号不可" required> 
    </p>

    <p>パスワード（確認用）<br>
        <input type="password" class="input" id="confirm_password" name="confirm_password" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+"  placeholder="もう一度入力" required>
    </p>

    <p id="passwordMessage" style="color:red;"></p>

    <p><input type="submit" value="登録">  <input type="reset" value="リセット"></p>
</form>

<script>
    const form = document.getElementById('registerForm');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirm_password');
    const message = document.getElementById('passwordMessage');

    // フォーム送信前のチェック
    form.addEventListener('submit', function(event) {
        if (password.value !== confirmPassword.value) {
            event.preventDefault(); // フォーム送信を止める
            message.textContent = "パスワードが一致しません。登録できません。";
            confirmPassword.focus(); // 2回目入力欄にフォーカス
        } else {
            message.textContent = "";
        }
    });
   
    // 2回目のパスワードを打っている最中に、1回目と一致しているかをチェック
    confirmPassword.addEventListener('input', function() {
        if (password.value !== confirmPassword.value) {
            message.textContent = "パスワードが一致しません。";
        } else {
            message.textContent = "";
        }
    });
</script>

</main>

<jsp:include page="/common/footer.jsp" />
</body>
</html>