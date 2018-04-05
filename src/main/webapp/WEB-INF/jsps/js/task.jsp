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
    var form = $("form");
    form.attr('action', "api/task/edit/" + i);
    if(form.find("input[name='id']")){
      console.log("find");
    }
    // $("form")..append(`
    // <input type="hidden" maxlength="1" name="id"
		// 							class="form-control" value="${id}">
    // `);
    $.each(dataJson[i - 1], function (key, value) {
      $('input[name="' + key + '"]').val(value);
    });
    $("#list-bar").find("li").removeClass('active');
    $(this).addClass('active');
  });

  $("#submit-form").submit(function(event){
    console.log($(this).serializeFormJSON());
    event.preventDefault();
  })

</script>