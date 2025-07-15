<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content" style="color: white">
	<h1>Home Page 메인</h1>
</div>
		
<script>
	$(function() {
		var result = '<c:out value = "${result}" />';
		if (result === 'Logined') {
			alert("로그인에 성공했습니다.");
		}
	});
</script>

	</body>
</html>
