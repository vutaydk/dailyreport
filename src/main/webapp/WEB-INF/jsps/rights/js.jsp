<script>
	var dataJson = JSON.parse($.getJSON({
		'url' : "rest/rights/get-all",
		'async' : false
	}).responseText);

	$.each(dataJson, function(i, value) {
		$("#list").append(
				'<li class="list-group-item"><span class="badge badge-secondary">'
						+ value.id + '</span> ' + value.name + '</li>');
	});

	$(function() {
		var $listItem = $("#list").find("li");
		$listItem.click(function() {
			var i = $(this).index();
			$('form').attr('action', "rest/rights/edit/" + dataJson[i].id);
			$.each(dataJson[i], function(key, value) {
				$('input[name="txt_' + key + '"]').val(value);
			});
			$listItem.removeClass('active');
			$(this).addClass('active');
		});

		$("#search").keyup(function() {
			var filter;
			filter = $("#search").val().toLowerCase(); // get search input

			// Just a shorter version
			$listItem.hide().has(':contains(' + filter + ')').show();

			// case insensitive searching with animation
			$listItem.slideUp().filter(function() {
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