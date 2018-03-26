<script>
	$.getJSON("rest/rights/get-all", function(data) {
		$.each(data, function(i, value) {
			$("#list-rights").append(
					'<li class="list-group-item"><span class="badge badge-secondary">'+ value.id +'</span> ' + value.name + '</li>');
		});
	})

	$(document).ready(function() {
		
		function onItemClick(e) {
			e.preventDefault();
			
			var id = $(this.children).text(); //get task id
			var taskName = $(this).contents().filter(function () {
                return this.nodeType == 3;
            }).text(); // get taskName
			
			$('form').attr('action', "rest/task/edit/" + id);
			$('input[name="txt_taskCode"]').val(id);
			$('input[name="txt_name"]').val(taskName)
			
			$that = $(this);
            $that.parent().find('li').removeClass('active');
            $that.addClass('active');
		}
		
		var links = $('#list-task').find("li");
		
		links.each(function () {
            this.onclick = onItemClick;
        });
		
		
		$("#searchTaskInput").keyup(function () {
            var filter, li;
            filter = $("#searchTaskInput").val().toUpperCase(); //get search input
            li = $("#list-task").find("li"); //get all li item
            
            $(li).each(function (key, item) {
                if ($(item).text().toUpperCase().indexOf(filter) > -1) {
                	$(item).show();
                } else {
                	$(item).hide();
                }
            });
        });
		
		
		$("form").submit(function() {
			$.ajax({
				url : $(this).attr('action'), // url where to submit the request
				type : "POST", // type of action POST || GET
				dataType : 'json', // data type
				data : $(this).serialize(), // post data || get data
				success : function(result) {
					if (!jQuery.isEmptyObject(result)) {
						if (!$.isPlainObject(result)) { // message
							alert(result);
							location.reload();
						}

						if ($.isPlainObject(result)) // error validate
							$.each(result, function(key, value) {
								alert(key + ': ' + value);
							})
					}
				},
				error : function() {
					alert("error");
					location.reload();
				}
			})
			return false;
		});
	});
</script>