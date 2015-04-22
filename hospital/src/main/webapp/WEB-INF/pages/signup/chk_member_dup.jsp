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
	$("#btnLogin").click(function(){
		document.location.href = "<c:url value="/signin/login.do" />";
	});
	
	$("#btnPwdFind").click(function(){
		document.location.href = "<c:url value="/signin/idpwdFind.do" />";
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
			<h3 class="contents_title"><img src='<c:url value="${dirImages}/member/title_member.png" />' alt="회원가입"/></h3>
			<div class="contents_wrap">

				<h4 class="join_step1"><img src='<c:url value="${dirImages}/member/join_step_bg.png" />' alt="약관동의 및 휴대폰 인증"/></h4>
				<h5 class="s_title">가입여부 확인결과 </h5>
								
				<form id="crmForm" method="post">
		
					<div class="result_box">
						<p>고객님께서는 다음의 아이디로 가입되어 있습니다.<br/>점주 회원으로 가입하시려면 회원 탈퇴 후 재 가입 하셔야 합니다.</p>
						<strong>${chkMem.m_id}<span class="date">(${chkMem.m_kind}&nbsp;&nbsp;${chkMem.reg_day} 가입)</span></strong>
					</div>

					<!-- 버튼 영역 -->
					<div class="btn_area contents_hr">
						<span class="btn_pack m_gray" id="btnLogin"><a href="#">로그인</a></span>
						<span class="btn_pack m_gray" id="btnPwdFind"><a href="#">비밀번호 찾기</a></span>
					</div>
					<!-- //버튼 영역 -->

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