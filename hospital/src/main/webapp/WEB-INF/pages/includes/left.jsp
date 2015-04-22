<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/includes/taglibs.jspf" %>
<%@ include file="/WEB-INF/pages/includes/variables.jspf" %>


		<!-- lnb -->		
		<div id="lnb" class="sidebar">
				<h2>
					
					
					<c:choose>
						<c:when test="${menuid eq '05'}">
							<img src='<c:url value="${dirImages}/common/lnb_introduce.png" />' alt="GameKOK 소개"/>
						</c:when>
						<c:when test="${menuid eq '04'}">
							<img src='<c:url value="${dirImages}/common/lnb_download.png" />' alt="다운로드"/>
						</c:when>
						<c:when test="${menuid eq '03'}">
							<img src='<c:url value="${dirImages}/common/lnb_admin.png" />' alt="매장관리"/>
						</c:when>
						<c:when test="${menuid eq '02'}">
							<img src='<c:url value="${dirImages}/common/lnb_community.png" />' alt="커뮤니티"/>
						</c:when>
						<c:when test="${menuid eq '01'}">
							<img src='<c:url value="${dirImages}/common/lnb_member.png" />' alt="회원가입"/>
						</c:when>																																																										
						<c:otherwise>	
							<img src='<c:url value="${dirImages}/common/lnb_introduce.png" />' alt="GameKOK 소개"/>
						</c:otherwise>
					</c:choose>
					
				</h2>
				<ul class="menu">
					
					<c:forEach items="${leftMenu}" var="menu">					
						<c:choose>
							<c:when test="${menu.parent_code eq 'root' && menu.child_yn eq 'N'}">
								<li><a href="<c:url value="${menu.menu_link}" />">${menu.menu_name }</a></li>
							</c:when>
							<c:otherwise>																
									
									<c:if test="${menu.menu_code eq '0502'}">
									<li><a href="<c:url value="${menu.menu_link}" />">${menu.menu_name }</a>
										<ul>
											<c:forEach items="${leftMenu}" var="subMenu">
												<c:if test="${subMenu.parent_code ne 'root' && menu.menu_code eq subMenu.parent_code }">
													<li><a href="<c:url value="${subMenu.menu_link}" />">${subMenu.menu_name }</a></li>
												</c:if>			
											</c:forEach>				
										</ul>									
									</li>
									</c:if>
							</c:otherwise>
						</c:choose>				
					</c:forEach>	
								
				</ul>
		</div>

			
		<!-- //lnb -->

		
	
	