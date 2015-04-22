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
	<script type="text/javascript">
	
		$(function(){
			$("#btn_retry").click(function(){
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
							window.close();
						}else if(data.flag == "NOMATCH"){
							alert(data.msg);
							$("#cert_num").val("");
						}
						else //성공
						{
							//아이디 최종보여주기 화면으로 이동.
							alert(data.msg);
							
							opener.location.href = "/signin/idresult_view.do";
							self.close();							
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
<body class="popup">
	<div class="popupTop">
		<h1>인증번호 입력</h1>
	</div>
	<form id="cmdFrm">
		<fieldset>
			<div>
				<p>휴대폰으로 받은 인증번호 입력 후 확인을 눌러 주세요.</p>
				<div>
					<h2>인증번호</h2>
					<input type="text" id="cert_num" name="cert_num" maxlength=10 />
					<span class="btn typeSm"><a href="#" id="btn_retry" name="btn_retry">인증번호 재전송</a></span>
				</div>
			</div>
			<div class="popupBtm">
				<span class="btn typeMid" id="btn_exe"><button>확인</button></span>
			</div>
		</fieldset>
	</form>
</body>
</html>