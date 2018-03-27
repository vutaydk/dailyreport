<div class="col-3">
	<div class="sidebar p-1">
		<div class="input-group mb-1">
			<input id="search" type="text" class="form-control">
			<div class="input-group-prepend">
				<div class="input-group-text">
					<button class="btn btn-default" type="button">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</div>
		<ul id="list" class="list-group">
		</ul>
	</div>
</div>
<script>
	$(function() {
		var $listItem = $("#list").find("li");
		$("#search").keyup(function() {
			var filter = $("#search").val().toLowerCase(); // get search input

			// Just a shorter version
			$listItem.hide().has(':contains(' + filter + ')').show();

			// case insensitive searching with animation
			$listItem.slideUp().filter(function() {
				return $(this).text().toLowerCase().indexOf(filter) > -1
			}).stop(true).fadeIn();
		});
	});
</script>
<!-- ./sidebar -->