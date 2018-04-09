<script>
  var dataJson;
  var submitForm = $("#submit-form");
  var messages = {
    name: "Name is too short (requires 6 characters).",
    level: "Level must be a digit."
  };

  $.getJSON("api/rights/get-all").done().always(function (data) {
    dataJson = data;
    $("#search").keyup(function () {
      search(this, data);
    });
    pagination(data);
  });

  $("body").on("click", ".list-group-item", function () {
    var i = $(this).attr("id");
    submitForm.attr('action', "api/rights/edit/" + i);
    $.each(dataJson[i - 1], function (key, value) {
      $('#' + key).val(value);
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
      name: {
        required: true,
        minlength: 6
      },
      level: {
        required: false,
        digits: true
      }
    },
    errorPlacement: function (error, element) {},
    submitHandler: function (form) {
      var data = {
        name: $(form).find("#name").val(),
        level: $(form).find("#level").val()
      };
      submit_ajax("api/rights/add", data);
      return false;
    }
  });
</script>