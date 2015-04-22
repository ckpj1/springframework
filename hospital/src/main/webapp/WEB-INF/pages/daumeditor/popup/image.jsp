<%@ page language="java" %>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8"%>
<!-- 
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
        --> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<script src="<c:url value="${dirScripts}/daumeditor/js/popup.js" />" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="<c:url value="${dirScripts}/daumeditor/css/popup.css" />" type="text/css" charset="utf-8"/>

<script type="text/javascript">
// <![CDATA[
	function upload() {
		if ((document.image_upload.uploadImg.value).match(/\.(bmp|gif|jpg|jpeg|png)$/i)){
			document.image_upload.submit();
		}else{
			alert("이미지 파일만 첨부하실 수 있습니다.");
		}
	}
	
	function done() {
		if (typeof(execAttach) == 'undefined') { //Virtual Function
	        return;
	    }
		
		var _mockdata = {
			'imageurl': '${fullPath}/${realFileName}',
			'filename': '${viewFileName}',
			'filesize': '${fileSize}',
			'imagealign': 'C',
			'originalurl': '${fullPath}/${realFileName}',
			'thumburl': '${fullPath}/${realFileName}'
		};
		execAttach(_mockdata);
		closeWindow();
	}

	function initUploader(){
	    var _opener = PopupUtil.getOpener();
	    if (!_opener) {
	        alert('잘못된 경로로 접근하셨습니다.');
	        return;
	    }
	    
	    var _attacher = getAttacher('image', _opener);
	    registerAction(_attacher);
	}
// ]]>
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Daum에디터 - 이미지 첨부</title>
</head>
<body onload="initUploader();">
<div class="wrapper">
	<div class="header">
		<h1>사진 첨부</h1>
	</div>
	<c:choose>
		<c:when test="${realFileName eq null}">
			<div class="body">
			  	<form name="image_upload" id="image_upload" action="<c:url value="/daumeditor/popup/image.do"/>" method="post" enctype="multipart/form-data" accept-charset="utf-8">
					<dl class="alert">
					    <dt>사진 첨부 하기</dt>
					    <dd>
			    	    	<input type="file" name="uploadImg" id="uploadImg" size="30">
						</dd>
					</dl>
				</form>
			</div>
			<div class="footer">
				<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
				<ul>
					<li class="submit"><a href="#" onclick="upload();" title="업로드" class="btnlink">업로드</a> </li>
					<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
			<div class="body">
				<dl class="alert">
				    <dt>사진 첨부 확인</dt>
				    <dd>
				    	확인을 누르시면 <b>"${viewFileName}"</b> 가 사진첨부 됩니다.<br /> 
					</dd>
				</dl>
			</div>
			<div class="footer">
				<p><a href="#" onclick="closeWindow();" title="닫기" class="close">닫기</a></p>
				<ul>
					<li class="submit"><a href="#" onclick="done();" title="등록" class="btnlink">등록</a> </li>
					<li class="cancel"><a href="#" onclick="closeWindow();" title="취소" class="btnlink">취소</a></li>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>	
</div>

</body>
</html>