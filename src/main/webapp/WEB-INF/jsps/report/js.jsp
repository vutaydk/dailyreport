<script>
	$("#startAt").change(function() {
		$data = $(this).val();
		$("#finishAt").datepicker('setStartDate', $data);
	});

	$("#table").bootstrapTable({
		searchTimeOut : 0,
		pageSize : 5,
		search : true,
		searchTimeOut : 500,
		showToggle : true,
		showRefresh : true,
		showColumns : true,
		classes : 'table table-no-bordered',
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

	$.getJSON("api/report/get-all", {
		'async' : true,
	}).done(function(data) {
		var arrayName = {};
		$.each(data, function(i, value) {
			arrayName[value.employeeCode] = value.employeeName;
		});

		$employeeSearch = $("#employeeSearch");
		$startAt = $("#startAt");

		function search() {
			var filter = locdau($employeeSearch.val().replace(/\s+/g, ''));
			$filterBy = {};
			$.each(arrayName, function(key, value) {
				if (locdau(value.replace(/\s+/g, '')) === filter) {
					$filterBy['employeeCode'] = key;
					return false;
				}
			});
			var filter2 = $startAt.val();
			if (!jQuery.isEmptyObject(filter2)) {
				$filterBy['date'] = filter2;
			}
			setTimeout(function() {
				$("#table").bootstrapTable('filterBy', $filterBy)
			}, 100);
		}
		$employeeSearch.keyup(function() {
			search();
		});

		$startAt.change(function() {
			search();
		});
	});
</script>