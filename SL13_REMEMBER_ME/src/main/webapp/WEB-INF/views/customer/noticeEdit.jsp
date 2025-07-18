<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="content">
	<h2>공지사항</h2>
	<h3 class="hidden">방문페이지위치</h3>
	<ul id="breadscrumb" class="block_hlist">
		<li>HOME</li>
		<li>고객센터</li>
		<li>공지사항수정</li>
	</ul>
	<!-- <form action="" method="post"> -->
	<!-- <form action="" method="post" enctype="multipart/form-data"> -->
	<%-- 스프링 시큐리티와  enctype="multipart/form-data" 인경우에 처리 방법 ?${_csrf.parameterName}=${_csrf.token} --%>
	<form action="/customer/noticeEdit.htm?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
	     <!-- 글번호 추가하기  -->
	     <input type="hidden" name="seq"   value="${notice.seq}">
	
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
						type="hidden" name="o_filesrc" value="${ notice.filesrc }" /> ${ notice.filesrc }
				</dd>
			</dl>

			<div class="article-content">
				<textarea id="txtContent" class="txtContent" name="content">${ notice.content }</textarea>
			</div>
		</div>
		<p class="article-comment margin-small">
			<!-- <a class="btn-save button" href="noticeEdit.htm">수정</a> -->
			<input type="submit" class="btn-save button" value="수정" /> <a
				class="btn-cancel button" href="noticeDetail.htm?seq=${ notice.seq }">취소</a>
		</p>
		<%-- 
		<input type="hidden" name="${ _csrf.parameterName }"
			value="${ _csrf.token }">
		 --%>	
	</form>
</div>
