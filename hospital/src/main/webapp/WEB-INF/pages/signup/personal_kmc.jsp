<%
	response.setHeader("Pragma","no-cache");			// HTTP1.0 캐쉬 방지
	response.setDateHeader("Expires",0);				// proxy 서버의 캐쉬 방지
	response.setHeader("Pragma", "no-store");			// HTTP1.1 캐쉬 방지
	if(request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");// HTTP1.1 캐쉬 방지
%>

<%@ page contentType = "text/html;charset=ksc5601" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String rec_cert = request.getParameter("rec_cert").trim();
	String certNum  = request.getParameter("certNum").trim();
	
	String next_url = "";
	if( session.getAttribute("next_url") != null )
		next_url = ((String)session.getAttribute("next_url")).trim();
%>
<html>
<head>
<script type="text/javascript">
	function end() {
		
		parent.opener.parent.document.form_kmc.rec_cert.value = '<%=rec_cert%>';
		parent.opener.parent.document.form_kmc.certNum.value = '<%=certNum%>';
		<% if( next_url.compareTo("") != 0 ) {%>
			parent.opener.parent.document.form_kmc.target = 'Parent_window';
			parent.opener.parent.document.form_kmc.action = '<%=next_url%>';
			parent.opener.parent.document.form_kmc.submit();
		<%}%>
		self.close();
	}
</script>
</head>

<body onload="javascript:end()">
</body>

</html>