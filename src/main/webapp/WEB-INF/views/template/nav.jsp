<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
		<sec:authorize access="isAuthenticated()">
			
			<ul class="nav nav-pills nav-stacked">
				<li id="navCreatePost"><a href="./"><sec:authentication property="principal.username" /></a></li>
				<li id="navFollowing"><a href="following">Following <span class="badge"><c:out value="${followingNumber}"/></span> </a>  </li>
				<li id="navFollowers"><a href="followers">Followers <span class="badge"><c:out value="${followersNumber}"/></span> </a>  </li>
				<li id="navFindUser"><a href="findUser">Find Users</a></li>
				<li id="navPosts"><a href="posts">Show Posts</a></li>
			</ul>
			
		</sec:authorize>