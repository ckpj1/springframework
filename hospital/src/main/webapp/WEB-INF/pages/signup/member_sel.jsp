<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
<title><fmt:message key="pages.title" /></title>
<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/common.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/style.css" />" />
<sec:authorize access="isAuthenticated()">
	<script type="text/javascript">
		window.location.replace("<c:url value="/main.do" />");
	</script>
</sec:authorize>		
<script type="text/javascript">
$.noConflict();


jQuery(document).ready(function($){
	$("#btnJumju").click(function(){
		
		$("#kind").val("M");
		
		document.location.href=  "<c:url value="/signup/terms.do" />?kind=M";
		//$("#cmdFrm").attr("action", "<c:url value="/signup/terms.do" />?kind=M").submit();
	});
	
	$("#btnMember").click(function(){
		$("#kind").val("S");
		document.location.href=  "<c:url value="/signup/terms.do" />?kind=S";
		//$("#cmdFrm").attr("action", "<c:url value="/signup/terms.do" />").submit();
	});
	
});

</script>


</head>
<body>

<div id="wrap">
	<c:set var="topmenu_index" value="-1"/>
	<c:set var="sidebar_index" value="1"/>
	<c:set var="sidebar_single_yn" value="N"/>
	<!-- header-->
	<%@ include file= "/WEB-INF/pages/includes/header.jspf" %>
	<!-- header-->
	
	
	<!-- container -->
	<div id="container">

		<!-- lnb -->
		<jsp:include page="/main/leftmenu.do">
			<jsp:param value="01" name="menuid"/>
		</jsp:include>
		<!-- lnb -->

		<!-- contents -->
		<div id="contents">
		
			<h3 class="contents_title"><img src='<c:url value="${dirImages}/member/title_member.png" />' alt="회원가입"/></h3>
			<div class="contents_wrap">

				<h5 class="s_title">가입하려는 회원 유형을 선택하세요 </h5>
				<form id="cmdFrm" name="cmdFrm" method="post">
				<input type="hidden" id="kind" name="kind"/>
				
				<div class="member_type">
					<a href="#" id="btnJumju"><img src='<c:url value="${dirImages}/member/member_type1.png" />' alt="사업자 회원 가입하기"/></a>
					<span class="type_separate"></span>
					<a href="#" id="btnMember"><img src='<c:url value="${dirImages}/member/member_type2.png" />' alt="일반 회원 가입하기"/></a>
				</div>				
				
	
				</form>					
				
				


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