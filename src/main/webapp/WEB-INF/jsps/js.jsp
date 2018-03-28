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

	// get json
	var dataJson = (function() {
		var json = null;
		$.ajax({
			'async' : false,
			'global' : false,
			'url' : "rest/project/get-chart",
			'dataType' : "json",
			'success' : function(data) {
				json = data;
				update_chart(data);
				$.each(data, function(i, item) {
					if (!jQuery.isEmptyObject(item.reports)) {
						$("#dr_project").append(
								'<option value="' + item.id + '">' + item.name
										+ '</option>');
					}
				});
			}
		});
		return json;
	})();

	$("#dr_project").change(function() {
		var selected = this.value;
		if (selected != 0)
			var projects = jQuery.grep(dataJson, function(item, i) {
				return item.id == selected;
			});
		else
			var projects = dataJson;

		update_chart(projects);
	});

	function update_chart(projects) {
		$("#dr_task").empty();
		$("#dr_task").append('<option value="0">Select all task...</option>');
		var task = {};
		$.each(projects, function(i, item) {
			$.each(item.reports, function(i, item) {
				if (typeof task[item.taskId] != 'undefined') {
					var timeWorked = task[item.taskId][1] + item.timeWorked;
					task[item.taskId] = [ item.taskName, timeWorked ];
				} else {
					task[item.taskId] = [ item.taskName, item.timeWorked ];

					if (typeof task[item.taskId] != 'undefined')
						$("#dr_task").append(
								'<option value="' + item.taskId + '">'
										+ item.taskName + '</option>');
				}
			});
		});

		var taskName = [], taskTime = [];
		$.each(task, function(i, item) {

			taskName.push(item[0]);
			taskTime.push(item[1]);
		});
		myChart.data.labels = taskName;
		myChart.data.datasets[0].data = taskTime;
		myChart.update();
	}
</script>