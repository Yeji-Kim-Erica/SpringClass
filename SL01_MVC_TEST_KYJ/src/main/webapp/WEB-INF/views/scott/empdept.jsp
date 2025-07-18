<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<title>EMP (2025. 6. 30. 오후 4:09:01)</title>
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
        WEB-INF/views/scott/emp.jsp
  </xmp>
  
  <form action="/scott/empdept" method="get">
  <select id="deptno" name="deptno">
  	<option value="">-- 전체 부서 --</option>
	<c:forEach var="dept" items="${deptList}">
      <option value="${dept.deptno}" <c:if test="${param.deptno == dept.deptno}">selected</c:if>>
      	${dept.dname} (${dept.deptno})
      </option>
    </c:forEach>
  </select>
  </form>
  
	  <table id="tbl-emp">
	  <caption></caption>
	  	<thead>
	  		<tr>
	  			<td></td>
	  			<td>EmpNo</td>
	  			<td>EName</td>
	  			<td>Job</td>
	  			<td>Mgr</td>
	  			<td>Hiredate</td>
	  			<td>Sal</td>
	  			<td>Comm</td>
	  			<td>DeptNo</td>
	  		</tr>
	  	</thead>
	  	<tbody>
            <c:if test="${empty empList}">
                <tr>
                    <td colspan="9">조회된 사원이 없습니다.</td>
                </tr>
            </c:if>
	  		<c:forEach items="${empList}" var="dto">
	  			<tr>
		  			<td><input type="checkbox" value="${ dto.empno }" name="empno"></td>
		  			<td>${dto.empno}</td>
		  			<td>${dto.ename}</td>
		  			<td>${dto.job}</td>
		  			<td>${dto.mgr}</td>
		  			<td>${dto.hiredate}</td>
		  			<td>${dto.sal}</td>
		  			<td>${dto.comm}</td>
		  			<td>${dto.deptno}</td>
	  			</tr>
	  		</c:forEach>
	  	</tbody>
	  	<tfoot>
	      <tr>
	        <td colspan="9">
	          <button id="home" class="home">home</button>
	        </td>
	      </tr>
	  	</tfoot>
	  </table>
  
</div>

<script>
	$(function() {
		$("#deptno").on("change", function() {
			$("form").submit();
		});
	}); // document.ready() 닫기

</script>

</body>
</html> 