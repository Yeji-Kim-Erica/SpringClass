<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<title>김예지 (2025. 7. 9. 오후 4:06:22)</title>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">yErica HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<div>
  <xmp class="code"> 
       /ajax/upload.jsp 
  </xmp>
  
  <form action="" method="post" enctype="multipart/form-data">
  	<div><input type="text" name="output" value="hello world!"></div>
  	<div>
  		<input type="file" name="attach" multiple="multiple">
  		<button type="button" id="btnAjaxUpload">ajax file upload</button>
  		
  		<hr>
		<div><input type="text" name="writer" value="admin"></div>
	    <div><input type="text" name="email" value="admin@naver.com"></div>
	    
	    <div><input type="submit"></div>
	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  	</div>
  </form>
  
</div>

<script>
	$(function() {
		$("#btnAjaxUpload").on("click", function() {
			// formData : HTML <form> 요소에 있는 입력값들을 키, 값 쌍으로 수집할 수 있는 내장 객체
			var formData = new FormData();
			var inputFile = $(":file[name='attach']");
			
			var files = inputFile[0].files;
			console.log(files);
			
			for (var i = 0; i < files.length; i++) {
				formData.append("attachList", files[i]);
			} // for
			
			$.ajax({
				url: 'uploadAjax' // /ajax/uploadAjax 요청 URL
				, processData: false
				, contentType: false
				, type: 'post'
				, data: formData
				, success: function(result) {
					alert("ajax file uploaded...");
				}
				, error: function(xhr, status, error) {
					alert('업로드 실패:', status, error);
				}
			}); // $.ajax({
			
		}); // $("#btnAjaxUpload").on("click", function() {
	}); // $(function() {
</script>

</body>
</html> 