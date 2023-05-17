<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

		<c:set var="myUsername">
			<sec:authentication property="principal.username" />
		</c:set>
		
		<c:choose>
		<c:when test="${not empty users}">

		<table id="userSearchResults" class="table table-striped">
			<thead>
				<tr>
					<th colspan="2"> User Name  </th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}">

				<tr>
					<td> <a href="posts?username=<c:out value='${user}'/>"> <c:out value="${user}"/></a></td>
					<td>
						<c:set var="isFollowed">
							<spring:eval expression="following.contains(user)" />
						</c:set>

						<c:choose>
						<c:when test="${user == myUsername}">
							This is you!
						</c:when>
						<c:when test="${isFollowed}">
					        <form method="POST" action="unfollow" role="form">
					        	<input type="hidden" name="usernameToUnfollow" value="<c:out value="${user}"/>"/>
					        	<input type="submit" value="Unfollow" class="btn btn-default"/>
					        </form>
				        </c:when>
				        <c:otherwise>
					        <form method="POST" action="follow" role="form">
					        	<input type="hidden" name="usernameToFollow" value="<c:out value="${user}"/>"/>
					        	<input type="submit" value="Follow" class="btn btn-default"/>
					        </form>
				        </c:otherwise>
				        </c:choose>
				    </td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
		<p>No users were found.</p>
		</c:otherwise>
		</c:choose>
