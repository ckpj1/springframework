<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title><fmt:message key="pages.title" /></title>
	<link rel="stylesheet" href="<fmt:message key='styles.dir'/>/common.css"  type="text/css"/>
	<link rel="stylesheet" href="<fmt:message key='styles.dir'/>/popup.css" type="text/css"/>
	<script type="text/javascript" src="<fmt:message key='scripts.dir'/>/jquery1.8.3.js" ></script>
	<script type="text/javascript">
		function closePop()
		{	
			window.close();
		}
	</script>
</head>
<body class="popup" >
	<div class="popupTop">
		<h1>가입 인증</h1>
	</div>
	<div class="popupContent">
		<p class="msg">
			<c:choose>
				<c:when test="${result == 'fail'}">
				인증에 실패하였습니다.<br />휴대폰 번호 및 인증 번호를 다시 확인해주세요.
				</c:when>
				<c:otherwise>
				인증에 성공하였습니다.<br />다음 단계로 이동합니다.
				</c:otherwise>
			</c:choose>
		</p>
	</div>
	<div class="popupBtm">
		<form id="frm_confirm" method="GET" >
			<input type="hidden" name="step" value="${step}" />
			<span class="btn typeMid"><a href="#" onclick="closePop();">확 인</a></span>
		</form>
	</div>
</body>
</html>

