<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/message.tld" prefix="m"%>
<nav class="navbar navbar-expand-lg header">
	<a class="navbar-brand" href=""><b><m:message
				key="label.brandName" /></b></a>
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
					<a class="dropdown-item" href="logout"><m:message
							key="label.logout" /></a>
				</div></li>
			<li class="nav-item"><select class="form-control"
				onchange="this.options[this.selectedIndex].value && (window.location = this.options[this.selectedIndex].value);">
					<option value="">Language...</option>
					<option value="lang?io=en">English</option>
					<option value="lang?io=vi">Viet Nam</option>
			</select></li>
		</ul>
	</div>
</nav>
<!-- /.nav header -->

<nav class="navbar navbar-expand-lg  border mb-3 bg-white">
	<div class="collapse navbar-collapse">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="report"><m:message
						key="label.project" /></a></li>
			<li class="nav-item"><a class="nav-link" href="report/add"><m:message
						key="label.project.add" /></a></li>
			<li class="nav-item"><a class="nav-link" href="project"><m:message
						key="label.project.manament" /></a></li>
			<li class="nav-item"><a class="nav-link" href="task"><m:message
						key="label.task" /></a></li>
			<li class="nav-item"><a class="nav-link" href="rights"><m:message
						key="label.rights" /></a></li>
		</ul>
	</div>
</nav>
<!-- /.menu -->