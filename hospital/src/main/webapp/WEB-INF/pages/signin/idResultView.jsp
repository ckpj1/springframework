<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
	<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
	<title><fmt:message key="pages.title" /></title>
	<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
	
	
	<c:if test="${not empty errMsg}">
		<script type="text/javascript">
			alert("${errMsg}");
			window.location.replace("<c:url value="/signin/idpwdFind.do" />");		
		</script>
	</c:if>	
	
	
	<script type="text/javascript">
	$(function(){

		//로그인
		$("#btnLogin").on("click",function(){
			document.location.href = "<c:url value="/signin/login.do" />";
		});
		
		//비밀번호 찾기
		$("#btnFindPwd").on("click",function(){
			document.location.href = "<c:url value="/signin/idpwdFind.do" />";
		});		
		
	});
	</script>

</head>



<body>


		<h4>▶아이디 결과</h4>
		<c:forEach var="find" items="${id_list}">	
			<label>
				<c:out value="${find.m_id}" />
			</label>
		</c:forEach>
					
		<br/><br/><br/><br/><br/>		
			
		<span id="btnLogin"><a href="#" >로그인</a></span>
		&nbsp;&nbsp;&nbsp;
		<span id="btnFindPwd"><a href="#" >비밀번호찾기</a></span>


</body>
</html>