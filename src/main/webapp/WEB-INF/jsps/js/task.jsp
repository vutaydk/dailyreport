<script>
  var dataJson;
  var submitForm = $("#submit-form");
  var messages = {
    taskCode: "Task code must be 4 characters.",
    name: "Name is too short (requires 6 characters)."
  };

  $.getJSON("api/task/get-all").done().always(function (data) {
    dataJson = data;
    $("#search").keyup(function () {
      search(this, data);
    });
    pagination(data);
  });

  $("body").on("click", ".list-group-item", function () {
    var i = $(this).attr("id");
    submitForm.attr('action', "api/task/edit/" + i);
    $.each(dataJson[i - 1], function (key, value) {
      $('input[name="' + key + '"]').val(value);
    });
    $("#list-bar").find("li").removeClass('active');
    $(this).addClass('active');
  });

  submitForm.validate({
    onkeyup: function (element) {
      $(element).valid()
    },
    highlight: function (element) {
      $(element).addClass("is-invalid").tooltip({
        title: messages[element.id],
        placement: 'right',
      }).tooltip('show');
    },
    unhighlight: function (element) {
      $(element).removeClass("is-invalid").tooltip('dispose');
    },
    rules: {
      taskCode: {
        required: true,
        rangelength: [4, 4]
      },
      name: {
        required: true,
        minlength: 6
      }
    },
    errorPlacement: function (error, element) {},
    submitHandler: function (form) {
      var data = {
        taskCode: $(form).find("#taskCode").val(),
        name: $(form).find("#name").val()
      };
      submit_ajax("api/task/add", data);
      return false;
    }
  });
  
</script>