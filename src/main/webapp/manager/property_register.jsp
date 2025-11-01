<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>物件登録</title>
<jsp:include page="/common/head.jsp" />
<style type="text/css">
	.address{
	   width: 25em;
    	max-width: 100%;
	}
</style>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

	<h1>物件登録</h1>
	※登録制限※<br>
		物件名：５０文字以内<br>
		賃料：１０００万円以内<br>
		物件説明：５００文字以内<br>
 	 	区以降住所：５０文字以内<br>
		画像名：１００文字以内<br>
		画像：jpeg(jpg)　２MB以内　2000px×2000px以内<br>
	<hr>　
	<form action="/rental_room/manager/PropertyRegisterConfirm.action" method="post" enctype="multipart/form-data">
		<p> 登録画像<input type="file" id="imageInput" name="image" accept="image/jpeg" required><br> </p>
		<p> 物件名<input type="text" name="name" minlength="1" maxlength="50" required><br> </p>
		<p> 賃料(共益費込)<input type="number" name="price" min="0" max="10000000" step="1000" required>円<br> </p>
		<p> 物件説明<br> <textarea name="info" rows="6" cols="50" maxlength="300" required></textarea> </p>
		<p>
    	間取り<br>
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
   		</p>
   		<p>
		ペット<br>
      <label><input type="radio" name="pet" value="可" required> 可</label>
      <label><input type="radio" name="pet" value="不可"> 不可</label><br>
   		</p>
   		<p>
		地区<br>
		<select name="city" required>
			<option value="">-- 選択してください --</option>
			<option value="中央区">中央区</option>
			<option value="花見川区">花見川区</option>
			<option value="稲毛区">稲毛区</option>
			<option value="若葉区">若葉区</option>
			<option value="緑区">緑区</option>
			<option value="美浜区">美浜区</option>
		</select>
   		</p>
   		<p> 区以降住所<br> <input class="address" type="text" name="address" maxlength="50" required> </p>
		<input type="submit" value="登録"> <input type="reset" value="リセット">
	</form>

	
<script>
  document.getElementById("imageInput").addEventListener("change", function () {
    const file = this.files[0];
    if (file && file.size > 2 * 1024 * 1024) { // 2MB 超え
      alert("画像サイズは2MB以内にしてください。");
      this.value = ""; // 選択をクリア
    }
  });
</script>


<jsp:include page="/common/footer.jsp"/>
</body>
</html>
