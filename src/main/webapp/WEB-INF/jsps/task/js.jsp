<script>
	$('#table').bootstrapTable({
		searchTimeOut : 0,
		pageSize : 5
	});

	$('#table').on('click-row.bs.table', function(item, $element, field) {
		$.each(field, function() {
			$('.text-danger').removeClass('text-danger');
			$('.font-weight-bold').removeClass('font-weight-bold');
			$(this).addClass("text-danger font-weight-bold");
		});
		$('form').attr('action', "rest/task/edit/" + $element.id);
		$.each($element, function(index, row) {
			$('input[name="txt_' + index + '"]').val(row);
			alert(row);
		});
	});

	$(document).ready(function() {
		$("form").submit(function() {
			$.ajax({
				url : $(this).attr('action'), // url where to submit the request
				type : "POST", // type of action POST || GET
				dataType : 'json', // data type
				data : $(this).serialize(), // post data || get data
				success : function(result) {
					if (!jQuery.isEmptyObject(result)) {
						if (!$.isPlainObject(result)) { // message
							alert(result);
							location.reload();
						}

						if ($.isPlainObject(result)) // error validate
							$.each(result, function(key, value) {
								alert(key + ': ' + value);
							})
					}
				},
				error : function() {
					alert("error");
					location.reload();
				}
			})
			return false;
		});
	});
</script>