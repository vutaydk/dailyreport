<script>
	var dataJson = JSON.parse($.getJSON({
		'url' : "rest/task/get-all",
		'async' : false
	}).responseText);

	$.each(dataJson, function(i, value) {
		$("#list").append(
				'<li class="list-group-item"><span class="badge badge-secondary">'
						+ value.id + '</span> ' + value.name + '</li>');
	});

	$(document).ready(function() {

		$('#list li').click(function() {
			var i = $(this).index();
			$('form').attr('action', "rest/task/edit/" + dataJson[i].id);
			$.each(dataJson[i], function(key, value) {
				$('input[name="txt_' + key + '"]').val(value);
			});
			$('#list li').removeClass('active');
			$(this).addClass('active');
		});

		$("#search").keyup(function() {
			var filter;
			filter = $("#search").val().toLowerCase(); // get search input

			// Just a shorter version
			$('#list li').hide().has(':contains(' + filter + ')').show();

			// case insensitive searching with animation
			$("#list li").slideUp().filter(function() {
				return $(this).text().toLowerCase().indexOf(filter) > -1
			}).stop(true).fadeIn();
		});

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