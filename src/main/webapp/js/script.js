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

var Pagination = function(arr) {
  console.log(arr)
  var DOM = {
    sidebarPagination : $(".sidebar-pagination"),
    sidebarList : $(".sidebar-list").find("ul"),
    prev : $("#prev"),
    next : $("#next"),
    first : $("#first"),
    last : $("#last"),
    pageSelect : $("#page-select")
  };

  var rowsOfPage = 10, currentPage = 1;
  var data = [];
  if ($.isArray(arr)) {
    console.log("isArr")
    data = $.extend(data, arr);
    console.log(data)
  }

  function refresh() {
    var totalPage = Math.ceil(data.length / rowsOfPage);
    var firstRow = (currentPage - 1) * rowsOfPage;
    var lastRow = (rowsOfPage * currentPage) - 1;
    if (lastRow > data.length)
      lastRow = data.length - 1;
    console.log(totalPage)
    console.log(firstRow)
    console.log(lastRow)
    if (totalPage <= 1) {
      DOM.sidebarPagination.hide()
    } else {
      DOM.sidebarPagination.show()
    }

    if (currentPage == 1) {
      DOM.first.addClass("disabled");
      DOM.prev.addClass("disabled")
    } else {
      DOM.first.removeClass("disabled");
      DOM.prev.removeClass("disabled")
    }
    if (currentPage == totalPage) {
      DOM.next.addClass("disabled");
      DOM.last.addClass("disabled")
    } else {
      DOM.next.removeClass("disabled");
      DOM.last.removeClass("disabled")
    }
    console.log(lastRow)
    console.log("for")
    DOM.sidebarList.empty();
    var listItems = '';
    for (i = firstRow; i <= lastRow; i++) {
      listItems += `<li class="list-group-item">${data[i].name}</li>`;
      console.log(data[i].name)
    }
    DOM.sidebarList.append(listItems);

    DOM.pageSelect.empty();
    var selectItems = '';
    for (i = 1; i <= totalPage; i++) {
      selectItems += `<option value="${i}" >${i}</option>`;
    }
    DOM.pageSelect.append(selectItems);

    DOM.pageSelect.children("option").removeAttr("selected");
    DOM.pageSelect.children("option[value='" + currentPage + "']").attr(
      "selected", "selected");
  }

  function clickEvent() {
    DOM.first.click(function(e) {
      e.preventDefault();
      currentPage = 1;
      refresh();
    });
    DOM.prev.click(function(e) {
      e.preventDefault();
      if (currentPage > 1)
        currentPage--;
      refresh();
    });
    DOM.next.click(function(e) {
      e.preventDefault();
      if (currentPage < data.lenght)
        currentPage++;
      refresh();
    });
    DOM.last.click(function(e) {
      e.preventDefault();
      config.currentPage = data.lenght;
      refresh();
    });
    DOM.pageSelect.change(function(e) {
      e.preventDefault();
      var optionSelected = $("option:selected", this);
      var valueSelected = this.value;
      currentPage = valueSelected;
      refresh();
    })
  }

  refresh();
  clickEvent();

  return this;
};

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
        $("form").find("input").removeClass("is-invalid").tooltip('dispose');
        $.each(data, function(key, value) {
          $("input[name='" + key + "']").addClass("is-invalid").tooltip({
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
  str = str.replace(/^\-+|\-+$/g, ""); // cắt bỏ ký tự - ở đầu và cuối chuỗi
  return str;
}