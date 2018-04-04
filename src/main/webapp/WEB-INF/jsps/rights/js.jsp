<script>
  var dataJson;
  var responseJson = function (data) {
    dataJson = data;
    pagination(data);
    $("form").attr('action', "api/rights/add");
    var $listBar = $("#list-bar").find("li");
    $listBar.click(function () {
      var i = $(this).index();
      $("form").attr('action', "api/rights/edit/" + data[i].id);
      $.each(data[i], function (key, value) {
        $('input[name="' + key + '"]').val(value);
      });
      $listBar.removeClass('active');
      $(this).addClass('active');
    });
  };
  $.getJSON("api/rights/get-all").done(responseJson);
  
  $("#search").keyup(function () {
    var filter = locdau($(this).val().replace(/\s+/g, ''));
    var result = dataJson.filter(item => {
      var name = locdau(item.name.replace(/\s+/g, ''));
      return name.indexOf(filter) !== -1;
    });
    pagination(result);
  });
</script>