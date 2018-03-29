<div class="col-3">
	<div class="sidebar p-1">
		<div class="input-group mb-1">
			<input id="search-bar" type="text" class="form-control">
			<div class="input-group-append">
				<span class="input-group-text"> <i class="fas fa-search"></i>
				</span>
			</div>
		</div>
		<ul id="list-bar" class="list-group">
		</ul>
	</div>
</div>
<script>
  $(function() {
    $("#search-bar").keyup(
      function() {
        var filter = locdau($(this).val().replace(/\s+/g, '')); // get search input

        var $listBar = $("#list-bar").find("li");

        // case insensitive searching with animation
        $listBar.hide().filter(
          function() {
            return locdau($(this).text().replace(/\s+/g, '')).search(
              new RegExp(filter, "i")) > -1
          }).stop(true).fadeIn();
      });
  });
</script>
<!-- ./sidebar -->