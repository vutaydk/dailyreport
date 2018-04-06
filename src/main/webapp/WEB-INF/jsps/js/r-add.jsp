<script>
    $(document).ready(function () {

        var taskForm =
            `<div class="box-body">
            <div class="row">
                <div class="col-10">

                    <div class="form-group row">
                        <label class="col-3 col-form-label">
                            <m:message key="label.projectCode" />
                        </label>
                        <div class="col-9">
                            <select name="projectCode" class="form-control">
                                <option value="">Choose...</option>
                                <option value="Project1">Project 1</option>
                                <option value="Project2">Project 2</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">
                            <m:message key="label.taskCode" />
                        </label>
                        <div class="col-9">
                            <select name="taskCode" class="form-control">
                                <option value="">Choose...</option>
                                <option value="Task1">Task 1</option>
                                <option value="Task2">Task 2</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-3 col-form-label">
                            <m:message key="label.timeWork" />
                        </label>
                        <div class="col-9">
                            <input type="text" name="timeWork" class="form-control" placeholder="Hour">
                        </div>
                    </div>
                </div>

                <div class="col-2">
                    <div class="row">
                        <button class="btn btn-outline-danger ml-2 btn-note-remove" type="button">
                            <i class="fas fa-trash-alt"></i>
                        </button>
                        <button id="toggle-note" class="btn btn-outline-dark  ml-2 btn-note-toggle" type="button" data-toggle="collapse" data-target="#report-note"
                            aria-controls="report-note" aria-expanded="false" aria-label="Toggle report note">
                            <i class="fas fa-minus"></i>
                        </button>
                    </div>
                </div>

                </div>
                <div class="r-note">
                    <div class="form-group">
                        <label>
                            <m:message key="label.note" />
                        </label>
                        <div class="col-12">
                            <textarea rows="10" name="note" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button type="button" class="btn btn-outline-dark btn-add-work">
                                <m:message key="label.submit" />
                            </button>
                        </div>
                    </div>
                </div>
            </div>`;

        var addReportForm = $("#addReportForm");
        var reportList = $("#reports");
        

        // on Add work button click
        var addWorkBtnClick = function () {
            reportList.append(taskForm);
            var btnAddWork = $(".btn-add-work");
            btnAddWork.hide().filter(":last").show();
        };
        $("body").on("click", ".btn-add-work", addWorkBtnClick);

        // on toggle note click
        var noteToggleBtnClick = function () {
            var self = $(this);
            self.children().toggleClass("fa-plus fa-minus");
            self.closest(".box-body").children(".r-note").slideToggle("fast");
        };
        $("body").on("click", ".btn-note-toggle", noteToggleBtnClick);

        // on remove
        var noteRemoveBtnClick = function () {
            var seft = $(this);
            seft.closest(".box-body").remove();
            var btnAddWork = $(".btn-add-work");
            btnAddWork.hide().filter(":last").show();
        }
        $("body").on("click", ".btn-note-remove", noteRemoveBtnClick);

        // on submit report
        $("body").on("click", "#report-submit", function () {
            var data = [];
            var reportFormItem = $("div").find(".box-body");
            $.each(reportFormItem, function () {
                var record = {};
                var recordItem = $(this).find("input, select, textarea");
                $.each(recordItem, function () {
                    record[this.name] = this.value;
                });
                data.push(record);
            });
            console.log(data);
        });

    });
</script>