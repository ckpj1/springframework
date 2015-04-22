<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Pragma","no-cache");			// HTTP1.0 캐쉬 방지
	response.setDateHeader("Expires",0);				// proxy 서버의 캐쉬 방지
	response.setHeader("Pragma", "no-store");			// HTTP1.1 캐쉬 방지
	if(request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");// HTTP1.1 캐쉬 방지
%>
<%@ page contentType = "text/html;charset=ksc5601"%>
<html>
<head>
<script type="text/javascript">
	<c:if test="${not empty errMsg}">
		alert("${errMsg}");
		self.close();
	</c:if>
	function end() {
		parent.opener.parent.document.frm_reg.rec_cert.value = '${rec_cert}';
		parent.opener.parent.document.frm_reg.certNum.value = '${certNum}';
		parent.opener.parent.document.getElementById("cellphone").value = '${cellphone}';
		parent.opener.parent.document.getElementById("label_cellphone").innerText = '${cellphone}';
		self.close();
	}
</script>
</head>

<body onload="javascript:end()">
</body>

</html>