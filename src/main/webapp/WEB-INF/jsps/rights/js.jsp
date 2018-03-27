<script>
	var dataJson = JSON.parse($.getJSON({
		'url' : "rest/rights/get-all",
		'async' : false
	}).responseText);

	$(function() {
		$.each(dataJson, function(i, value) {
			$("#list-bar").append(
					'<li class="list-group-item"><span class="badge badge-secondary">'
							+ value.id + '</span> ' + value.name + '</li>');
		});

		var $listBar = $("#list-bar").find("li");
		$listBar.click(function() {
			var i = $(this).index();
			$('form').attr('action', "rest/rights/edit/" + dataJson[i].id);
			$.each(dataJson[i], function(key, value) {
				$('input[name="txt_' + key + '"]').val(value);
			});
			$listBar.removeClass('active');
			$(this).addClass('active');
		});
	});
</script>