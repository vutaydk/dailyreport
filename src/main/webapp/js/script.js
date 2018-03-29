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

function formatDate(date) {
	date = new Date(date);
	var day = date.getDate();
	var month = date.getMonth() + 1;
	var year = date.getFullYear();

	return day + '/' + month + '/' + year;
}

function locdau(value) {
	// code by Minit - www.canthoit.info - 13-05-2009
	var str = value;
	str = str.toLowerCase();
	str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
	str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
	str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
	str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
	str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
	str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
	str = str.replace(/đ/g, "d");
	str = str
			.replace(
					/!|@|\$|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\'| |\"|\&|\#|\[|\]|~/g,
					"");
	str = str.replace(/-+-/g, ""); // thay thế 2- thành 1-
	str = str.replace(/^\-+|\-+$/g, "");// cắt bỏ ký tự - ở đầu và cuối chuỗi
	return str;
}
