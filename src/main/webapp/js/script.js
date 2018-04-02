(function($) {
	$.fn.serializeFormJSON = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};

})(jQuery);

/*Pagination sidebar*/
var Pagination = (function() {
	var rows = [];
	var numberOfRow;
	var rowOfPage = 3;
	var currentPage = 1;
	var firstRow;
	var lastRow;
	var totalPage;

	var config = {
		sidebarPagination : $(".sidebar-pagination"),
		sidebarList : $(".sidebar-list").find("ul"),
		prev : $("#prev"),
		next : $("#next"),
		first : $("#first"),
		last : $("#last"),
		pageSelect : $("#page-select")
	};

	config.prev.click(function(e) {
		e.preventDefault();
		if (currentPage > 1)
			currentPage--;
		pagination();
	});

	config.next.click(function(e) {
		e.preventDefault();
		if (currentPage < totalPage)
			currentPage++;
		pagination();
	});

	config.first.click(function(e) {
		e.preventDefault();
		currentPage = 1;
		pagination();
	});

	config.last.click(function(e) {
		e.preventDefault();
		currentPage = totalPage;
		pagination();
	});

	config.pageSelect.change(function(e) {
		var optionSelected = $("option:selected", this);
		var valueSelected = this.value;
		currentPage = valueSelected;
		pagination();
	});

	var pushData = function(arrayData) {
		/* push data to rows */
		$.each(arrayData, function(key, value) {
			rows.push(value);
			numberOfRow = rows.length;
			totalPage = Math.ceil(numberOfRow / rowOfPage);
		});
		for (i = 1; i <= totalPage; i++) {
			config.pageSelect.append(`<option value="${i}" >${i}</option>`)
		}
		pagination();
	}

	var pagination = function(arrayData) {

		/* hide pagination control if totalPage < 1 */
		if (totalPage <= 1) {
			config.sidebarPagination.hide();
		} else {
			config.sidebarPagination.show();
		}

		firstRow = (currentPage - 1) * rowOfPage;
		lastRow = (rowOfPage * currentPage) - 1;

		config.pageSelect.children("option").removeAttr("selected");
		config.pageSelect.children("option[value='" + currentPage + "']").attr(
				"selected", "selected");

		if (lastRow < numberOfRow) {
			config.sidebarList.empty();
			for (i = firstRow; i <= lastRow; i++) {
				config.sidebarList
						.append(`<li class="list-group-item">${rows[i].name}</li>`);
			}
		}

		/* disable first and prev button */
		if (currentPage == 1) {
			config.prev.addClass("disabled");
			config.first.addClass("disabled");
		} else {
			config.prev.removeClass("disabled");
			config.first.removeClass("disabled");
		}

		/* disable next and last button */
		if (currentPage == totalPage) {
			config.next.addClass("disabled");
			config.last.addClass("disabled");
		} else {
			config.next.removeClass("disabled");
			config.last.removeClass("disabled");
		}
	}

	// Public
	return {
		pushData : pushData
	}
})();

var isProcessing = false;
function submit_ajax() {
	if (isProcessing)
		return;
	$(document).ajaxSend(function() {
		isProcessing = true;
	});
	var form = $("form");
	// Send the request
	$.ajax({
		type : 'POST',
		url : form.attr('action'),
		contentType : "application/json",
		dataType : 'json',
		data : JSON.stringify(form.serializeFormJSON()),
		success : function(data) {
			// data processing
			if (!$.isPlainObject(data)) { // message
				location.reload();
			} else { // errors validate
				$("form").find("input").removeClass("is-invalid").tooltip(
						'dispose');
				$.each(data, function(key, value) {
					$("input[name='" + key + "']").addClass("is-invalid")
							.tooltip({
								title : value,
								placement : 'right',
							}).tooltip('show');
				});
				isProcessing = false;
			}
		},
		error : function() {
			alert("error");
			location.reload();
		}
	});
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
