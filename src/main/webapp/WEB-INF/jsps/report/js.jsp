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

	var arrayName = {};
	var dataJson = (function() {
		var json = null;
		$.ajax({
			'async' : false,
			'global' : false,
			'url' : "rest/report/get-all",
			'dataType' : "json",
			'success' : function(data) {
				json = data;
				$.each(data, function(i, value) {
					arrayName[value.employeeCode] = value.employeeName;
				});
			}
		});
		return json;
	})();

	$(function() {
		$("#employeeSearch").keyup(function() {
			var filter = {}, val = locdau($(this).val().replace(/\s+/g, ''));
			$.each(arrayName, function(key, value) {
				if (locdau(value.replace(/\s+/g, '')) === val) {
					filter = {
						employeeCode : key
					};
					return false;
				}
			});
			setTimeout(function() {
				$("#table").bootstrapTable('filterBy', filter)
			}, 100);
		});

		$("#startAt").change(function() {
			var filter = {}, val = $(this).val();
			if (!jQuery.isEmptyObject(val)) {
				filter = {
					date : val
				};
			}
			$("table").bootstrapTable('filterBy', filter);
		});
	});
</script>