<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<nav class="navbar navbar-expand-lg header">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/"><b>Daily
			report</b></a>
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
						value="${sessionScope.user.name}" default="Admin" /> ( <c:out
						value="${sessionScope.user.rights.name}" default="Employee" /> )</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="logout">Logout</a>
				</div></li>
		</ul>
	</div>
</nav>
<!-- /.nav header -->