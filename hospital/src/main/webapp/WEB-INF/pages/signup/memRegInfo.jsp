<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
	<head>
<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
<title><fmt:message key="pages.title" /></title>
<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
<script type="text/javascript" src="<c:url value="${dirScripts}/utils.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/common.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/style.css" />" />			

<script type="text/javascript">

$.noConflict();


jQuery(document).ready(function($){
	//확인
	$("#btnOk").click(function(){		
		document.location.replace("/main.do");
	});
	
	//정보수정
	$("#btnInfoUpdate").click(function(){
		document.location.replace("/mypage/memModifyPrev.do");
	});

	//매장 등록
	$("#btnShopReg").click(function(){
		document.location.replace("/mypage/shopReg.do");
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

				<h4 class="join_step3"><img src='<c:url value="${dirImages}/member/join_step_bg.png" />' alt="가입 완료"/></h4>
				<div class="result_box mgn_btm3">
					<p class="success_txt"><strong>${loginvo.name }</strong> 님 가입해 주셔서 감사합니다.<br/> 입력하신 정보를 확인해 주세요.</p>
				</div>

					<!-- 기본정보 -->
					<h5 class="s_title">개인 정보 확인 </h5>
					<form id="cmdFrm" name="cmdFrm" method="post" >
					<table cellspacing="0" cellpadding="0" class="join_info">
						<colgroup>
							<col width="140"/>
							<col width=" "/>
						</colgroup>
						<tr>
							<th><span>이름</span></th>
							<td>${loginvo.name }</td>
						</tr>
						<tr>
							<th><span>연락처</span></th>
							<td>${loginvo.phone }</td>
						</tr>
						<tr>
							<th class="tbl_btm"><span>아이디</span></th>
							<td class="tbl_btm">${loginvo.m_id }</td>
						</tr>
					</table>
					<!-- //기본정보 -->

					<!-- 버튼 영역 -->
					<div class="btn_area">
						<span class="btn_pack m_gray" id="btnOk"><a href="javascript:void(0);">확 인</a></span>
						<span class="btn_pack m_gray" id="btnInfoUpdate"><a href="#">정보수정</a></span>
						<span class="btn_pack m_gray" id="btnShopReg"><a href="#">매장등록</a></span>
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