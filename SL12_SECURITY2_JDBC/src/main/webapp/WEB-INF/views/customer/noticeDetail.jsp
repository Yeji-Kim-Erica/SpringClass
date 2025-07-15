<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li id="home"><a href="">HOME</a></li>
		<li><a href="">고객센터</a></li>
		<li><a href="">공지사항</a></li>
	</ul>
	<div id="notice-article-detail" class="article-detail margin-large">
		<dl class="article-detail-row">
			<dt class="article-detail-title">제목</dt>
			<dd class="article-detail-data">${ notice.title }</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">작성일</dt>
			<dd class="article-detail-data">${ notice.regdate }</dd>
		</dl>
		<dl class="article-detail-row half-row">
			<dt class="article-detail-title">작성자</dt>
			<dd class="article-detail-data half-data">${ notice.writer }</dd>
		</dl>
		<dl class="article-detail-row half-row">
			<dt class="article-detail-title">조회수</dt>
			<dd class="article-detail-data half-data">${ notice.hit }</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">첨부파일</dt>
			<dd class="article-detail-data">
				<a href="download.htm?dir=/customer/upload&filesrc=${notice.filesrc}">${notice.filesrc}</a>
			</dd>
		</dl>

		<div class="article-content">
			<c:if test="${not empty notice.filesrc and fn:endsWith(notice.filesrc, '.jpg')}">
				<div><img alt="첨부파일 이미지" src="${pageContext.request.contextPath}/customer/upload/${notice.filesrc}"></div>
			</c:if>
			${ notice.content }
		</div>
	</div>
	<p class="article-comment margin-small">
		<!-- 목록 버튼 -->
		<a class="btn-list button" href="notice.htm"></a>
		<sec:authentication property="principal" var="pinfo" />
		<sec:authorize access="isAuthenticated()">
			<c:if test="${pinfo.username eq notice.writer}">
				<!-- 수정 버튼 -->
				<a
					class="btn-edit button"
					href="noticeEdit.htm?seq=${notice.seq}&filesrc=${notice.filesrc}"></a>
				<!-- 삭제 버튼 -->
				<a class="btn-del button"
					href="noticeDel.htm?seq=${notice.seq}&filesrc=${notice.filesrc}"></a>
			</c:if>
		</sec:authorize>
	</p>
	<div class="margin-small" style="border-top: 1px solid #dfdfdf;">
		<dl class="article-detail-row">
			<dt class="article-detail-title">▲ 다음글</dt>
			<dd class="article-detail-data">다음 글이 없습니다.</dd>
		</dl>
		<dl class="article-detail-row">
			<dt class="article-detail-title">▼ 이전글</dt>
			<dd class="article-detail-data">제 12회 창업스쿨</dd>
		</dl>
	</div>
</div>

<script>
	$(function() {
		$("a.btn-del.button").on("click", function() {
			event.preventDefault();
			if (confirm("정말 삭제합니까?")) {
				location.href = $(this).attr("href");
			} // if
		});

		var result = '<c:out value="${result}" />';
		if (result === 'Failed') {
			alert("${notice.seq}번 공지글 삭제에 실패했습니다.");
		}
	}); // $(function() {
</script>

</body>
</html>
