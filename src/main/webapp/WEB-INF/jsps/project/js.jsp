<script>
  $(document).ready(
    function() {

      $("#submit-form").validate({
        onkeyup : function(element) {
          $(element).valid()
        },
        highlight : function(element) {
          $(element).addClass("is-invalid");
        },
        unhighlight : function(element) {
          $(element).removeClass("is-invalid");
        },
        rules : {
          name : {
            required : true,
            minlength : 2
          },
          projectCode : {
            required : true,
            minlength : 2
          }
        },
        messages : {
          name : {
            required : "Please enter name.",
            minlength : "Name too short"
          },
          projectCode : {
            required : "Please enter project code.",
            minlength : "Project code too short"
          }
        },
        submitHandler : function(form) {
          submit_ajax();
          alert('valid form submitted');
          return false;
        }
      });

      $("#startAt").change(function() {
        $("#finishAt").datepicker('setStartDate', $(this).val());
      });

      var responseJson = function(data) {
        Pagination.configPagination(data);
        Pagination.init();
        $.each(data, function(i, value) {
          $("#list-bar").append(
            '<li class="list-group-item"><span class="badge badge-secondary">'
              + value.id + '</span> ' + value.name + '</li>');
        });
        var $listBar = $("#list-bar").find("li");
        $listBar.click(function() {
          var i = $(this).index();
          $("form").attr('action', "api/project/edit/" + data[i].id);
          $.each(data[i], function(key, value) {
            $('input[name="' + key + '"]').val(value);
          });
          $listBar.removeClass('active');
          $(this).addClass('active');
        });
      };

      $.getJSON("api/project/get-all").done(responseJson);
    });
</script>