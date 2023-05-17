<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <table id="following" class="table table-striped">
        	<thead>
        		<tr>
        			<th colspan="2">Followers</th>
        		</tr>
        	</thead>
			<tbody>
				<c:forEach var="follow" items="${followers}">
				<tr>
					<td><c:out value="${follow.username}"/></td>
					<td><c:out value="${follow.email}"/></td>
					<td>
				        <form method="POST" action="follow" role="form">
				        	<input type="hidden" name="usernameTofollow" value="<c:out value="${follow.username}"/>"/>
				        	<input type="submit" value="Follow" class="btn btn-primary"/>
				        </form>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
