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
							document.location.replace("<c:url value="/signin/id_find_phone_pop_keyin.do" />");
						}
					},
					error:function(request, status, error)
					{	
						alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
					} 
				});					
				return false;

			});
		
			
		});
	</script>
</head>
<body>
	
		<h1>인증번호 받기</h1>	
	<form>
		<input type="hidden" id="phone" name="phone" value=${find_id_phone } />
		<fieldset>
			
				회원 가입 시 등록하신 휴대폰으로 인증번호 발송해 드립니다.<br/>
				<label id="cellphone" >${find_id_hidden_phone}</label>
			
			<div>
				<button id="btn_exe" >인증번호 받기</button>
			</div>
		</fieldset>
	</form>
	
	
</body>
</html>