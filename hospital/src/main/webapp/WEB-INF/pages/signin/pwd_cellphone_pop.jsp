<%@ page language="java" contentType="text/html; utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
	<title><fmt:message key="pages.title" /></title>
		<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/common.css" />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/popup.css" />" />		
	
	<script type="text/javascript">

		$(function(){
			<c:if test="${not empty errMsg}">
				alert("${errMsg}");
				self.close();
			</c:if>
			
			$("#btn_exe").click(function(){
				
				$.ajax({
					url:"<c:url value="/signin/id_find_phone_pop_json.do" />", 
					data:null,
					type:'post',
					dataType:'json',
					 async : false,
					contextType:'application/json',
					success:function(data){				
						if( data.flag == "UNEXP" || data.flag== "BAD_REQ"  )
						{
							alert(data.msg);
							window.close();
						}
						else
						{
							alert("해당 휴대전화로 인증번호를 발송하였습니다.");
							//인증번호 창으로 이동.
							document.location.replace("/signin/pwd_find_phone_pop_keyin.do");
						}
					},
					error:function(request, status, error)
					{	
						alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
					} 
				});					
				return false;
			});
			
			$("#btn_cancel").click(function(){
				self.close();
			});
		
			
		});
	</script>
</head>
<body>
	
<div id="pop_wrap">

	<!-- header-->
	<h1 class="header">비밀번호 재발급</h1>
	<!-- //header-->

	<!-- contents width:400px-->
	<div class="pop_container">
		<form id="cmdFrm" name="cmdFrm">
		<input type="hidden" id="phone" name="phone" value=${find_id_phone } />
		
		<div class="pop_contents">
			<p>회원정보에 등록된 휴대폰으로 인증번호를 발송해 드립니다.</p>
			<strong>${find_id_hidden_phone}</strong>
		</div>
		
		<!-- 버튼 영역 -->
		<div class="btn_area">
			<span class="btn_pack m_gray"><input type="button" id="btn_exe" value="인증번호 받기"/></span>
			<span class="btn_pack m_gray"><input type="button" id="btn_cancel" value="취소"/></span>
		</div>
		<!-- //버튼 영역 -->
		</form>
	</div>
	<!-- //contents -->



</div>	
	
	
	
</body>
</html>