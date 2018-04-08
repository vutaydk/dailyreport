<script>
  var dataJson;
  $.getJSON("api/task/get-all").done().always(function (data) {
    dataJson = data;
    $("#search").keyup(function () {
      search(this, data);
    });
    pagination(data);
  });

  var submitForm = $("#submit-form");
  $("body").on("click", ".list-group-item", function () {
    var i = $(this).attr("id");
    submitForm.attr('action', "api/task/edit/" + i);
    $.each(dataJson[i - 1], function (key, value) {
      $('input[name="' + key + '"]').val(value);
    });
    $("#list-bar").find("li").removeClass('active');
    $(this).addClass('active');
  });

  submitForm.submit(function () {
    console.table($(this).serializeFormJSON());
    var data = {
      taskCode: $(this).find("input[name='taskCode']").val(),
      name: $(this).find("input[name='name']").val()
    };
    submit_ajax("api/task/add", data);
    return false;
  })

</script>