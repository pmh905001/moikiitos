<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <table id="following" class="table table-striped">
        	<thead>
        		<tr>
        			<th colspan="2">Following</th>
        		</tr>
        	</thead>
			<tbody>
				<c:forEach var="follow" items="${following}">
				<tr>
					<td><c:out value="${follow.username}"/></td>
					<td><c:out value="${follow.email}"/></td>
					<td>
				        <form method="POST" action="unfollow" role="form">
				        	<input type="hidden" name="usernameToUnfollow" value="<c:out value="${follow.username}"/>"/>
				        	<input type="submit" value="Unfollow" class="btn btn-primary"/>
				        </form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
