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
	<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/popup.css" />" />		
	<script type="text/javascript">
	
		$(function(){
			//재전송
			$("#btn_retry").click(function(){
				$.ajax({
					url: "<c:url value="/signin/id_find_phone_pop_json.do" />",
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
						}
					},
					error:function(request, status, error)
					{	
						alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
					} 
				});//end ajax			
				
				return false;
			});//end btn_retry click
			
			//확인
			$("#btn_exe").click(function(){
				
				var frm = $("#cmdFrm").serialize();
				
				$.ajax({
					url:"<c:url value="/signin/id_find_phone_pop_keyin_json.do" />", 
					data:frm,
					type:'post',
					dataType:'json',
					 async : false,
					contextType:'application/json',
					success:function(data){				
						if( data.flag == "UNEXP" || data.flag== "BAD_REQ"  )
						{
							alert(data.msg);
							self.close();
						}else if(data.flag == "NOMATCH"){
							alert(data.msg);
							$("#cert_num").val("");
						}
						else //성공
						{
							//비밀번호 변경화면으로 이동
							alert(data.msg);
							
							window.opener.location.href = "/signin/pwd_cellphone_pop_change.do";
							window.close();
						}
					},
					error:function(request, status, error)
					{	
						alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
					} 
				});//end ajax			
				
				return false;
			});//end btn_retry click			
			
		});
	
	
	</script>
</head>
<body>

<div id="pop_wrap">

	<!-- header-->
	<h1 class="header">인증번호 입력</h1>
	<!-- //header-->

	<!-- contents width:400px-->
	<div class="pop_container">
		<form id="cmdFrm">
		
		<div class="pop_contents">			
			<strong>인증번호</strong>
			<input class="txt" type="text" size="20" id="cert_num" name="cert_num" maxlength=10 />
			<span class="btn_pack s_gray"><input type="button" id="btn_retry" value="재전송"/></span>
		</div>

		<!-- 버튼 영역 -->
		<div class="btn_area">
			<span class="btn_pack m_gray"><input type="button" id="btn_exe" value="확 인"/></span>
		</div>
		
		</form>
		<!-- //버튼 영역 -->

	</div>
	<!-- //contents -->
</div>


</body>
</html>