<script>
  $(document).ready(function () {

    var submitForm = $("#submit-form");
    var messages = {
      projectCode: "Project Code must be 4 characters.",
      name: "Name is too short (requires 6 characters).",
      startAt: "Invalid Start Date.",
      finishAt: "Invalid Finish Date.",
    };
    $.validator.addMethod("vndate", function (value, element) {
      return this.optional(element) || /^(0?[1-9]|[12][0-9]|3[01])[\/](0?[1-9]|1[012])[\/]\d{4}$/.test(value);
    }, "Please enter this field.");


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
        projectCode: {
          required: true,
          rangelength: [4, 4]
        },
        startAt: {
          required: true,
          vndate: true
        },
        finishAt: {
          required: false,
          vndate: true
        }
      },
      errorPlacement: function (error, element) {},
      submitHandler: function (form) {
        var data = {
          projectCode: $(form).find("#projectCode").val(),
          name: $(form).find("#name").val(),
          startAt: $(form).find("#startAt").val(),
          finishAt: $(form).find("#finishAt").val()
        };
        submit_ajax("api/project/add", data);
        return false;
      }
    });

    $("#startAt").change(function () {
      $("#finishAt").datepicker('setStartDate', $(this).val());
    });

    var dataJson;
    $.getJSON("api/project/get-all").done().always(function (data) {
      dataJson = data;
      $("#search").keyup(function () {
        search(this, data);
      });
      pagination(data);
    });


    $("body").on("click", ".list-group-item", function () {
      var i = $(this).attr("id");
      submitForm.attr('action', "api/project/edit/" + i);
      $.each(dataJson[i - 1], function (key, value) {
        $('input[name="' + key + '"]').val(value);
      });
      $("#list-bar").find("li").removeClass('active');
      $(this).addClass('active');
    });

  });
</script>