<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<br/>
<br/>
		<c:choose>
		<c:when test="${not empty posts && not empty posts.content}">
		
		<c:set var="myUsername"><sec:authentication property="principal.username" /></c:set>
		
		<c:forEach var="post" items="${posts.content}">
			<br/>
			<div class="input-group input-group-lg">
				<c:choose>
				<c:when test="${post.blogUser.username == myUsername }">
					<span class="form-control"  aria-describedby="sizing-addon1" style="overflow: hidden;" title="<c:out value='${post.message}'/>"><c:out value='${post.message}'/></span><span class="input-group-addon" id="sizing-addon1"><c:out value="${post.blogUser.username}"/></span>
				</c:when>
				<c:otherwise>
	  				<span class="input-group-addon" id="sizing-addon1"><c:out value="${post.blogUser.username}"/></span><span class="form-control"  aria-describedby="sizing-addon1" style="overflow: hidden;" title="<c:out value='${post.message}'/>"><c:out value='${post.message}'/></span>
				</c:otherwise>
				</c:choose>
			</div>
		</c:forEach>
		
<jsp:include page="paging.jsp" />
		</c:when>
		<c:otherwise>
		No posts were found.
		</c:otherwise>
		</c:choose>