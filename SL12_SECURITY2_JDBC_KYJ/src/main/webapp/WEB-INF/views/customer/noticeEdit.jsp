<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>
	<form action="/customer/noticeEdit.htm?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="seq" value="${notice.seq}">
	<!-- <form action="noticeEdit.htm" method="post" enctype="multipart/form-data"> -->
		<%-- <input type="hidden" name="seq" value="${ notice.seq }" /> --%>
		<div id="notice-article-detail" class="article-detail margin-large">
			<dl class="article-detail-row">
				<dt class="article-detail-title">제목</dt>
				<dd class="article-detail-data">
					&nbsp;<input name="title" value="${ notice.title }" />
				</dd>
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
					&nbsp;<input type="file" id="txtFile" name="file" /> <input
						type="hidden" id="originalFile" name="o_filesrc"
						value="${notice.filesrc}" />
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content">${ notice.content }</textarea>
			</div>
		</div>
		<p class="article-comment margin-small">
			<!-- <button class="btn-save button" type="submit">수정</button> -->
			<input class="btn-save button" type="submit" value="수정" /> <a
				class="btn-cancel button" href="noticeDetail.htm?seq=${notice.seq}"></a>
		</p>
	</form>
</div>

