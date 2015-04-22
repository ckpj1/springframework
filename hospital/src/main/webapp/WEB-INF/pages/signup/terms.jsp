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

		jq("#btnAgree").click(function(){
			var ichk = false;
			
			jq('input:checkbox[name="chkAgree"]').each(function(){
				if(!this.checked){
				
					if(this.id == "chkAgree1"){
						alert("GameKOK 런처 서비스 이용 약관에 동의해 주세요.");	
					}else if(this.id == "chkAgree2"){
						alert("개인정보 취급벙침 이용 약관에 동의해 주세요.");
					}else{					
						alert("GameKOK 사용권 계약서에 동의해 주세요.");
					}
					
					ichk = false;
					return false;
				}else{
					ichk = true;
				}
			});			
			
			if(ichk){
				openKMCWindow();
				return false;
			}			
			
			return false;
	
		});
	
		
		jq("#btnCancel").click(function(){
			document.location.href = "/";
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
				<form id="crmForm" method="post">
					<!-- 약관내용 -->
					<fieldset class="agree">
						<legend>GameKOK 런처 서비스 이용 약관</legend>
								<%@ include file= "/WEB-INF/pages/signup/terms_service_desc.jsp" %>	

						<div class="check">
							<input type="checkbox" id="chkAgree1" name="chkAgree" style="cursor:pointer;" />
							<label for="chkAgree1" style="cursor:pointer;">위 서비스 이용 약관에 동의합니다.</label>
						</div>
					</fieldset>
					<!-- //약관내용 -->
					<!-- 약관내용 -->
					<fieldset class="agree">
						<legend>개인정보취급방침</legend>
								<%@ include file= "/WEB-INF/pages/signup/terms_userinfo_desc.jsp" %>	
						<div class="check">
							<input type="checkbox" id="chkAgree2" name="chkAgree" style="cursor:pointer;" />
							<label for="chkAgree2" style="cursor:pointer;">위 개인정보 취급방침에 동의합니다.</label>
						</div>
					</fieldset>
					<!-- //약관내용 -->					

					<!-- 약관내용 -->
					<fieldset class="agree">
						<legend>GameKOK 사용권 계약서</legend>
						<%@ include file= "/WEB-INF/pages/signup/terms_use_desc.jsp" %>
							
						<div class="check">
							<input type="checkbox" id="chkAgree3" name="chkAgree" style="cursor:pointer;" />
							<label for="chkAgree3" style="cursor:pointer;">위 사용권 계약서에 동의합니다.</label>
						</div>
					</fieldset>
					<!-- //약관내용 -->

					<!-- 버튼 영역 -->
					<div class="btn_area">
						<span class="btn_pack m_orange"><input type="button" id="btnAgree" value="약관동의 및 본인확인"/></span>
						<span class="btn_pack m_gray"><input type="button" id="btnCancel" value="동의하지 않습니다"/></span>
						
						<!-- <a href="/signup/chk_member_dup.do?kind=${kind}" >[테스트]회원가입폼으로 바로 이동.</a> -->
					</div>
					<!-- //버튼 영역 -->

				</form>

				<!--  휴대폰인증 -->
				<c:set var="url_code" value="009005"/> <!-- 페이지별 인증코드값 -->
				<c:set var="tr_url" value='<%=request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf(request.getRequestURI()))+"/signup/personal_kmc.do" %>' /> <!-- 결과수신 페이지 (FULL URL)-->
				<c:set var="next_url" value='<%=request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf(request.getRequestURI()))+"/signup/chk_member_dup.do" %>' /> <!-- 모화면 진행 페이지 (FULL URL)-->
				<%@ include file= "/WEB-INF/pages/includes/cert_kmc.jspf" %>

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
