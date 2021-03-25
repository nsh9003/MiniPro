<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 메뉴 부분 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top shadow-lg">

<c:set var='root' value="${pageContext.request.contextPath }/" /> <!-- root라는 이름으로 기초경로 설정. 서블릿에서 설정한 "/WEB-INF/views/"을 가리킴 -->
	<a class="navbar-brand" href="${root}main">SoftSoldesk</a> <!-- 이름 같은 파일이 다른 폴더에 여러개 있을 수 있기때문에 위치를 지정해놔야 함 -->
	
	<button class="navbar-toggler" type="button" data-toggle="collapse"
	        data-target="#navMenu">
		<span class="navbar-toggler-icon"></span>        
	</button>
	<div class="collapse navbar-collapse" id="navMenu">
		<ul class="navbar-nav">
		<c:forEach var='obj' items='${topMenuList }'> 
		<li class="nav-item">
				<a href="${root}board/main?board_info_idx=${obj.board_info_idx}" class="nav-link">${obj.board_info_name }</a>
			</li>
		</c:forEach>

		</ul>
		<!-- c의 스위치 -->
		<!-- 유저로그인이 true면->로그인한 상태면 -->
		<ul class="navbar-nav ml-auto">
		<c:choose> 
		<c:when test="${loginUserBean.userLogin == true }">
		<li class="nav-item">
				<a href="${root}user/modify" class="nav-link">정보수정</a>
			</li>
			<li class="nav-item">
				<a href="${root}user/logout" class="nav-link">로그아웃</a>
			</li>
			
			</c:when>
			<c:otherwise> <!-- when에 해당하지 않으면 -->
			<li class="nav-item">
				<a href="${root}user/login" class="nav-link">로그인</a>
			</li>
			<li class="nav-item">
				<a href="${root}user/join" class="nav-link">회원가입</a>
			</li>
			</c:otherwise>
			</c:choose>
		</ul>
		
	</div>
</nav>


