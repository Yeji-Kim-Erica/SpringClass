<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<h3>
		<a href="/time">/time</a>
	</h3>
	
	<!-- 단순한 문자열 형태로 응답받기 -->
	<h3><a href="/sample/getText">Hello World : 문자열</a></h3>
	
	<!-- 자바 객체 -->
	<h3><a href="/sample/getSample">Hello World : 자바 객체</a></h3>

	<!-- 자바 컬렉션 -->
	<h3><a href="/sample/getList">Hello World : 자바 컬렉션</a></h3>

	<!-- 사원번호가 7369인 사원 정보를 XML, JSON 응답 -->
	<h3><a href="/scott/employee/7369">사원번호 7369 사원정보</a></h3>
	
	<!-- 사원번호(empno) 중복 체크 -->
	<h3><a href="/scott/idCheck/7369">사원번호 7369 중복 체크</a><br></h3>

	<!-- 모든 사원정보 조회 -->
	<h3><a href="/scott/emplist">모든 사원의 사원정보</a><br></h3>

</body>
</html>
