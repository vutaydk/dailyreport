<script>
	$('#startAt').change(function() {
		$data = $(this).val();
		$('#finishAt').datepicker('setStartDate', $data);
	});

	var dataJson = JSON.parse($.getJSON({
		'url' : "rest/project/get-all",
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
			$('form').attr('action', "rest/project/edit/" + dataJson[i].id);
			$.each(dataJson[i], function(key, value) {
				$('input[name="txt_' + key + '"]').val(value);
			});
			$listBar.removeClass('active');
			$(this).addClass('active');
		});
	});
</script>