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
		
		//휴대폰 인증
		$("#btnPhoneConfirm").on("click",function(){
			
			var leftPosition = (screen.width) ? (screen.width-500)/2 : 0;
			var topPosition = (screen.height) ? (screen.height-250)/2 : 0;
			var option = 'resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, height=250,width=500, top='+topPosition+',left='+leftPosition;
			var pop = window.open("", "SearchPwdCellphone", option);
			
			if(pop == null){
				alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
			}
			//signin/search_id_cellphone_pop			
			 $("#cmdFrm").attr("action","<c:url value="/signin/pwd_find_phone_pop.do" />").attr('target', 'SearchPwdCellphone').submit();
			
		});//end btnPhoneConfirm

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
	
				<form id="cmdFrm" name="cmdFrm">
				<h5 class="s_title">비밀번호 재발급 </h5>
				<div class="result_box">
					<p>회원정보에 등록된 다음의 휴대폰으로 인증 후 비밀번호를 재발급 받으실 수 있습니다.</p>
					<c:forEach var="find" items="${id_list}">	
						<strong><c:out value="${find.hidden_phone}" /></strong>
					</c:forEach>					
				</div>
				
				<!-- 버튼 영역 -->
				<div class="btn_area contents_hr">
					<span class="btn_pack m_gray" id="btnPhoneConfirm"><a href="#" >휴대폰 인증</a></span>
				</div>
				</form>
				<!-- //버튼 영역 -->	
			</div>		

	
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