<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/common/head.jsp" />
<title>物件登録</title>
<style type="text/css">
/* 画面中央、読みやすい幅 */
.container {
  max-width: 720px;
  margin: 0 auto;
  padding: 20px 0;
  text-align: left;
}

/* 見出しは抑えめのアクセント */
h1, h2 {
  text-align: center;
  font-weight: 600;
}

.note-box {
  color:black;
  max-width: 360px;    /* ← 好きな幅にする */
  margin: 0 auto 10px;
  background: #ffffffdd;
  border-left: 4px solid #88a298;
  padding: 4px 15px 12px;
  border-radius: 8px;
  font-size:0.8em;
}
.form-box {
  max-width: 380px;    /* ← noteより少し広げてもOK */
  margin: 0 auto;
  background: #ffffff;
  padding: 20px 24px;
  border-radius: 10px;
  border: 1px solid #cfd6d1;
  box-shadow: 0 2px 6px rgba(0,0,0,0.06);
}
/* h2の余白微調整 */
.form-box h2 {
  margin-top: 0;
  margin-bottom: 16px;
  font-size: 1.3em;
  color: #52695e;
}

input[type="text"],
input[type="number"],
textarea,
select {
  width: 100%;          /* 枠の中で完結 */
  max-width: 340px;     /* ← 少し細めに制限 */
}

textarea {
  resize: vertical;
}

.bold {
font-weight: bold;
}
</style>
</head>
<body>
<jsp:include page="/common/header.jsp" />

	<h1>《 物件登録 》</h1>
	<section class="note-box">
	<h2>※登録制限※</h2>
		<ul>
			<li>物件名：50文字以内</li>
			<li>賃料：1000万円以内</li>
			<li>物件説明：500文字以内</li>
			<li>以降住所：50文字以内</li>
			<li>画像名：100文字以内</li>
			<li>画像：<span style="color:red;inline;">jpeg(jpg)</span> / 2MB以内 / 2000px×2000px以内</li>
		</ul>
	</section>

	<div class="container">
		<!-- フォームエリア -->
		<section class="form-box">
			<h2>物件情報を入力してください</h2>

			<form action="/rental_room/manager/PropertyRegisterConfirm.action" method="post" enctype="multipart/form-data">
				<div class="bold">
				<p> 登録画像<br><input type="file" id="imageInput" name="image" accept="image/jpeg" required><br> </p>
				<p> 物件名<input type="text" name="name" minlength="1" maxlength="50" required><br> </p>
				<p> 賃料(共益費込)<br><input style="width:100px" type="number" name="price" min="0" max="10000000" required>円<br> </p>
				<p> 物件説明<br> <textarea name="info" rows="10" cols="50" maxlength="500" required></textarea> </p>
				</div>	
				<div class="bold"> 間取り</div>
					<label><input type="radio" name="layout" value="1R" required> 1R</label> 
					<label><input type="radio" name="layout" value="1K"> 1K</label> 
					<label><input type="radio" name="layout" value="1DK"> 1DK</label> 
					<label><input type="radio" name="layout" value="1LDK"> 1LDK</label><br> 
					<label><input type="radio" name="layout" value="2R"> 2R</label> 
					<label><input type="radio" name="layout" value="2K"> 2K</label> 
					<label><input type="radio" name="layout" value="2DK"> 2DK</label> 
					<label><input type="radio" name="layout" value="2LDK"> 2LDK</label><br> 
					<label><input type="radio" name="layout" value="3R"> 3R</label> 
					<label><input type="radio" name="layout" value="3K"> 3K</label> 
					<label><input type="radio" name="layout" value="3DK"> 3DK</label> 
					<label><input type="radio" name="layout" value="3LDK"> 3LDK</label><br> 
					<label><input type="radio" name="layout" value="4R以上"> 4R以上</label><br>
				<div class="bold">
				<p>
					ペット<br> <label><input type="radio" name="pet" value="可" required> 可</label> <label><input type="radio" name="pet" value="不可"> 不可</label><br>
				</p>
				<p>
					地区<br> <select name="city" required>
						<option value="">-- 選択してください --</option>
						<option value="中央区">中央区</option>
						<option value="花見川区">花見川区</option>
						<option value="稲毛区">稲毛区</option>
						<option value="若葉区">若葉区</option>
						<option value="緑区">緑区</option>
						<option value="美浜区">美浜区</option>
					</select>
				</p>
				<p>
					以降住所<br> <input class="address" type="text" name="address" maxlength="50" required>
				</p>
				</div>
				<input type="submit" value="登録"> <input type="reset" value="リセット">
			</form>
		</section>
	</div>
	<br>

	<script>
		document.getElementById("imageInput").addEventListener("change", function() {
			const file = this.files[0];
			// 2MB 超え
			if (file && file.size > 2 * 1024 * 1024) { 
					alert("画像サイズは2MB以内にしてください。");
					this.value = ""; // 選択をクリア
			}
		});
	</script>

	<jsp:include page="/common/footer.jsp" />
</body>
</html>
