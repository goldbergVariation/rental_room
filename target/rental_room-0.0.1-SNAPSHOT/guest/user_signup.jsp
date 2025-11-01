<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="jakarta.tags.core" %>
<html>
<head>
<jsp:include page="/common/head.jsp" />
</head>

<body>
<jsp:include page="/common/header.jsp"/>

<form id="registerForm" action="/rental_room/guest/UserCheck.action" method="post">
    <p>ログインID<br>
        <input type="text" name="loginid" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" required>
        <br>※4～20文字、半角英数字のみ、記号不可
    </p>

    <p>ニックネーム<br>
        <input type="text" name="nickname" minlength="1" maxlength="20" required>
        <br>※1～20文字
    </p>

    <p>パスワード<br>
        <input type="password" id="password" name="password" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" required>
        <br>※4～20文字、半角英数字のみ、記号不可
    </p>

    <p>パスワード（確認用）<br>
        <input type="password" id="confirm_password" name="confirm_password" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" required>
        ※4～20文字、半角英数字のみ、記号不可
    </p>

    <p id="passwordMessage" style="color:red;"></p>

    <p><input type="submit" value="登録"></p>
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

<jsp:include page="/common/footer.jsp"/>
</body>
</html>