<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>
<div id="paging" class="pagenav">
	<%--
		totalNum : 총 목록 개수
		pageListCnt : 한 페이지에 보여질 목록 개수
		pageGrpCnt : 한 페이지 그룹에 보여질 페이지의 개수
		totalPageCnt : 총 페이지 개수 
		pageIdx : 현재 페이지 번호
		startPage : 시작 페이지 번호
		endPage : 끝 페이지 번호
		
		사용하는 jsp에서 	function goPage(pageIdx){}를 구현
	 --%>
	<c:set var="totalNum" value="${param.totalNum}" />
	<c:set var="pageListCnt" value="${param.pageListCnt}" />
	<c:set var="pageGrpCnt" value="${param.pageGrpCnt}" />
	<c:set var="pageIdx" value="${param.pageIdx}" />
	<fmt:parseNumber var="totalPageCnt" value="${(totalNum / pageListCnt ) + ( totalNum % pageListCnt == 0 ? 0 : 1)}" integerOnly="true" />
	<fmt:parseNumber var="startPage" value="${((pageIdx-1)/pageGrpCnt)}" integerOnly="true" />
	<fmt:parseNumber var="startPage" value="${startPage * pageGrpCnt + 1}" integerOnly="true" />
	
	<c:choose>
		<c:when test="${startPage + pageGrpCnt > totalPageCnt}">
			<c:set var="endPage" value="${totalPageCnt}" />
		</c:when>
		<c:otherwise>
			<c:set var="endPage" value="${startPage + pageGrpCnt - 1}" />
		</c:otherwise>
	</c:choose>
	
	<c:if test="${startPage ne 1}"><%-- 첫 페이지로 --%>
		<span>
			<a href='#' onclick="javascript:goPage(1);">&lt;&lt;</a> <!-- <img src='<c:url value="${dirImages}/btn_prev2.gif" />'> -->
		</span>
	</c:if>
	<c:if test="${startPage - 1 > 0}"><%-- 이전 페이지 블럭으로 --%>
		<span>
			<a href='#' onclick="javascript:goPage(${startPage - 1});">&lt;</a> <!-- <img src='<c:url value="${dirImages}/btn_prev.gif" />'> -->
		</span>
	</c:if>
	
	<c:forEach var="idx" begin="${startPage}" end="${endPage}" step="1">
		<span>
			<a href='#' onclick="javascript:goPage(${idx});" >
				<c:choose>
					<c:when test="${idx eq pageIdx}"><strong><font color="red">${idx}</font></strong></c:when>
					<c:otherwise>${idx}</c:otherwise>
				</c:choose>
			</a>
		</span>
	</c:forEach>
	
	<c:if test="${endPage + 1 <= totalPageCnt}"><%-- 다음 페이지 블럭으로 --%>
		<span>
			<a href='#' onclick="javascript:goPage(${endPage + 1});" >&gt;</a> <!-- <img src='<c:url value="${dirImages}/btn_next.gif" />'> -->
		</span>
	</c:if>
	<c:if test="${endPage ne totalPageCnt}"><%-- 마지막 페이지로 --%>
		<span>
			<a href='#' onclick="javascript:goPage(${totalPageCnt});"  >&gt;&gt;</a> <!-- <img src='<c:url value="${dirImages}/btn_next2.gif" />'> -->
		</span>
	</c:if>
	
	
	
</div>