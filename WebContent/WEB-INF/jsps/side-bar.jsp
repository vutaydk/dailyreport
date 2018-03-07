<ul class="nav flex-column">
	<li class="nav-item"><a class="nav-link active" href="#">Create
			report</a></li>
	<li class="nav-item">
		<div class="dropdown-divider"></div>
	</li>
	<li class="nav-item">
		<div>
			<input id="datepicker" />
			<script>
				var currentDate = new Date().toLocaleDateString('en-US');
				$('#datepicker').datepicker({
					value : currentDate
				});
			</script>
		</div>
	</li>
</ul>