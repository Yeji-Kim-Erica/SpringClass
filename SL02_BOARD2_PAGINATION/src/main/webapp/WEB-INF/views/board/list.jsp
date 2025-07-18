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
               <td><a class="move" href="${board.bno}"><c:out value="${board.title}" /></a></td>
               <%-- <td><a href="/board/get/${board.bno}"><c:out value="${board.title}" /></a></td> --%>
               <td><c:out value="${board.writer}" /></td>
               <td><fmt:formatDate value="${ board.regdate }" pattern="yyyy-MM-dd" /></td>
               <td><fmt:formatDate value="${ board.updatedate }" pattern="yyyy-MM-dd" /></td>
             </tr>
           </c:forEach>
         </c:otherwise>
       </c:choose>
    </tbody>
    <tfoot>
    	<tr>
    		<td colspan="5">
    			<div class="center">
    				<div class="pagination">
    					<!-- prev -->
    					<c:if test="${pageMaker.prev}">
    						<a href="${pageMaker.startPage-1}">&laquo;</a>
    					</c:if>
    					<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1" var="num">
    						<a href="${num}" class="${num eq pageMaker.criteria.pageNum ? 'active' : ''}">${num}</a>
    					</c:forEach>
    					<!-- next -->
    					<c:if test="${pageMaker.next}">
    						<a href="${pageMaker.endPage+1}">&raquo;</a>
    					</c:if>
    				</div>
    			</div>
    		</td>
    	</tr>
    </tfoot>
  </table>
  
  <form id="actionForm" action="/board/list" method="get">
  	<input type="hidden" name="pageNum" value="${pageMaker.criteria.pageNum}">
  	<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
  </form>
  
</div>

<script>

	$(function() {
		var result = '<c:out value="${result}" />';
		var bno = '<c:out value="${bno}" />';
		// alert(result+"번 글 등록되었습니다.");
		checkModal(result);
		
		function checkModal(result) {
			if (result === "" || history.state) return;
			
			switch (result) {
			case "REGISTERED":
				alert(`\${bno}번 글이 등록되었습니다.`);
				return;
				
			case "REMOVED":
				alert(`\${bno}번 글이 삭제되었습니다.`);
				return;

			default:
				return;
			}
			
		} // function checkModal(result) {
		
		var actionForm = $("#actionForm");
			
		// 페이징 블럭에서 번호를 클릭할 때 이동
		$(".pagination a").on("click", function() {
			event.preventDefault();
			let pageNum = $(this).attr("href");
			actionForm
					  .find(":hidden[name=pageNum]")
					  								.val(pageNum)
					  								.end()
					  .submit();
		}); // $(".pagination a").on("click", function() {
			
		// 게시글을 클릭할 때 이동
		$("a.move").on("click", function() {
		// $("tbody a").on("click", function() {
			event.preventDefault();
			let bno = $(this).attr("href");
			actionForm
			  .attr("action", "/board/get")
			  .append(`<input type="hidden" name="bno" value="\${bno}">`)
			  .submit();
			
			/*
			let pageNum = ${param.pageNum};
			let amount = ${param.amount};
			let newHref = oldHref + `&pageNum=\${pageNum}&amount=\${amount}`;
			location.href = newHref;
			*/
		}); // $("tbody a").on("click", function() {
		
	}); // $(function() {

</script>

</body>
</html> 