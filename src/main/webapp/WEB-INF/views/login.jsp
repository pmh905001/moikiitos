<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<section id="login">
	
		<c:if test="${not empty message}">
		<div class="success">
			<c:out value="${message}"/>
		</div>
		</c:if>
		
		<form action="j_spring_security_check" method="post" role="form">
		
			<div class="form-group">
				<input type="text" id="username" name="j_username" maxlength="20" class="form-control" placeholder="Name/Email"/>
			</div>
			
			<div class="form-group">
				<input type="password" id="password" name="j_password" maxlength="80" class="form-control" placeholder="Password"/>
			</div>
			
			<input type="submit" value="Sign In" class="btn btn-primary" style="width: 100%"/>
		</form>
		
		<c:if test="${empty message && not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
		<div class="text-danger">
			Your login attempt was not successful, please try again.  
			<c:out value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}"/>
		</div>
		</c:if>
		
		<br/>
		<p>
			If you don't currently have an account, you can <a href="register">register a new username</a>.
		</p>
		
	</section>
