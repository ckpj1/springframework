<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
	<head>
<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
<title><fmt:message key="pages.title" /></title>
<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
<script type="text/javascript" src="<c:url value="${dirScripts}/utils.js" />"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/common.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="${dirStyles}/style.css" />" />		

<sec:authorize access="isAuthenticated()">
	<script type="text/javascript">
		window.location.replace("<c:url value="/main.do" />");		
	</script>
</sec:authorize>
<script type="text/javascript">

$.noConflict();
jQuery(document).ready(function($){
	//취소
	$("#btnCancel").click(function(){
		
		$("#cmdFrm").attr("action", "<c:url value="/signin/cancel.do" />").submit();	
		return false; 

	});
	
	//회원등록 버튼 
	$("#btnReg").click(function(){
		
		
		if( $("#email_id").val() == "" || $("#email_server").val() == "" )
		{
			alert("아이디를 입력하세요.");
			return false;
		}		
		
		if( ($("#email_id").val()+"@"+$("#email_server").val()).search(/^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/) == -1 )
		{
			alert("아이디(이메일) 형식이 잘못됐습니다.");
			return false;
		}		
		
		// 아이디 중복확인 체크
		if( $("#dup_cheked").val() != "1" )
		{
			alert("아이디 중복체크를 해주세요.");
			return false;	
		}		
		
		// 비밀번호확인
		if( $("#pwd").val() == "" )
		{
			alert("비밀번호를 입력하세요.");
			return false;
		}		
		if( $("#pwd_cnf").val() == "" )
		{
			alert("비밀번호확인을 입력하세요.");
			return false;
		}
		
		if( checkPassWord( $("#pwd").val() , $("#pwd_cnf").val() ) == false )
		{
			return false;					
		}		

		if(!confirm("회원가입을(를) 하시겠습니까?")){
			return false;
		}					
		
		$("#m_id").val($("#email_id").val()+"@"+$("#email_server").val());
		
		
		var frm = $("#cmdFrm").serialize();
		$.ajax({
			url: "<c:url value="/signup/userinfo_json.do" />",
			data:frm,
			type:'post',
			dataType:'json',
			 async : false,
			contextType:'application/json',	
			success:function(data){
				
				if(data.result =="success"){		
					alert(data.msg);
					
					//회원가입이 성공하면 security를 통해 로그인 시도 후 가입완료 페이지 이동.
					$.ajax({
						url: "/j_spring_security_check",
						data: { j_username: data.m_id , j_password: data.password }, 
						type:'post',				
						async : false,
				        beforeSend: function (xhr) {
				              xhr.setRequestHeader("X-memReg-call", "true");
				        },				 
						success:function(result){		
						   if (result == "ok") {	
							   document.location.replace("<c:url value="/signup/memRegInfo.do" />");
					            return false;
					          }else {           
					             
					        	  alert("<fmt:message key="login.exception.AccountNotEqual" />");
					        	  
					            return false;           
					        }	
						},
						error:function(request, status, error)
						{	
							alert("시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다.");
						} 
					}); //end ajax(회원로그인) 
				 	return false;
				}
				else if(data.result == "LOGIN_SUCCESS"){
					alert(data.msg); 
					document.location.href="/";
					return false;
				}
				else{
					alert(data.msg); 
					return false;
				}
				
			},
			error:function(request, status, error)
			{	
				alert(" 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. ");
			}
		}); //end ajax	
		// submit 방지
		return false;		
		
	});
	
	//이메일 선택
	$("#email_sel").change(function(){
		
		if( $("#email_sel").val() == "self" )
		{
			$("#email_server").prop("readonly",false);
			$("#email_server").val("");
		}
		else
		{
			$("#email_server").prop("readonly",true);
			
			$("#email_server").val($("#email_sel").val());
		}
	});
	
	
	// 아이디 중복
	$("#btn_chk_dup").click(function(){
		// 아이디 입력필수
		if(  $("#email_id").val() == "" || $("#email_server").val() == "")
		{
			alert("아이디를 입력하세요.");
			return false;
		}		

		if( ($("#email_id").val()+"@"+$("#email_server").val()).search(/^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/) == -1 )
		{
			alert("아이디(이메일) 형식이 잘못됐습니다.");
			return false;
		}		
		
		$.ajax({
			url: "<c:url value="/signup/check_dup.do" />",
			data:{id : $("#email_id").val()+"@"+$("#email_server").val()},
			type:'get',
			dataType:'json',
			async : false,
			contextType:'application/json',	
			success : function (data) {
				if( data.result == "DUP" )
				{
					alert(data.msg);
				}
				else if( data.result == "UNEXP" )
				{
					alert(data.msg);
				}
				else
				{
					$("#dup_cheked").val("1");
					alert(data.msg);
				}
			},
			error : function (xhr, status, errorThrown) {
				alert(" 시스템 에러가 발생하였습니다.시스템 관리자에게 문의 바랍니다. ");
			}
		});
		// submit 방지
		return false;
	});		
	
	$("#email_id").on("keyup", function(){
		$("#dup_cheked").val("0");
	});
	
	$("#email_server").on("keyup", function(){
		$("#dup_cheked").val("0");
	});
	
	$("#email_sel").change(function(){
		$("#dup_cheked").val("0");
	});
	
	
	//패스워드 유효성 검사
	function checkPassWord(ObjUserPassWord, objUserPassWordRe)
	{
	    if(ObjUserPassWord != objUserPassWordRe)
	    {
	    	alert("입력한 비밀번호가 맞지 않습니다.");
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
	    
    
	    return true;
	}	
	
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

				<h4 class="join_step2"><img src='<c:url value="${dirImages}/member/join_step_bg.png" />' alt="개인 정보 입력"/></h4>
				<form id="cmdFrm" name="cmdFrm" method="post" >
				
					<input type=hidden id="m_id" name="m_id" />
					<input type="hidden" name="dup_cheked" id="dup_cheked" value="0" />
					<input type="hidden" id="m_kind" name="m_kind" value="${kind}" />

					<!-- 기본정보 -->
					<h5 class="s_title">기본 정보 입력</h5>
					<table cellspacing="0" cellpadding="0" class="join_info">
						<colgroup>
							<col width="140"/>
							<col width=""/>
						</colgroup>
						<tr>
							<th><span>이름</span></th>
							<td>${reg_member.name }</td>
						</tr>
						<tr>
							<th><span>연락처</span></th>
							<td>${reg_member.phone }</td>
						</tr>
						<tr>
							<th><span><strong>*</strong>아이디</span></th>
							<td>
								<input type="text" class="txt" id="email_id" maxlength="50" name="email_id" style="ime-mode:disabled"/> @ 
								<input type="text" class="txt" id="email_server" maxlength="30" name="email_server" />
								<select id="email_sel" name="email_sel">
									<option value="self">직접선택</option>
									<c:forEach var="rec" items="${email_server_list}" >
										<option value="${rec}">${rec}</option>
									</c:forEach>
								</select>	
								<span class="btn_pack s_gray"><input type="button" id="btn_chk_dup" value="중복확인"/></span>
							</td>
						</tr>
						<tr>
							<th><span><strong>*</strong>비밀번호</span></th>
							<td>
								<input class="txt" type="password" class="txt" id="pwd" name="pwd" size="30" />
								<font color="red">8~20자의 영문, 숫자, 특수문자의 혼합으로 가능합니다.</font>
							</td>
						</tr>
						<tr>
							<th class="tbl_btm"><span><strong>*</strong>비밀번호 확인</span></th>
							<td class="tbl_btm"><input class="txt" type="password" id="pwd_cnf" name="pwd_cnf" size="30"/></td>
						</tr>
					</table>
					<!-- //기본정보 -->

					<!-- 버튼 영역 -->
					<div class="btn_area">
						<span class="btn_pack m_orange"><input type="button" id="btnReg" value="확 인"/></span>
						<span class="btn_pack m_gray"><input type="button" id="btnCancel" value="취 소"/></span>
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
<script type="text/javascript">

	if(document.cmdFrm.mchk.value=="false"){
		alert("휴대폰 인증 값이 존재하지 않습니다.");	
		window.location.href = "/";
	}


</script>
	
	
		
</body>
</html>