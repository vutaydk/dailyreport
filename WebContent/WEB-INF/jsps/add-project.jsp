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
        $('#startAt').datepicker({
            uiLibrary: 'bootstrap4'
        });
        $('#finishAt').datepicker({
            uiLibrary: 'bootstrap4'
        });
    </script>
	</div>
</div>