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

	$(document).ready(function() {
		$("form").submit(function() {
			$.ajax({
				url : $(this).attr('action'), // url where to submit the request
				type : "POST", // type of action POST || GET
				dataType : 'json', // data type
				data : $(this).serialize(), // post data || get data
				success : function(result) {
					if (jQuery.isEmptyObject(result) === false) {

						if (jQuery.isEmptyObject(result.message) === false) { // message success
							alert(result.message);
							$('form :input').val('');
						}

						if (jQuery.isEmptyObject(result.error) === false) // error validate
							$.each(result.error, function(key, value) {
								alert(key + ': ' + value);
							})
					}
				},
				error : function() {
					alert("error");
				}
			})
			return false;
		});
	});
</script>