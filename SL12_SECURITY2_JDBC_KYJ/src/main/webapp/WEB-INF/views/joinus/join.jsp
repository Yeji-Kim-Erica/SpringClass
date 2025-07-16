<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="content">
	<form id="joinForm" action="${pageContext.request.contextPath}/joinus/join.htm" method="post">
		<h2>회원가입</h2>
		<h3 class="hidden">방문페이지 로그</h3>
		<p id="breadscrumb" class="block_hlist clear">
			<img alt="Step1 개인정보 등록" src="images/step2.png" />
		</p>
		<h3 class="hidden">회원가입 폼</h3>
		<div id="join-form" class="join-form margin-large">
			<dl class="join-form-row">
				<dt class="join-form-title">아이디</dt>
				<dd class="join-form-data">
					<input type="text" name="id" /> <input id="btnCheckUid"
						class="button" type="button" value="중복확인" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">비밀번호</dt>
				<dd class="join-form-data">
					<input type="password" name="pwd" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">비밀번호 확인</dt>
				<dd class="join-form-data">
					<input type="password" name="pwd2" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">이름</dt>
				<dd class="join-form-data">
					<input type="text" name="name" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">성별</dt>
				<dd class="join-form-data">
					<select name="gender">
						<option>남성</option>
						<option>여성</option>
					</select>
				</dd>
			</dl>
			<dl class="join-form-row birthday">
				<dt class="join-form-title">생년월일</dt>
				<dd class="join-form-data">
					<span> <input type="text" id="year" />년 <input type="text"
						id="month" />월 <input type="text" id="day" />일 <input
						type="hidden" name="birth" id="Birth" />
					</span> <span class="moon"> <input type="radio" name="is_lunar"
						value="Solar" id="IsSolar" checked />양력 <input type="radio"
						name="is_lunar" value="Lunar" id="IsLunar" />음력
					</span>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">핸드폰 번호</dt>
				<dd class="join-form-data">
					<input type="text" name="cphone" /><span>[대시(-)를 포함할 것: 예)
						010-3456-2934]</span>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">이메일</dt>
				<dd class="join-form-data">
					<input type="text" name="email" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">취미</dt>
				<dd class="join-form-data habit">
					<input type="checkbox" name="habit" id="music" value="music" /><label
						for="music">음악</label> <input type="checkbox" name="habit"
						id="movie" value="movie" /><label for="movie">영화</label> <input
						type="checkbox" name="habit" id="trip" value="trip" /><label
						for="trip">여행</label>
				</dd>
			</dl>
		</div>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
		<div id="buttonLine">
			<button class="btn-okay button" type="submit" value="가입" />
		</div>
	</form>
</div>

<script>
	$(function() {
		/*
		$("#buttonLine button").on("click", function() {
			event.preventDefault();
			setBirth();
			$("#joinForm").submit();
		});
		 */

		var result = '<c:out value="${result}" />';
		console.log("> result : " + result);
		if (result === 'Failed') {
			alert("회원가입에 실패했습니다.");
		}
	});

	// 함수
	function pad2(v) {
		v = v.trim();
		return v.length === 1 ? '0' + v : v;
	}

	function setBirth() {
		const y = document.getElementById('year').value.trim();
		const m = pad2(document.getElementById('month').value);
		const d = pad2(document.getElementById('day').value);

		document.getElementById('Birth').value = `\${y}-\${m}-\${d}`;
	}
</script>

<script>
	// focus <-> blur 이벤트
	// blur: 입력이 끝나고 다른 칸으로 넘어가는 시점 (focus를 잃는 시점)
	/* [1]
	$("#day").blur(function (){
	   var year = $("#year").val();
	   var month = $("#month").val();
	   var day = $("#day").val();
	   var birth = `\${year}-\${month}-\${day}`;
	   $("#birth").val( birth );
	   // alert(  $("#birth").val() );
	});
	 */
</script>

</body>
</html>
