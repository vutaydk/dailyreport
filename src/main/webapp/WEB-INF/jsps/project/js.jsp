<script>
	$("#startAt").change(function() {
		$data = $(this).val();
		$("#finishAt").datepicker('setStartDate', $data);
	});

	$.getJSON("api/project/get-all", {
		'async' : true,
	}).done(
			function(data) {
				$.each(data, function(i, value) {
					$("#list-bar").append(
							'<li class="list-group-item"><span class="badge badge-secondary">'
									+ value.id + '</span> ' + value.name
									+ '</li>');
				});
				var $listBar = $("#list-bar").find("li");
				$listBar
						.click(function() {
							var i = $(this).index();
							$("form").attr('action',
									"api/project/edit/" + data[i].id);
							$.each(data[i], function(key, value) {
								$('input[name="txt_' + key + '"]').val(value);
							});
							$listBar.removeClass('active');
							$(this).addClass('active');
						});
			});
</script>