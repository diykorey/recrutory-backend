<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script src="<c:url value='/resources/jquery/jquery-2.1.0.min.js' />"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/css/bootstrap.min.css' />">

<!-- Optional theme -->
<link rel="stylesheet" href="<c:url value='/resources/bootstrap/css/bootstrap-theme.min.css' />">

<!-- Latest compiled and minified JavaScript -->
<script src="<c:url value='/resources/bootstrap/js/bootstrap.min.js' />"></script>
</head>
<body>
	<h1>Welcome page</h1>
	<div class="btn-group" data-toggle="buttons">
		<label class="btn btn-primary"> <input type="checkbox"> Option 1
		</label> <label class="btn btn-primary"> <input type="checkbox"> Option 2
		</label> <label class="btn btn-primary"> <input type="checkbox"> Option 3
		</label>
	</div>
</body>
</html>