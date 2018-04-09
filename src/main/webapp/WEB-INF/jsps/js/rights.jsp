<script>
  var dataJson;

  $.getJSON("api/rights/get-all").done().always(function (data) {
    dataJson = data;
    $("#search").keyup(function () {
      search(this, data);
    });
    pagination(data);
  });

  var submitForm = $("#submit-form");
  $("body").on("click", ".list-group-item", function () {
    var i = $(this).attr("id");
    submitForm.attr('action', "api/rights/edit/" + i);
    $.each(dataJson[i - 1], function (key, value) {
      $('#' + key).val(value);
    });
    $("#list-bar").find("li").removeClass('active');
    $(this).addClass('active');
  });

  submitForm.submit(function () {
    console.table($(this).serializeFormJSON());
    var data = {
      name: $(this).find("#name").val(),
      level: $(this).find("#level").val()
    };
    submit_ajax("api/rights/add", data);
    return false;
  })
</script>