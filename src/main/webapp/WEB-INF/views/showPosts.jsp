<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

        <form method="GET" action="posts" role="form">
        	<input type="text" id="username" name="username" class="form-control" placeholder="User Name"/>
        	<input type="submit" value="Show Posts" class="btn btn-primary" style="float: right;"/>
        </form>
        
<c:set value="/posts" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
