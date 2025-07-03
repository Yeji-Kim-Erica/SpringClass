<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${user}
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3>
	<a href="/time">/time</a>
</h3>
<h3>
	<a href="/scott/dept">부서 정보 조회</a>
</h3>

<!-- 

	1. <a href="/time">/time</a> 요청
	2. org.doit.ik.mapper.TimeMapper 인터페이스
	3. 사용할 DB: ORM(mybatis)
		(1) pom.xml
			 - https://mvnrepository.com/artifact/org.mybatis/mybatis p91
			      <dependency>
			          <groupId>org.mybatis</groupId>
			          <artifactId>mybatis</artifactId>
			          <version>3.4.6</version>
			      </dependency>
		     - https://mvnrepository.com/artifact/org.mybatis/mybatis-spring
			      <dependency>
			          <groupId>org.mybatis</groupId>
			          <artifactId>mybatis-spring</artifactId>
			          <version>1.3.2</version>
			      </dependency>

		(2) org.doit.ik.mapper 패키지 안에 mybatis-config.xml 생성
		
		(3) src/main/resources 폴더
			ㄴ org 폴더 생성
				ㄴ doit 폴더 생성
					ㄴ ik 폴더 생성
						ㄴ mapper 폴더 생성
							ㄴ TimeMapper.xml 추가 (인터페이스명(예: TimeMapper.java)과 동일하게 만들면 관리가 용이함)
								ㄴ 해당 Mapper 파일로 인해 Implementation이 가능해짐
		
		(4) log4jdbc.log4j2.properties 파일 추가, log4j.xml 파일 수정
		
		(5) root-context.xml 파일 수정
		
		(6) ojdbc6.jar 설정
	
	4. views 폴더
		ㄴ time.jsp 추가
	
	5. 
-->

</body>
</html>
