<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" type="image/x-icon" href="/images/SiSt.ico">
<title>김예지 (2025. 7. 2. 오후 12:36:12)</title>
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
        /board/get.jsp
  </xmp>
  
  <form action="/board/register" method="post">
     <table>  
       <tbody>
         <tr>
           <th>글번호</th>
           <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ boardVO.bno }"></td>        
         </tr> 
         <tr>
           <th>제목</th>
           <td><input type="text" class="full"  readonly="readonly"  value="${ boardVO.title }"></td>        
         </tr> 
         <tr>
           <th>내용</th>
           <td><textarea class="full" readonly="readonly"><c:out value="${ boardVO.content }"></c:out></textarea></td>        
         </tr> 
         <tr>
           <th>작성자</th>
           <td><input type="text" class="short" readonly="readonly" value="${ boardVO.writer }"></td>        
         </tr>  
       </tbody> 
       <tfoot>
         <tr>
           <td colspan="2">
             <button type="button"  data-oper="modify" class="edit">Modify</button>
             <button type="button"  data-oper="remove" class="delete">Delete</button>
             <button type="button" data-oper="list" class="list">List</button>
           </td>
         </tr>
       </tfoot>
     </table>
     
     <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
       
  </form>
  
</div>

<script>
	$(function() {
		const formObj = $("form");
		
		
		$("tfoot button").on("click", function() {
			let operation = $(this).data("oper");
			
			// location.href = ?
			// /board/modify/2
			// /board/modify?bno=2
					
			switch (operation) {
			case "modify":
				formObj
					   .attr({
						   "action" : "/board/modify",
						   "method" : "GET"
					   })
					   .submit();
				break;
				
			case "remove":
				if (confirm("정말 삭제하시겠습니까?")) {
					formObj
					   .attr({
						   "action" : "/board/remove",
						   "method" : "GET"
					   })
					   .submit();
				} // if
				break;
				
			case "list":
				location.href = "/board/list";
				break;

			default:
				break;
			}
		}); // $("tfoot button").on("click", function() {

		var result = '<c:out value="${result}" />';
		var bno = '<c:out value="${param.bno}" />';
		checkModal(result);
		
		function checkModal(result) {
			if (result === "" || history.state) return;
			if (result === 'SUCCESS') {
				alert(`${boardVO.bno}번 글이 수정되었습니다.`);
				return;
			} // if
		} // function checkModal(result) {
			
	}); // $(function() {
</script>

</body>
</html> 