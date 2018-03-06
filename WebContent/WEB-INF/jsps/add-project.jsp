<div class="card">
	<div class="card-body">
		<form>
			<div class="form-group row">
				<label for="projectCode" class="col-sm-2 col-form-label">Project
					code</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="projectCode"
						placeholder="Project code">
				</div>
			</div>
			<div class="form-group row">
				<label for="name" class="col-sm-2 col-form-label">Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name"
						placeholder="Name">
				</div>
			</div>
			<div class="form-group row">
				<label for="startAt" class="col-sm-2 col-form-label">Start at</label>
				<div class="col-sm-10">
					<input id="startAt">
				</div>
			</div>
			<div class="form-group row">
				<label for="finishAt" class="col-sm-2 col-form-label">Finish at</label>
				<div class="col-sm-10">
					<input id="finishAt">
				</div>
			</div>
			
		</form>
		<script>
			var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
	        $('#startAt').datepicker({
	            uiLibrary: 'bootstrap4',
	            minDate: today,
	            maxDate: function () {
	                return $('#finishAt').val();
	            }
	        });
	        $('#finishAt').datepicker({
	            uiLibrary: 'bootstrap4',
	            minDate: function () {
	                return $('#startAt').val();
	            }
	        });
	    </script>
	</div>
</div>