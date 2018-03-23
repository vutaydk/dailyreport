<script>
	$('#table').bootstrapTable({
		searchTimeOut : 0,
		pageSize : 5
	});

	function formatDate(date) {
		date = new Date(date);
		var day = date.getDate();
		var month = date.getMonth() + 1;
		var year = date.getFullYear();

		return day + '/' + month + '/' + year;
	}

	var today = new Date(new Date().getFullYear(), new Date().getMonth(),
			new Date().getDate());
	$('#startAt').datepicker({
		uiLibrary : 'bootstrap',
		iconsLibrary : 'fontawesome',
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
		iconsLibrary : 'fontawesome',
		minDate : function() {
			return $('#startAt').val();
		},
		icons : {
			rightIcon : '<i class="far fa-calendar-alt"></i>'
		}
	});
</script>