<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setHeader("Pragma","no-cache");			// HTTP1.0 ĳ�� ����
	response.setDateHeader("Expires",0);				// proxy ������ ĳ�� ����
	response.setHeader("Pragma", "no-store");			// HTTP1.1 ĳ�� ����
	if(request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");// HTTP1.1 ĳ�� ����
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