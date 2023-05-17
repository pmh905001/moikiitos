<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

        <form:form method="POST" action="./" commandName="post" role="form">
        	
        	<div class="form-group">
	        	<form:textarea id="createMessage" path="message" class="form-control" placeholder="Type something here..."></form:textarea>
	        	<div><form:errors path="message" cssClass="text-danger"></form:errors></div>
        	</div>
        	
        	<div id="counter"></div>
        	
        	<input type="submit" value="Post" class="btn btn-primary" style="float: right" />
        	
        	
        </form:form>
        <div class="text-success"><c:out value="${message}"/></div>

<c:set value="/" var="screenPath"/>
<jsp:include page="template/posts.jsp" />
