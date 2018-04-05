<script>
  $(document).ready(function () {
    $("#submit-form").validate({
      onkeyup: function (element) {
        $(element).valid()
      },
      highlight: function (element) {
        $(element).addClass("is-invalid");
      },
      unhighlight: function (element) {
        $(element).removeClass("is-invalid");
      },
      rules: {
        name: {
          required: true,
          minlength: 2
        },
        projectCode: {
          required: true,
          minlength: 2
        }
      },
      messages: {
        name: {
          required: "Please enter name.",
          minlength: "Name too short"
        },
        projectCode: {
          required: "Please enter project code.",
          minlength: "Project code too short"
        }
      },
      submitHandler: function (form) {
        submit_ajax();
        alert('valid form submitted');
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
      $("form").attr('action', "api/project/edit/" + i);
      $.each(dataJson[i - 1], function (key, value) {
        $('input[name="' + key + '"]').val(value);
      });
      $("#list-bar").find("li").removeClass('active');
      $(this).addClass('active');
    });

  });
</script>