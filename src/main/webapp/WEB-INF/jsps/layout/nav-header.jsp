<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg header">
	<a class="navbar-brand" href=""><b>Daily report</b></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse justify-content-end"
		id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"><c:out
						value="${user.name}" default="Admin" /> ( <c:out
						value="${user.rights.name}" default="Employee" /> )</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="logout">Logout</a>
				</div></li>
		</ul>
	</div>
</nav>
<!-- /.nav header -->

<nav class="navbar navbar-expand-lg  border mb-3 bg-white">
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="report">Personal
					report <span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="report/add">Report
					Add</a></li>
			<li class="nav-item"><a class="nav-link" href="project">Project
					managerment</a></li>
			<li class="nav-item"><a class="nav-link" href="task">Tasks</a></li>
			<li class="nav-item"><a class="nav-link" href="rights">Rights</a></li>
		</ul>
	</div>
</nav>
<!-- /.menu -->