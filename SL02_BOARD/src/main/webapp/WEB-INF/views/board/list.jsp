<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<title>LIST (2025. 7. 1. 오후 3:42:43)</title>
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
        /board/list.jsp
  </xmp>
  
  <table>
    <caption style="text-align: right;">
      <a href="/board/register">글쓰기</a>
    </caption>
    <thead>
       <tr>
         <th>#번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>수정일</th>        
       </tr>
    </thead>
    <tbody>
       <c:choose>
         <c:when test="${ empty board }">
           <tr>
             <td colspan="5">no board...</td>
           </tr>
         </c:when>
         <c:otherwise>
           <c:forEach items="${ board }" var="board">
             <tr>
               <td><c:out value="${board.bno}" /></td>
               <td><a href="/board/get?bno=${board.bno}"><c:out value="${board.title}" /></a></td>
               <%-- <td><a href="/board/get/${board.bno}"><c:out value="${board.title}" /></a></td> --%>
               <td><c:out value="${board.writer}" /></td>
               <td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd" /></td>
               <td><fmt:formatDate value="${ board.updatedate }" pattern="yyyy-MM-dd" /></td>
             </tr>
           </c:forEach>
         </c:otherwise>
       </c:choose>
    </tbody>
  </table>
  
</div>

<script>

	$(function() {
		var result = '<c:out value="${result}" />';
		var remove = '<c:out value="${remove}" />';
		// alert(result+"번 글 등록되었습니다.");
		checkModal(result);
		
		function checkModal(result) {
			if (result === "" & remove === "" || history.state) return;
			if (parseInt(result) > 0) {
				alert(`\${result}번 글이 등록되었습니다.`);
				return;
			} // if
			if (parseInt(remove)) {
				alert(`\${remove}번 글이 삭제되었습니다.`);
				return;
			}
		} // function checkModal(result) {
		
	}); // $(function() {

</script>

</body>
</html> 