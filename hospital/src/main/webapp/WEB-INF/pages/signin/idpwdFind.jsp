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
			//alert("로그인 상태에서는 접근할 수 없는 페이지 입니다.");
			window.location.replace("<c:url value="/main.do" />");
			
		</script>
	</sec:authorize>
	
	<script type="text/javascript">
	$.noConflict();


	jQuery(document).ready(function($){
		// 숫자만 입력가능
	    $('.numonly').css('imeMode','disabled').keypress(function(event) {
	        if(event.which && (event.which < 48 || event.which > 57) ) {
	            event.preventDefault();
	        }
	    }).keyup(function(){
	        if( $(this).val() != null && $(this).val() != '' ) {
	            //var tmps = $(this).val().replace(/[^0-9]/g, '');
	            var tmps = $(this).val().replace(/[^0-9]/g, '');
	            
	            $(this).val(tmps);
	    }
	    });	
		
		// 전화번호 가운데 3자리 입력후 다음으로 이동
		$("#id_phone_1").on("keyup",function(){
		  	var mch = $(this).val().match(/[0-9]/g);
		  	if( mch != null && mch.length == 3 )
		  	{
		  		$("#id_phone_2").focus();
		  	}
		});
		$("#pwd_phone_1").on("keyup",function(){
		  	var mch = $(this).val().match(/[0-9]/g);
		  	if( mch != null && mch.length == 3 )
		  	{
		  		$("#pwd_phone_2").focus();
		  	}
		});		
		
		// 전화번호 가운데 4자리 입력후 다음으로 이동
		$("#id_phone_2").on("keyup",function(){
		  	var mch = $(this).val().match(/[0-9]/g);
		  	if( mch != null && mch.length == 4 )
		  	{
		  		$("#id_phone_3").focus();
		  	}
		});
		$("#pwd_phone_2").on("keyup",function(){
		  	var mch = $(this).val().match(/[0-9]/g);
		  	if( mch != null && mch.length == 4 )
		  	{
		  		$("#pwd_phone_3").focus();
		  	}
		});		
		
		//아이디 찾기.			
		$("#btnID").click(function(){
			if( $.trim($("#id_name").val()) == "" )
			{
				alert("이름을 입력하세요.");
				$("#id_name").focus();
				return false;
			}	
			
			if( $.trim($("#id_phone_1").val()) == "" || $.trim($("#id_phone_2").val()) == "" || $.trim($("#id_phone_3").val()) == "")
			{
				alert("전화번호를 입력하세요.");
				return false;
			}			
			
			var frm = $("#idFrm").serialize();
			$.ajax({
				url: "<c:url value="/signin/findaction.do" />",
				data:frm,
				type:'post',
				dataType:'json',
				contextType:'application/json',	
				success:function(data){					
					if(data.flag =="success"){		
						
						if(data.result.length == 0){
							alert(data.msg);
							return false;
						}else{
							document.location.href  = "<c:url value="/signin/idpreview.do" />";
						}
					}
					else{
						alert(data.msg); 
						return false;
					}					
				},
				error:function(request, status, error)
				{	
					alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
				}
			}); //end ajax	
			// submit 방지
			return false;		
			
		});
		
		//비밀번호 찾기
		$("#btnPWD").click(function(){
			if( $.trim($("#pwd_name").val()) == "" )
			{
				alert("이름을 입력하세요.");
				$("#pwd_name").focus();
				return false;
			}	
			
			if( $.trim($("#pwd_id").val()) == "" )
			{
				alert("아이디를 입력하세요.");
				$("#pwd_id").focus();
				return false;
			}				
			
			
			if(( $.trim($("#pwd_id").val())).search(/^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/) == -1 )
			{
				alert("아이디(이메일) 형식이 잘못됐습니다.");
				return false;
			}					
			
			if( $.trim($("#pwd_phone_1").val()) == "" || $.trim($("#pwd_phone_2").val()) == "" || $.trim($("#pwd_phone_3").val()) == "")
			{
				alert("전화번호를 입력하세요.");
				return false;
			}					
			
			var frm = $("#pwdFrm").serialize();
			$.ajax({
				url: "<c:url value="/signin/findaction.do" />",
				data:frm,
				type:'post',
				dataType:'json',
				contextType:'application/json',	
				success:function(data){					
					if(data.flag =="success"){		
						
						if(data.result.length == 0){
							alert(data.msg);
							return false;
						}else{
							document.location.replace("<c:url value="/signin/pwd_reissue.do" />");
						}
					}
					else{
						alert(data.msg); 
						return false;
					}					
				},
				error:function(request, status, error)
				{	
					alert("<fmt:message key="pages.httpcodes.http500.1" />\n<fmt:message key="pages.httpcodes.http500.2" />");<%-- 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. --%>
				}
			}); //end ajax	
			// submit 방지
			return false;					
			
		});		

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

				<!-- id search -->
				<div class="id_search_area" >
					<h4 class="s_title">아이디 찾기</h4>
					<div class="id_search_box">
						<p>GameKOK에 가입한 고객님의 성함과 휴대폰 번호를<br/>입력해 주세요.</p>

						
						<!-- input -->			
						<form id="idFrm">			
						<input type="hidden" name="type" value="id" />
						<div class="id_search">
							<div class="search_input">
								<label for="id_name">이름</label>
								<input  id="id_name" name ="id_name" class="txt" type="text" size="25"/>
							</div>
							<div class="search_input">
								<label for="id_phone_1">휴대폰</label>
								<input id="id_phone_1" name ="id_phone_1" class="txt numonly" type="text" maxlength=3 size="7"/> -
								<input id="id_phone_2" name ="id_phone_2" class="txt numonly" type="text" maxlength=4 size="7"/> -
								<input id="id_phone_3" name ="id_phone_3"  class="txt numonly" type="text" maxlength=4 size="7"/>
							</div>
						</div>
						<!-- //input -->

						<div class="btn_area">
							<span class="btn_pack m_gray"><input type="button" id="btnID" value="아이디 찾기"/></span>
						</div> 
						</form>

					</div>
				</div>
				<!-- //id search -->

				<!-- pw search -->
				<div class="id_search_area" >
					<h4 class="s_title">비밀번호 변경</h4>
					<div class="id_search_box">
						<p>GameKOK에 가입한 고객님의 성함과 아이디 및<br/>핸드폰 번호를 입력해 주세요.</p>

						<!-- input -->
						<form id="pwdFrm">
						<input type="hidden" name="type" value="pwd" />						
						<div class="id_search">
							<div class="search_input">
								<label for="pwd_name">이름</label>
								<input maxlength=4 id="pwd_name" name ="pwd_name" class="txt" type="text" size="25"/>
							</div>
							<div class="search_input">
								<label for="pwd_id">아이디</label>
								<input id="pwd_id" name ="pwd_id"  class="txt" type="text" size="25"/>
							</div>
							<div class="search_input">
								<label for="pwd_phone_1">휴대폰</label>
								<input type="text" class="txt numonly" maxlength=3 size="7" id="pwd_phone_1" name ="pwd_phone_1" /> - 
								<input type="text" class="txt numonly" maxlength=4 size="7" id="pwd_phone_2" name ="pwd_phone_2" /> - 
								<input type="text" class="txt numonly" maxlength=4 size="7" id="pwd_phone_3" name ="pwd_phone_3" />
							</div>
						</div>
						<!-- //input -->

						<div class="btn_area">
							<span class="btn_pack m_gray"><input type="button" id="btnPWD" value="비밀번호 변경"/></span>
						</div>
						</form>

					</div>
				</div>
				<!-- //pw search -->

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
	
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