function submit_ajax() {
	var $form = $("form");
	$.ajax({
		url : $form.attr('action'), // url where to submit the request
		type : "POST", // type of action POST || GET
		dataType : 'json', // data type
		data : $form.serialize(), // post data || get data
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
};