<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<title>김예지 (2025. 7. 2. 오전 9:10:10)</title>
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
        emp.jsp
  </xmp>
  
  <h3>아이디 중복검사</h3>
  
  <input id="empno" name="empno" value="7369">
  <button id="idCheckBtn" type="button">중복 확인</button>
  <div id="idCheckResult"></div>
  
</div>

<script>

$(function() {
	
	// 아이디 중복검사
	$("#idCheckBtn").on("click", function() {
		let vempno = $("#empno").val();
		const emp = { empno: vempno };
		
		idChk(emp, function(result) {
			$("#idCheckResult").text(result);
		});
		
	});
	
});

// 아이지 중복 검사 함수
function idChk(emp, callback, error){
	console.log("idChk()........");
	
	// empno 체크하는 ajax
	$.ajax({
		type: 'POST',
		url: `/emp/chkId`,
		data: JSON.stringify(emp),
		contentType: "application/json; charset=utf-8",
		cache: false,
		beforeSend: function(xhr){
			console.log("idChk.beforeSend ...............");
		},
		success: function(result, status, xhr){          
          if( callback ){
              callback( result );
          } // if
        },
        error: function (xhr, status, er){           
			if( error ){
            	error( er );
    		} // if
    	}
    })
    .fail(function() {
		alert( "ajax 아이디 조회 실패!!!" );
	});
	
} // remove
</script>

</body>
</html> 