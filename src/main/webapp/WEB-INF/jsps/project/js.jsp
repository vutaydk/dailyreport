<script>
	var today = new Date();
	$('#startAt').datepicker({
		uiLibrary : 'bootstrap',
		format : 'dd/mm/yyyy',
		minDate : today,
		maxDate : function() {
			return $('#finishAt').val();
		},
		icons : {
			rightIcon : '<i class="far fa-calendar-alt"></i>'
		}
	});

	$('#finishAt').datepicker({
		uiLibrary : 'bootstrap',
		format : 'dd/mm/yyyy',
		minDate : function() {
			return $('#startAt').val();
		},
		icons : {
			rightIcon : '<i class="far fa-calendar-alt"></i>'
		}
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
			$(this).removeClass('active');
			$(this).addClass('active');
		});
	});
</script>