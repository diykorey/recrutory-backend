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
<style>
#sortable {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 450px;
}

#sortable li {
	margin: 3px 3px 3px 0;
	padding: 1px;
	float: left;
	width: 100px;
	height: 90px;
	font-size: 4em;
	text-align: center;
}
</style>
<script>
	$(function() {
		$("#sortable").sortable();
		$("#sortable").disableSelection();
	});
</script>
</head>
<body>
	<h1>Welcome page</h1>
	<div class="btn-group" data-toggle="buttons">
		<label class="btn btn-primary"> <input type="checkbox"> Option 1
		</label> <label class="btn btn-primary"> <input type="checkbox"> Option 2
		</label> <label class="btn btn-primary"> <input type="checkbox"> Option 3
		</label>
	</div>
	<ul id="sortable">
		<li class="ui-state-default">1</li>
		<li class="ui-state-default">2</li>
		<li class="ui-state-default">3</li>
		<li class="ui-state-default">4</li>
		<li class="ui-state-default">5</li>
		<li class="ui-state-default">6</li>
		<li class="ui-state-default">7</li>
		<li class="ui-state-default">8</li>
		<li class="ui-state-default">9</li>
		<li class="ui-state-default">10</li>
		<li class="ui-state-default">11</li>
		<li class="ui-state-default">12</li>
	</ul>
</body>
</html>