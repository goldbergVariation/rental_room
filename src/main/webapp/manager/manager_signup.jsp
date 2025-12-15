<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>管理者新規登録</title>
<jsp:include page="/common/head.jsp" />
<style type="text/css">
/* ------ 統一カラーパレット ------ */
:root{
  --c-card-bg:      #eeeeee;      /* ほんのり暖色のオフホワイト（背景と馴染みつつ埋もれない） */
  --c-field-bg:     #ffffff;      /* 入力欄のやわらかクリーム */
  --c-field-border: #f0ddc7;      /* 入力欄の枠：淡いベージュ */
  --c-text-main:    #5c5347;      /* 落ち着いたブラウン系テキスト */
  --c-accent:       #CCCCCC;     /* タイトル＆ボタンのサーモンオレンジ */
  --c-accent-dark:  #BBBBBB;      /* ボタン影など濃いめサーモン */
  --c-shadow:       rgba(188, 204, 178, 0.35); /* 背景の黄緑となじむ、少しグリーン寄りの影 */
}

*{
  margin: 0;
  padding: 0;
}

/* 入力欄 */
input{
  display: block;
  width: 100%;
  font-size: 1rem;
  color: var(--c-text-main);
  background-color: var(--c-field-bg);
  border: 1px solid var(--c-field-border);
  border-radius: 6px;
  box-sizing: border-box;
  padding: 10px;
  margin-top: 5px;
  margin-bottom: 25px;
}

input:focus{
  outline: none;
  border-color: var(--c-accent);
  box-shadow: 0 0 0 3px rgba(242, 155, 114, 0.25);
}

/* ボタン（登録・リセット） */
input[type="submit"],
input[type="reset"]{
  display: inline-block;
  width: 150px;
  text-align: center;
  color: #111111;
  background-color: var(--c-accent);
  border: none;
  border-radius: 999px;
  box-shadow: 0 5px 0 var(--c-accent-dark);
  cursor: pointer;
  margin-right: 16px;
  padding: 10px 0;
}

input[type="submit"]:hover,
input[type="reset"]:hover{
  background-color: #AAAAAA;
  box-shadow: 0 3px 0 var(--c-accent-dark);
  transform: translateY(1px);
}

/* フォーム全体（カード） */
.form{
  width: 500px;
  margin: 40px auto;
  background-color: var(--c-card-bg);          /* 背景より少しだけ温かいオフホワイト */
  border: 1px solid #f1e4cf;                   /* うっすらベージュの枠 */
  box-shadow: 0 12px 24px var(--c-shadow);     /* 少しグリーン寄りの影で背景となじませる */
  border-radius: 16px;
}

/* タイトルバー */
.form_title{
  color: #111111;
  background-color: var(--c-accent);           /* ボタンと同じ色で統一 */
  padding: 14px 30px;
  font-size: 1.2rem;
  border-radius: 16px 16px 0 0;
}

/* 中身の余白だけ確保（背景はカードと同じトーンに） */
.form_content{
  margin: 25px 50px;
  border-radius: 0 0 16px 16px;
}

.form_list{
  margin-bottom: 2em;
}

</style>
</head>

<body>
	<%@include file="/common/header.jsp"%>

	<div class="form">
		<div class="form_title">管理者新規登録</div>
		<div class="form_content">
			<form id="registerForm" action="/rental_room/manager/ManagerCheck.action" method="post">
				<div class="form_list">
				<label>ログインID </label> <input type="text" class="input" name="loginId" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" placeholder="4～20文字、半角英数字のみ、記号不可" required> 
				<label>ニックネーム</label>
				<input type="text" class="input" name="nickName" minlength="1" maxlength="20" placeholder="1～20文字" required> 
				<label>メールアドレス</label>
				<input type="email" class="input" id="email" name="email" minlength="4" maxlength="50" required> 
				<label>パスワード</label>
				<input type="password" class="input" id="password" name="password" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" placeholder="4～20文字、半角英数字のみ、記号不可" required> 
				<label>パスワード（確認用）</label>
				<input type="password" class="input" id="confirm_password" name="confirm_password" minlength="4" maxlength="20" pattern="[a-zA-Z0-9]+" placeholder="もう一度入力" required>

				<p id="passwordMessage" style="color: red;"></p>
				<input type="submit" value="登録"> <input type="reset" value="リセット">
				</div>
			</form>
		</div>
	</div>

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


	<jsp:include page="/common/footer.jsp" />
</body>
</html>
