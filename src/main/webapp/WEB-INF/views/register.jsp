<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

	<section id="register">
	        
		<form:form method="POST" action="user" commandName="blogUser" role="form" onsubmit="return checkConfirmPassword()">
		
			<spring:bind path="username">
			
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="username" maxlength="20" cssClass="form-control" placeholder='Name'/>
					<form:errors path="username" cssClass="text-danger"></form:errors>
				</div>
				
			</spring:bind>
			
			
			<spring:bind path="email">
			
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="text" path="email" maxlength="20" cssClass="form-control" placeholder="Email"/>
					<form:errors path="email" cssClass="text-danger"></form:errors>
				</div>
				
			</spring:bind>
			
			<spring:bind path="password">
			
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="password" path="password" maxlength="80" cssClass="form-control" placeholder="Password" />
					<form:errors path="password" cssClass="text-danger"></form:errors>
				</div>
				
			</spring:bind>
			
			
			<div class="form-group ">
				<input type="password" name="confirmPassword" id="confirmPassword" maxlength="80" class="form-control" placeholder="Password Confirmation" />
			</div>
			
			
			<input type="submit" value="Register User" class="btn btn-primary" style="width: 100%"/>
			
		</form:form>
		
		<br/>
		<p>
			If you already registered, please <a href="login">Sign in</a>.
		</p>
		
	</section>