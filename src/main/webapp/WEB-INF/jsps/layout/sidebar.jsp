<div class="col-3">
	<div class="sidebar p-1">
		<div class="input-group mb-1">
			<input id="search-bar" type="text" class="form-control">
			<div class="input-group-prepend">
				<div class="input-group-text">
					<button class="btn btn-default" type="button">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</div>
		<ul id="list-bar" class="list-group">
		</ul>
	</div>
</div>
<script>
	$(function() {
		$("#search-bar").keyup(function() {
			var filter = $(this).val().toLowerCase(); // get search input

			var $listBar = $("#list-bar").find("li");

			// Just a shorter version
			$listBar.hide().has(':contains(' + filter + ')').show();

			// case insensitive searching with animation
			$listBar.slideUp().filter(function() {
				return $(this).text().toLowerCase().indexOf(filter) > -1
			}).stop(true).fadeIn();
		});
	});
</script>
<!-- ./sidebar -->