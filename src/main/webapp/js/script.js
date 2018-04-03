(function ($) {
	$.fn.serializeFormJSON = function () {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function () {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
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
var Pagination = (function () {
	var DOM = {};
	var config = {
		/*
		rows,
		numberOfRows,
		rowsOfPage,
		currentPage,
		firstRow,
		lastRow,
		totalPage
		*/
	};

	/*==========cache DOM==========*/
	function cacheDom() {
		console.log("cacheDom()");
		DOM.$sidebarPagination = $(".sidebar-pagination");
		DOM.$sidebarList = $(".sidebar-list").find("ul");
		DOM.$prev = $("#prev");
		DOM.$next = $("#next");
		DOM.$first = $("#first");
		DOM.$last = $("#last");
		DOM.$pageSelect = $("#page-select");
	}

	/*==========config==========*/
	function configPagination(data) {
		console.log("configPagination()");
		config.rows = data;
		config.rowsOfPage = 10;
		config.currentPage = 1;
	}

	/*==========click Event==========*/
	function clickEvent() {
		console.log("clickEvent()");
		DOM.$first.click(function (e) {
			e.preventDefault();
			console.log("first clicked");
			config.currentPage = 1;
			pagination();
		});
		DOM.$prev.click(function (e) {
			e.preventDefault();
			console.log("prev clicked");
			if (config.currentPage > 1)
				config.currentPage--;
			pagination();
		});
		DOM.$next.click(function (e) {
			e.preventDefault();
			console.log("next clicked");
			if (config.currentPage < config.totalPage)
				config.currentPage++;
			pagination();
		});
		DOM.$last.click(function (e) {
			e.preventDefault();
			console.log("last clicked");
			config.currentPage = config.totalPage;
			pagination();
		});
		DOM.$pageSelect.change(function (e) {
			e.preventDefault();
			console.log("page changed");
			var optionSelected = $("option:selected", this);
			var valueSelected = this.value;
			config.currentPage = valueSelected;
			pagination();
		})
	}

	/*==========pagination==========*/
	function pagination() {
		console.log("pagination()");
		/*Set numberOfRow, totalPage, firstRow, lastRow*/
		config.numberOfRows = config.rows.length;
		config.totalPage = Math.ceil(config.numberOfRows / config.rowsOfPage);
		config.firstRow = (config.currentPage - 1) * config.rowsOfPage;
		config.lastRow = (config.rowsOfPage * config.currentPage) - 1;
		if (config.numberOfRows < config.rowsOfPage || config.lastRow > config.numberOfRows)
			config.lastRow = config.numberOfRows;

		/*Hide page control if totalPage <=1*/
		if (config.totalPage <= 1) {
			DOM.$sidebarPagination.hide()
		} else {
			DOM.$sidebarPagination.show()
		}

		/*Disable button at first and last page*/
		if (config.currentPage == 1) {
			DOM.$first.addClass("disabled");
			DOM.$prev.addClass("disabled")
		} else {
			DOM.$first.removeClass("disabled");
			DOM.$prev.removeClass("disabled")
		}
		if (config.currentPage == config.totalPage) {
			DOM.$next.addClass("disabled");
			DOM.$last.addClass("disabled")
		} else {
			DOM.$next.removeClass("disabled");
			DOM.$last.removeClass("disabled")
		}

		/*Fill list*/
		if (config.lastRow < config.numberOfRows) {
			DOM.$sidebarList.empty();
			var listItems = '';
			for (i = config.firstRow; i <= config.lastRow; i++) {
				listItems += `<li class="list-group-item">${config.rows[i].name}</li>`;
			}
			DOM.$sidebarList.append(listItems);
		}

		/*Fill page number*/
		DOM.$pageSelect.empty();
		var selectItems = '';
		for (i = 1; i <= config.totalPage; i++) {
			selectItems += `<option value="${i}" >${i}</option>`;
		}
		DOM.$pageSelect.append(selectItems);

		/*Page selected*/
		DOM.$pageSelect.children("option").removeAttr("selected");
		DOM.$pageSelect.children("option[value='" + config.currentPage + "']").attr("selected", "selected");
	}

	/*==========public method==========*/
	function init() {
		console.log("init()");
		cacheDom();
		clickEvent();
		pagination();
		console.log(config);
	}

	/*==========return==========*/
	return {
		configPagination: configPagination,
		init: init
	}
})();

var isProcessing = false;

function submit_ajax() {
	if (isProcessing)
		return;
	$(document).ajaxSend(function () {
		isProcessing = true;
	});
	var form = $("form");
	// Send the request
	$.ajax({
		type: 'POST',
		url: form.attr('action'),
		contentType: "application/json",
		dataType: 'json',
		data: JSON.stringify(form.serializeFormJSON()),
		success: function (data) {
			// data processing
			if (!$.isPlainObject(data)) { // message
				location.reload();
			} else { // errors validate
				$("form").find("input").removeClass("is-invalid").tooltip(
					'dispose');
				$.each(data, function (key, value) {
					$("input[name='" + key + "']").addClass("is-invalid")
						.tooltip({
							title: value,
							placement: 'right',
						}).tooltip('show');
				});
				isProcessing = false;
			}
		},
		error: function () {
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
	str = str.replace(/^\-+|\-+$/g, ""); // cắt bỏ ký tự - ở đầu và cuối chuỗi
	return str;
}