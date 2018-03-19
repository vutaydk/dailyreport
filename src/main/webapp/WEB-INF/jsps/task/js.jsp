<script type="text/javascript">
	function actionFormatter(value, row, index) {
		return [ '<a href="task/edit?id=' + row.id + '">',
				'<i class="fas fa-edit"></i></a>' ].join('');
	}
	function formatDate(date) {
		date = new Date(date);
		var day = date.getDate();
		var month = date.getMonth();
		var year = date.getFullYear();

		return day + '/' + month + '/' + year;
	}

	$(document).ready(function() {
		$("form").submit(function() {
			// send ajax
			$.ajax({
				url : $(this).attr('action'), // url where to submit the request
				type : "POST", // type of action POST || GET
				dataType : 'json', // data type
				data : $(this).serialize(), // post data || get data
				success : function(result) {
					// you can see the result from the console
					// tab of the developer tools
					if (jQuery.isEmptyObject(result) === true) {
						$("button[name='refresh']").click();
						alert('Add success a new task.')
					} else {
						$.each(result, function(index, row) {
							alert(row)
						})
					}
					$("button[name='refresh']").click();
					$('form :input').val('');
				},
				error : function(xhr, resp, text) {
					console.log(xhr, resp, text);
				}
			})
			return false;
		});
	});
</script>