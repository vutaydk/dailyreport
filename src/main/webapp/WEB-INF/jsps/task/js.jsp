<script>
	var dataJson = (function() {
		var json = null;
		$.ajax({
			'async' : false,
			'global' : false,
			'url' : "rest/task/get-all",
			'dataType' : "json",
			'success' : function(data) {
				json = data;
				$.each(data, function(i, value) {
					$("#list-bar").append(
							'<li class="list-group-item"><span class="badge badge-secondary">'
									+ value.id + '</span> ' + value.name
									+ '</li>');
				});
			}
		});
		return json;
	})();

	$(function() {
		var $listBar = $("#list-bar").find("li");
		$listBar.click(function() {
			var i = $(this).index();
			$('form').attr('action', "rest/task/edit/" + dataJson[i].id);
			$.each(dataJson[i], function(key, value) {
				$('input[name="txt_' + key + '"]').val(value);
			});
			$listBar.removeClass('active');
			$(this).addClass('active');
		});
	});
</script>