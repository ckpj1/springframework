<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
<%@ include file="/WEB-INF/pages/includes/doctype.jspf" %>
<html>
	<head>
		<%@ include file="/WEB-INF/pages/includes/metatags.jspf" %>
		<title><fmt:message key="pages.title" /></title>
		
		<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-1.8.3.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="${dirScripts}/plugin/ui/jquery-ui-1.9.2.custom.css" />" />
		<%--<link rel="stylesheet" type="text/css" href="<c:url value="${dirScripts}/plugin/ui/demos.css" />" /> --%>
		<script type="text/javascript" src="<c:url value="${dirScripts}/jquery-ui-1.9.2.custom.js" />"></script>
		

		<script type="text/javascript">
			$(function(){
				$(".dateVal").datepicker({
					monthNamesShort: [<fmt:message key='pages.commons.months' />],//<%-- "1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월" --%>
				    dayNamesMin: [<fmt:message key='pages.commons.days' />],//<%-- "일","월","화","수","목","금","토" --%>
				    weekHeader: 'Wk',
				    dateFormat: 'yy-mm-dd', //형식(2012-03-03)
				    autoSize: false, //오토리사이즈(body등 상위태그의 설정에 따른다)
				    changeMonth: true, //월변경가능
				    changeYear: true, //년변경가능
				    showMonthAfterYear: true, //년 뒤에 월 표시
				    buttonImageOnly: true, //이미지표시
				    buttonImage: "<c:url value="${dirImages}/icon_calendar.gif" />",
				    showOn: "both", //엘리먼트와 이미지 동시 사용
				    yearRange: "c-5:c+5"	// 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가.
				});	
				
				$(".ui-datepicker-trigger").css({"vertical-align":"middle", "padding-left":"3px", "cursor" : "hand"});$("#ui-datepicker-div").hide();
				
				//로그인 팝업
				$("#btnlogin").click(function(){
					fnEdit();
				});
				
				//우편번호 검색
				$("#btnsearch1").click(function(){
						
						$.ajax({
							url: "<c:url value="/addr/findOldAddress.do" />",
							data:{
								regkey : "871d754d0b9db14b752957e28e72d4ea42b0630d",
								condition : "all",
								keyword : $("#dong").val()
							},
							type:'post',
							dataType:'json',
							 async : false,
							contextType:'application/json',
							success:function(data){
								$("#dataBody").html("");
								
								if(data.result.length != 0){
									for(var i =0; i < data.result.length; i++){
										var dataTrs = "";
										dataTrs+="<tr>";
										dataTrs+="<td name='data"+i+"'>"+data.result[i].zipcode  +"</td>";
										dataTrs+="<td name='data"+i+"'>"+data.result[i].address+"</td>";		
										dataTrs+= "</tr>";
										$(dataTrs).appendTo("#dataBody");
										
										tableTdOver("data"+i);			
									}//end for
								}//end if
							},
							error:function(request, status, error)
							{	
								alert("error---->" + request.responseText);
								//alert("시스템 에러 발생하였습니다. 관리자에게 문의하십시요.")
							} 
						});							
							
							
				});
				
				
			});
			
			function edit_popup(url, height, width){

			    iframe = $('<iframe src="' + url + '" frameborder="0" style="border:0" scrolling="no"></iframe>').dialog({
					title: "Login",
					autoOpen: true,
					height: height,
					width: width,
					position:[,],
					modal: true,
					closeOnEscape:true,
					resizable: false,
					autoResize: false,
					overlay: {
						opacity: 0.5,
						background: "black"
					},
					open: function(event, ui) {
					},
					close: function(event, ui) {
					}
				}).width(width-10).height(height);	 
				 
			}


			function fnEdit(){
				
			    var height = 150;
			    var width = 300;
			    
			    var url = "/signin/login.do";   

			    edit_popup(url, height, width);		
			}		
			
			function loginOK(){
				$("#cmdFrm").attr("action","<c:url value="/main.do" />").submit();
				
				//모달창을 close 시킨다.
			    setTimeout(function() {iframe.dialog('close');}, 500);
			}
			
		</script>
	</head>
	<body>
		<form id="cmdFrm" name="cmdFrm" method="post" >
			
<%--
1.datepicker
					<input type="text" id="stDt" name="stDt" class="dateVal" style="width: 80px;" maxlength="10" readonly="readonly"> ~
					<input type="text" id="edDt" name="edDt" class="dateVal" style="width: 80px;" maxlength="10" readonly="readonly">
 --%>
2.로그인 팝업		
		<a href="#" id="btnlogin" ><fmt:message key="pages.login.8" /></a> 
 
 
	 findzip api <br/>
	 
	 		<input type="text" name="dong" id="dong" /> 
	 		<a href="#" id="btnsearch1" >search</a>
	 		
				 <table border="1" cellpadding="0" cellspacing="1">

						  <thead>
		    				<tr>
		    					<th>zipcode</th>
		    					<th>address</th>
						     </tr>
						 </thead>
						 <tbody id="dataBody">		
						 		<tr><td colspan='2'>no data</td></tr>
						 </tbody>
				   	    </table>

		</form>
		
		
	</body>
</html>