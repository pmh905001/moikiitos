<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html lang="en">
	<tiles:insertAttribute name="header" />
	<body>
		<section id="main" class="container">
			<sec:authorize access="isAuthenticated()">
			<nav class="navbar">
				<span class="navbar-brand" href="#" style="font-size: 35px;">Moi kiitos</span>
				<ul class="nav navbar-nav navbar-right">
        			<li>
        				<a href="j_spring_security_logout" class="btn btn-default">Logout</a>
        			</li>
		      	</ul>
			</nav>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<h1 align="center">Moi Kiitos</h1>
				<h3 align="center">Welcome</h3>
			</sec:authorize>
			
			<div class="row">
    			<div class="col-md-3"><tiles:insertAttribute name="nav" />
				</div>
   				<div class="col-md-9">
   					<section id="content">
						<tiles:insertAttribute name="body" />
					</section>
				</div>
  			</div>
			
			<tiles:insertAttribute name="footer" />

		</section>
		

	</body>
</html>