<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/style.css" />" />		
	<script type="text/javascript">
		
		// 비밀번호재발급
	$.noConflict();

	jQuery(document).ready(function($){
			
			<c:if test="${not empty errMsg}">
				alert("${errMsg}");
				self.close();
			</c:if>
			
			$("#btn_exe").click(function(){
				
				// 비밀번호
				if( $("#m_pw").val() == "" )
				{
					alert( "비밀번호 항목을 입력하지 않으셨습니다." );
					return false;
				}
				
				// 비밀번호확인
				if( $("#m_pw_cnf").val() == "" )
				{
					alert( "비밀번호를 확인해주세요." );
					return false;
				}
				
				if( checkPassWord( $("#m_id").val() , $("#m_pw").val() , $("#m_pw_cnf").val() ) == false )
				{
					return false;					
				}
				
				if(!confirm("비밀번호를 변경하시겠습니까?")){
					return false;
				}
				
				$.ajax({
					type: "post",
					url:"<c:url value="/signin/pwd_cellphone_pop_change_json.do" />", 
					data : $("#cmdFrm").serialize(),
					type:'post',
					dataType:'json',
					async : false,
					contextType:'application/json',
					success : function (data) {
						if( data.flag == "UNEXP" || data.flag == "BAD_REQ")
						{
							alert( data.msg);
							window.close();
						}
						else
						{
							alert(data.msg);
							document.location.href='/signin/login.do';							
						}
					},
					error:function(request, status, error)
					{	
						alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
					} 
				});
				
				// submit 방지
				return false;
			});
			
			function checkPassWord(ObjUserID, ObjUserPassWord, objUserPassWordRe)
			{
			    if(ObjUserPassWord != objUserPassWordRe)
			    {
			    	alert( "비밀번호와 비밀번호확인이 일치하지 않습니다." );
			        return false;
			    }
			    if(ObjUserPassWord.length<8 || ObjUserPassWord.length>20)
			    {
			    	alert( "비밀번호는 8~20자의 영문, 숫자, 특수문자의 혼합으로 가능합니다." );
			        return false;
			    }
			    if(ObjUserPassWord.match(/[^a-zA-Z0-9`~!#@#$%^&\*\(\)\-_\+\=\\\|\{\[\}\]\:\;|\?\,\.\<\>\'\"].*/))
			    {
			    	alert( "비밀번호는 8~20자의 영문, 숫자, 특수문자의 혼합으로 가능합니다." );
			        return false;
			    }
			    
			    if(ObjUserID.indexOf(ObjUserPassWord) > -1)
			    {
			        alert("비밀번호에 아이디를 사용할 수 없습니다.");
			        return false;
			    }
			    
			    return true;
			}
			
			
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

				<form id="cmdFrm">
				<input type="hidden" id="m_id" name="m_id" value="${m_id}"/>
				
				<h5 class="s_title">비밀번호 재발급 </h5>
				<div class="result_box">
					<p>변경할 비밀번호를 입력해 주세요. <br/>8~20자의 영문 대소문자, 숫자, 특수문자를 조합하여 사용하실 수 있습니다.</p>
					
					<!-- input -->
					<div class="new_pw">
						<div class="search_input">
							<label for="m_pw">새 비밀번호</label>
							<input class="txt" type="password" id="m_pw" name="m_pw" size="25"/>
						</div>
						<div class="search_input">
							<label for="m_pw_cnf">새 비밀번호 확인</label>
							<input class="txt" type="password" id="m_pw_cnf" name="m_pw_cnf" size="25"/>
						</div>
					</div>
					<!-- //input -->
				</div>

				<!-- 버튼 영역 -->
				<div class="btn_area contents_hr">
					<span class="btn_pack m_gray" id="btn_exe"><a href="#" >확인</a></span>
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