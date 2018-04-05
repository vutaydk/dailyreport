<script>
  var dataJson;
  $.getJSON("api/task/get-all").done().always(function (data) {
    dataJson = data;
    $("#search").keyup(function () {
      search(this, data);
    });
    pagination(data);
  });

  $("body").on("click", ".list-group-item", function () {
    var i = $(this).attr("id");
    $("form").attr('action', "api/task/edit/" + i);
    $.each(dataJson[i - 1], function (key, value) {
      $('input[name="' + key + '"]').val(value);
    });
    $("#list-bar").find("li").removeClass('active');
    $(this).addClass('active');
  });
</script>