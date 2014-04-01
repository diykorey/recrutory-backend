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

	<div class="container-fluid">
		<!-- Container begin -->
		<h2>Dashboard</h2>
		<div id="main_row" class="row-fluid" style="height: 100%">
			<!-- main_row  begin -->
			<div class="col-xs-6" style="height: 100%">
				<div class="row-fluid">
					<!-- Vacancies row begin -->
					<div class="col-xs-3">
						<div class="panel panel-default" draggable="true">
							<div class="panel-heading" style="padding: 2px 2px;">
								&nbsp;
								<div class="label label-success  pull-right">Interview</div>
							</div>
							<div class="panel-body" contenteditable="true">Basic panel example</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" style="padding: 2px 2px;">
								&nbsp;
								<div class="label label-success pull-right">Interview</div>
							</div>
							<div class="panel-body" contenteditable="true">Basic panel example</div>
						</div>
					</div>
					<div class="col-xs-3">
						<div class="panel panel-default">
							<div class="panel-heading" style="padding: 2px 2px;">
								&nbsp;
								<div class="label label-info  pull-right">Offer</div>
							</div>
							<div class="panel-body" contenteditable="true">Basic panel example</div>
						</div>
					</div>
					<div class="col-xs-3">
						<div class="panel panel-default">
							<div class="panel-heading" style="padding: 2px 2px;">
								&nbsp;
								<div class="label label-danger  pull-right">Contact</div>
							</div>
							<div class="panel-body" contenteditable="true">Basic panel example</div>
						</div>
					</div>
					<div class="col-xs-3">
						<div class="panel panel-default">
							<div class="panel-heading" style="padding: 2px 2px;">
								&nbsp;
								<div class="label label-danger  pull-right">Contact</div>
							</div>
							<div class="panel-body" contenteditable="true">Basic panel example</div>
						</div>
					</div>
					<!-- Vacancies row end -->
				</div>
			</div>
			<div class="col-xs-6" style="border-left: 2px solid #EEE; height: 100%">
				<div class="panel panel-default">
					<div class="panel-heading">
						New Vacancy
						<button type="button" class="btn btn-default btn-lg  pull-right">
							<span class="glyphicon glyphicon-star"></span> Star
						</button>
					</div>
					<div class="panel-body">

						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane active" id="vacancyMainTab">
								<form role="form">
									<div class="form-group">
										<label for="vacancyRequirements">Requirements:</label>
										<textarea class="form-control" rows="3"></textarea>
									</div>
									<div class="form-group">
										<label for="vacancyRequirements">Project Description:</label>
										<textarea class="form-control" rows="3"></textarea>
									</div>
								</form>
							</div>
							<div class="tab-pane" id="vacancyFlowsTab">
								<table class="table table-hover table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>First Name</th>
											<th>Last Name</th>
											<th>Username</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>1</td>
											<td>Mark</td>
											<td>Otto</td>
											<td>@mdo</td>
										</tr>
										<tr>
											<td>2</td>
											<td>Jacob</td>
											<td>Thornton</td>
											<td>@fat</td>
										</tr>
										<tr>
											<td>3</td>
											<td>Larry</td>
											<td>the Bird</td>
											<td>@twitter</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="tab-pane" id="vacancyCommentsTab">3...</div>
							<div class="tab-pane" id="vacancyTaskTab">4...</div>
						</div>
						<nav class="navbar navbar-default" role="navigation">
							<div class="container-fluid">
								<!-- Collect the nav links, forms, and other content for toggling -->
								<div class="collapse navbar-collapse" id="vacancyNavbar">
									<ul class="nav navbar-nav" id="vacancyTabs">
										<li class="active"><a href="#vacancyMainTab" data-toggle="tab">Main</a></li>
										<li><a href="#vacancyFlowsTab" data-toggle="tab">Flows</a></li>
										<li><a href="#vacancyCommentsTab" data-toggle="tab">Comments</a></li>
										<li><a href="#vacancyTaskTab" data-toggle="tab">Task</a></li>
									</ul>
								</div>
								<!-- /.navbar-collapse -->
							</div>
							<!-- /.container-fluid -->
						</nav>


					</div>
				</div>

			</div>
			<!-- main_row  end -->
		</div>
		<!-- Container end -->
	</div>
</body>
</html>