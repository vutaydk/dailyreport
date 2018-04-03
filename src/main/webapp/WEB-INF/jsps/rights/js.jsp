<script>
  var responseJson = function(data) {
    pagination(data);
    $("form").attr('action', "api/rights/add");
    var $listBar = $("#list-bar").find("li");
    $listBar.click(function() {
      var i = $(this).index();
      $("form").attr('action', "api/rights/edit/" + data[i].id);
      $.each(data[i], function(key, value) {
        $('input[name="' + key + '"]').val(value);
      });
      $listBar.removeClass('active');
      $(this).addClass('active');
    });
  };

  $.getJSON("api/rights/get-all").done(responseJson);
</script>