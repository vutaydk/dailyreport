<script>
	$('#report-list-table').bootstrapTable({
		searchTimeOut : 0,
		pageSize : 5,
		search : true,
		showToggle : true,
		showRefresh : true,
		showColumns : true,
		sortOrder : 'desc',
		pagination : true,
		iconsPrefix : 'fa',
		icons : {
			toggle : 'fas fa-list-alt',
			refresh : 'fas fa-sync',
			paginationSwitchUp : 'fas fa-sort-up',
			paginationSwitchDown : 'fas fa-sort-down',
		}
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