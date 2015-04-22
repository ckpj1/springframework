
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr"/>
	<title><fmt:message key="pages.title" /></title>
	<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
	<sec:authorize access="isAuthenticated()">
		<script type="text/javascript">
			window.location.replace("<c:url value="/main.do" />");
		</script>
	</sec:authorize>

	<script type="text/javascript">
	
		// 팝업에서 이 창을 지정할 수 있도록!!
		window.name ="Parent_window";
	
		var jq = jQuery.noConflict();
		jq(document).ready(function(){
			jq("#btnCellpone").on("click",function(){
				openKMCWindow();
				return false;
			});
			
			jq("#btnUserInfo").on("click",function(){
				window.location.href = "/signup/chk_member_dup.do";
			});			
		});
		
	</script>
</head>

<body>

		<c:set var="topmenu_index" value="-1"/>
		<c:set var="sidebar_index" value="1"/>
		<c:set var="sidebar_single_yn" value="N"/>


		<span id="btnCellpone"><a href="#" >휴대폰으로 인증하기</a></span>
		&nbsp;&nbsp;&nbsp;<span id="btnUserInfo"><a href="#" >[테스트]회원가입폼으로 바로 이동.</a></span>
	<!--  휴대폰인증 -->
	<c:set var="url_code" value="009001"/> <!-- 페이지별 인증코드값 -->
	<c:set var="tr_url" value='<%=request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf(request.getRequestURI()))+"/signup/personal_kmc.do" %>' /> <!-- 결과수신 페이지 (FULL URL)-->
	<c:set var="next_url" value='<%=request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf(request.getRequestURI()))+"/signup/chk_member_dup.do" %>' /> <!-- 모화면 진행 페이지 (FULL URL)-->
	<%@ include file= "/WEB-INF/pages/includes/cert_kmc.jspf" %>
</body>
</html>

	