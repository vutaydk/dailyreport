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

function search(input, arr) {
  var filter = locdau($(input).val().replace(/\s+/g, ''));
  var result = arr.filter(item => {
    var name = locdau(item.name.replace(/\s+/g, ''));
    return name.indexOf(filter) !== -1;
  });
  pagination(result);
}

function pagination(arr) {
  var s = {
    sidebarPagination: $(".sidebar-pagination"),
    sidebarList: $(".sidebar-list").find("ul"),
    prev: $("#prev"),
    next: $("#next"),
    first: $("#first"),
    last: $("#last"),
    pageSelect: $("#page-select"),
    search: $("#search")
  };

  var data = [];
  if ($.isArray(arr)) {
    data = $.extend(data, arr);
  }
  var rowsOfPage = 2,
    currentPage = 1,
    totalPage = Math.ceil(data.length / rowsOfPage);

  s.pageSelect.empty();
  var selectItems = '';
  for (i = 1; i <= totalPage; i++) {
    selectItems += `<option value="${i}" >${i}</option>`;
  }
  s.pageSelect.append(selectItems);

  function refresh() {
    var firstRow = (currentPage - 1) * rowsOfPage;
    var lastRow = rowsOfPage * currentPage;
    if (lastRow > data.length)
      lastRow = data.length;

    if (totalPage <= 1) {
      s.sidebarPagination.hide()
    } else {
      s.sidebarPagination.show()
    }

    if (currentPage == 1) {
      [s.first, s.prev].map(b => b.addClass("disabled"));
    } else {
      [s.first, s.prev].map(b => b.removeClass("disabled"));
    }
    if (currentPage == totalPage) {
      [s.next, s.last].map(b => b.addClass("disabled"));
    } else {
      [s.next, s.last].map(b => b.removeClass("disabled"));
    }

    s.sidebarList.empty();
    var listItems = '';
    for (i = firstRow; i < lastRow; i++) {
      listItems += `<li id="${data[i].id}" class="list-group-item">${data[i].name}</li>`;
    }
    s.sidebarList.append(listItems);

    s.pageSelect.children("option").removeAttr("selected");
    s.pageSelect.children("option[value='" + currentPage + "']").attr(
      "selected", "selected");
  }

  function clickEvent() {
    s.first.click(function (e) {
      e.preventDefault();
      currentPage = 1;
      refresh();
    });
    s.prev.click(function (e) {
      e.preventDefault();
      if (currentPage > 1)
        currentPage--;
      refresh();
    });
    s.next.click(function (e) {
      e.preventDefault();
      if (currentPage < totalPage)
        currentPage++;
      refresh();
    });
    s.last.click(function (e) {
      e.preventDefault();
      currentPage = totalPage;
      refresh();
    });
    s.pageSelect.change(function (e) {
      e.preventDefault();
      currentPage = this.value;
      refresh();
    })
  }
  refresh();
  clickEvent();
};

var isProcessing = false;

function submit_ajax(url, data) {
  if (isProcessing)
    return;
  $(document).ajaxSend(function () {
    isProcessing = true;
  });
  var form = $("form");
  // Send the request
  $.ajax({
    type: 'POST',
    url: url,
    contentType: "application/json",
    dataType: 'json',
    data: JSON.stringify(data),
    success: function (response) {
      isProcessing = false;
      var form = $("form");
      if (!$.isEmptyObject(response.messages)) {
        console.table(response.messages);
        form.find("input").removeClass("is-invalid").tooltip('dispose');
        $.each(response.messages, function (key, value) {
          form.find("#" + key).addClass("is-invalid").tooltip({
            title: value,
            placement: 'right',
          }).tooltip('show');
        })
      } else {
        form.find("input").removeClass("is-invalid").tooltip('dispose');
        alert(response.errors.success);
        location.reload();
      }

    },
    error: function (response) {
      alert(response.errors.errror);
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