<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
	<title><fmt:message key="pages.title" /></title>
	<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/common.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/style.css" />" />	
	
	<c:if test="${not empty errMsg}">
		<script type="text/javascript">
			alert("${errMsg}");
			window.location.replace("<c:url value="/signin/idpwdFind.do" />");		
		</script>
	</c:if>	
	
	<script type="text/javascript">
	$.noConflict();

	jQuery(document).ready(function($){

	});
	
	
	</script>

</head>


<body>


<div id="wrap">

	<c:set var="topmenu_index" value="-1"/>
	<c:set var="sidebar_index" value="2"/>
	<c:set var="sidebar_single_yn" value="N"/>
	
	<!-- header-->
	<%@ include file= "/WEB-INF/pages/includes/header.jspf" %>
	<!-- //header-->

	<!-- container -->
	<div id="container">

		<!-- lnb -->
		<jsp:include page="/main/leftmenu.do">
			<jsp:param value="01" name="menuid"/>
		</jsp:include>
		<!-- //lnb -->

		<!-- contents -->
		<div id="contents">
			<h3 class="contents_title"><img src='<c:url value="${dirImages}/member/title_idpw_search.png" />'  alt="아이디/비밀번호 찾기"/></h3>
			<div class="contents_wrap">
	
				
				<h5 class="s_title">아이디 찾기 </h5>
				<div class="result_box">
					<p>고객님의 아이디는 다음과 같습니다.</p>
					<c:forEach var="find" items="${id_list}">	
						<strong><c:out value="${find.m_id}" /></strong>
					</c:forEach>					
				</div>

				<!-- 버튼 영역 -->
				<div class="btn_area contents_hr">
					<span class="btn_pack m_gray" id="btnLogin"><a href="<c:url value="/signin/login.do" />">로그인</a></span>
					<span class="btn_pack m_gray" id="btnFindPwd"><a href="<c:url value="/signin/idpwdFind.do" />">비밀번호 찾기</a></span>
				</div>
				<!-- //버튼 영역 -->	
			</div>
		</div>
		<!-- //contents -->

	</div>
	<!-- //container -->

	<!-- footer -->
	<%@ include file= "/WEB-INF/pages/includes/footer.jspf" %>
	<!-- //footer -->

</div>	
	

</body>
</html>