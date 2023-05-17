<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<form action="findUser" method="post" role="form">
			<div class="form-group">
				<input type="text" id="username" name="username" class="form-control" placeholder="Search by name/email" />
				<input type="submit" value="Search" class="btn btn-primary" style="float: right">
			</div>
			
			<div class="help-block">Note: you can specify a "%" symbol as a wildcard.  For instance, searching on "%drew" will match "andrew" or "drew".</div>
		</form>
