<script>
  // Chart
  var ctx = document.getElementById("myChart").getContext('2d');
  var data = {
    type : 'pie',
    data : {
      labels : [],
      datasets : [ {
        label : '# of Votes',
        data : [],
        backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
          'rgba(255, 159, 64, 0.2)' ],
        borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
          'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
          'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
        borderWidth : 1
      } ]
    },
    options : {
      scales : {
        yAxes : [ {
          ticks : {
            beginAtZero : true
          }
        } ]
      }
    }
  };
  var myChart = new Chart(ctx, data);

  // data processing
  var responseJson = function(data) {
    update_chart(data);
    $.each(data, function(i, item) {
      if (!jQuery.isEmptyObject(item.tasks)) {
        $("#dr_project").append(
          '<option value="' + item.id + '">' + item.name + '</option>');
      }
    });

    $("#dr_project").change(function() {
      var selected = this.value;
      if (selected != 0)
        var projects = jQuery.grep(data, function(item, i) {
          return item.id == selected;
        });
      else
        var projects = data;

      update_chart(projects);
    });
  };

  // get json
  $.getJSON("api/project/get-chart").done(responseJson);

  function update_chart(projects) {
    $("#dr_task").empty().append(
      '<option value="0">Select all task...</option>');
    var task = {};
    $.each(projects, function(i, item) {
      $.each(item.tasks, function(i, item) {
        if (typeof task[item.taskId] != 'undefined') {
          var timeWork = task[item.taskId][1] + item.timeWork;
          task[item.taskId] = [ item.taskName, timeWork ];
        } else {
          task[item.taskId] = [ item.taskName, item.timeWork ];

          if (typeof task[item.taskId] != 'undefined')
            $("#dr_task").append(
              '<option value="' + item.taskId + '">' + item.taskName
                + '</option>');
        }
      });
    });

    var taskName = [], taskTime = [], total = 0;
    $tbody = $("table tbody").empty();
    $tfoot = $("table tfoot").empty();
    $.each(task, function(i, item) {
      $tbody
        .append('<tr><td>' + item[0] + '</td><td>' + item[1] + '</td></tr>');
      taskName.push(item[0]);
      taskTime.push(item[1]);
      total += item[1];
    });
    $tfoot.append('<tr><td>Total</td> <td>' + total + '</td></tr>');
    myChart.data.labels = taskName;
    myChart.data.datasets[0].data = taskTime;
    myChart.update();
  }
</script>