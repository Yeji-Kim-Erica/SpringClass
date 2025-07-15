<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
	<h2>로그인</h2>
	<h3 class="hidden">방문페이지 로그</h3>
	<ul id="breadscrumb" class="block_hlist clear">
		<li>HOME</li>
		<li>회원가입</li>
		<li>로그인</li>
	</ul>
	<h3 class="hidden">회원가입 폼</h3>
	<div id="join-form" class="join-form margin-large">
		
		<c:if test="${param.error eq true}">
			<div>
	           <strong style="color:red">아이디 또는 패스워드가 일치하지 않습니다.</strong><br />
	           <c:if test="${ SPRING_SECURITY_LAST_EXCEPTION  != null}">
	              Message : <c:out   value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
	           </c:if>
	        </div>
		</c:if>
	
		<%-- <form action="${pageContext.request.contextPath}/joinus/login.htm" method="POST"> --%>
		<form action="/login" method="POST">
			<fieldset>
				<legend class="hidden">로그인 폼</legend>
				<h3>
					<img src="${pageContext.request.contextPath}/joinus/images/txtTitle.png" />
				</h3>
				<ul id="loginBox">
<!-- 				<li><label for="uid">아이디</label><input name="id" class="text" /></li> -->
<!-- 				<li><label for="pwd">비밀번호</label><input type="password" name="pwd" class="text" /></li> -->
					<li><label for="uid">아이디</label><input name="username" class="text" id="id" /></li>
					<li><label for="pwd">비밀번호</label><input type="password" name="password" class="text" id="pwd" /></li>
				</ul>
				<p>
					<input type="submit" id="btnLogin" value="" />
				</p>
				<ul id="loginOption">
					<li><span>아이디 또는 비밀번호를 분실하셨나요?</span><a href="/Member/FindID"><img
							alt="ID/PWD 찾기" src="${pageContext.request.contextPath}/joinus/images/btnFind.png" /></a></li>
					<li><span>아이디가 없으신 분은 회원가입을 해주세요.</span><a
						href="/Member/Agree"><img alt="회원가입" src="${pageContext.request.contextPath}/joinus/images/btnJoin.png" /></a></li>
				</ul>
			</fieldset>
			
			<!-- POST 방식 요청에 의해 필요 -->
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		</form>
	</div>
</div>

<script>
	$(function() {
		var result = '<c:out value="${result}" />';
		switch (result) {
		case 'Registered':
			alert("회원가입이 성공적으로 완료되었습니다.");
			break;

		case 'Failed':
			alert("로그인에 실패했습니다.");
			break;

		default:
			break;
		}
	});
</script>

</body>
</html>
