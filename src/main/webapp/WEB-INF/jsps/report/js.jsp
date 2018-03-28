<script>
	$('#startAt').change(function() {
		$data = $(this).val();
		$('#finishAt').datepicker('setStartDate', $data);
	});

	$('#table').bootstrapTable({
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

	var dataJson = JSON.parse($.getJSON({
		'url' : "rest/report/get-all",
		'async' : false
	}).responseText);

	var arrayName = [];
	$.each(dataJson, function(key, value) {
		if ($.inArray(value.employeeName, arrayName) === -1) {
			arrayName.push(value.employeeName);
		}
	});

	$(function() {
		/* $("#employeeSearch")
				.keyup(
						function() {
							var $searchInput, $resultContainer;

							$searchInput = $("#employeeSearch").val()
									.toLowerCase(); // get search input
							$resultContainer = $("div").find(".result");
							$resultContainer.html("");

							$(arrayName)
									.each(
											function(key, value) {
												if (value.toLowerCase().search(
														$searchInput) != -1) {
													$resultContainer
															.append('<li class="list-group-item list-group-item-action">'
																	+ value
																	+ '</li>');
												}
											});
						});

		$('.result').on('click', 'li', function() {
			$(".result").html("");
			var itemValue = $(this).text();
			$("#employeeSearch").val(itemValue);
			$("#table").bootstrapTable('filterBy', {
				employeeName : [ itemValue ]
			});
		}); */

		$('#employeeSearch').keyup(function() {
			var filter = {}, val = $(this).val();
			if (!jQuery.isEmptyObject(val)) {
				filter = {
					employeeName : val
				};
			}
			$('#table').bootstrapTable('filterBy', filter);
		});

		$('#startAt').change(function() {
			var filter = {}, val = $(this).val();
			if (!jQuery.isEmptyObject(val)) {
				filter = {
					date : val
				};
			}
			$('table').bootstrapTable('filterBy', filter);
		});
	});
</script>