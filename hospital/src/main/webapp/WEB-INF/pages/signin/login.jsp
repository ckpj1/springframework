<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
	<head>
		<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
		<title><fmt:message key="pages.title" /></title>
		<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="${dirScripts}/plugin/ui/jquery-ui-1.9.2.custom.css" />" />
		<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-ui-1.9.2.custom.js" />"></script>
					<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/common.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/style.css" />" />
	</head>
	
	<sec:authorize access="isAuthenticated()">
		<script type="text/javascript">
			history.back();
		</script>
	</sec:authorize>
		
	<script type="text/javascript">	
	$(function() {
		var flag = '<c:out value="${flag}" />';		

		//로그인 에러시 팝업 호출.
		if(flag =="error"){
			alert("아이디/비밀번호가 잘못 되었습니다.");
		}
		
		jQuery("#j_username").val("E-Mail");
		jQuery("#j_username").addClass("txt_login_default");
		jQuery("#j_password").val("");

		jQuery(".loginbtn").click(function(){
			
			var user_name = jQuery("#j_username").val();
		    var user_pass =  jQuery("#j_password").val();
	
		    if(user_name == ""){
		    	alert("아이디를 입력해 주세요.");
		    	jQuery("#j_username").focus();
		    	return false;
		    }
		    
		    if(user_pass == ""){
		    	alert("비밀번호를 입력해 주세요.");
		    	jQuery("#j_password").focus();
		    	return false;
		    }
		    
		   	document.loginFrm.action = "/j_spring_security_check";
		    document.loginFrm.submit();		    
		});  
		    
		jQuery('#j_username')
		  .on('focus', function(){
		      var $this = jQuery(this);
		      if($this.val() == 'E-Mail'){
		          $this.val('');
		          $this.removeClass("txt_login_default");
		      }
		  })
		  .on('blur', function(){
		      var $this = jQuery(this);
		      if($this.val() == ''){
		          $this.val('E-Mail');
		          $this.addClass("txt_login_default");
		      }
		  });
		    
	});	
	
	</script>
	
	<body>
	
<div id="wrap">
	<c:set var="topmenu_index" value="-1"/>
	<c:set var="sidebar_index" value="0"/>
	<c:set var="sidebar_single_yn" value="N"/>


	<!-- header-->
	<%@ include file= "/WEB-INF/pages/includes/headerLogin.jspf" %>
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
			<h3 class="contents_title"><img src='<c:url value="${dirImages}/member/title_login.png" />' alt="로그인"/></h3>
			<div class="contents_wrap">

				<!-- login box action='/j_spring_security_check' -->
				<form id="loginFrm" name="loginFrm" method="post">
				<fieldset class="login_box" >
					<legend class="blind">로그인</legend>
					<div class="login_inputbox">
						<div class="login_uid">
							<label for="j_username"><img src='<c:url value="${dirImages}/common/layer_uid.png" />' alt="아이디"/></label>
							<input class="txt_big" type="text" id="j_username" name="j_username" tabindex="1" value="Email" size="29"/>
						</div>
						<div class="login_upw">
							<label for="j_password"><img src='<c:url value="${dirImages}/common/layer_upw.png" />' alt="비밀번호"/></label>
							<input class="txt_big" type="password"   id="j_password" name="j_password" tabindex="2" value="" size="29" />
						</div>
						<span><input type="image" value="로그인" class="loginbtn" src='<c:url value="${dirImages}/common/layer_loginbtn.png" />' /></span>
					</div>
					<div class="login_join"><a href="<c:url value='/signup/memsel.do' />"><strong>회원가입</strong></a>  |  <a href="<c:url value='/signin/idpwdFind.do' />">아이디/비밀번호 찾기</a></div>
				</fieldset>
				</form>
				<!-- //login box -->

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