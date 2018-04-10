<script>
    $(document).ready(function () {
        var addReportForm = $("#addReportForm");

        var addWorkBtnClick = function () {
            $(".card").first().clone().appendTo(addReportForm);
            $(".btn-note-remove").prop("disabled", false);
        };
        $("body").on("click", ".btn-add-work", addWorkBtnClick);

        // on toggle note click
        var noteToggleBtnClick = function () {
            var self = $(this);
            self.children().toggleClass("fa-plus fa-minus");
            self.closest(".card-body").children(".r-note").slideToggle("fast");
        };
        $("body").on("click", ".btn-note-toggle", noteToggleBtnClick);

        // on remove
        var noteRemoveBtnClick = function () {
            var seft = $(this);
            var b = $(".btn-note-remove");
            if (!(b.length > 1)) {
                b.prop('disabled', true);
                return;
            }
            seft.closest(".card-body").remove();
            b = $(".btn-note-remove");
            if (b.length == 1) {
                b.prop('disabled', true);
            } else {
                b.prop("disabled", false);
            }
        }
        $("body").on("click", ".btn-note-remove", noteRemoveBtnClick);

        // on submit report
        var messages = {
            projectCode: "Please choose a Project.",
            taskCode: "Please choose a Task.",
            timeWork: "Time work is invalid.",
            note: "Note must be not blank.",
        };
        addReportForm.validate({
            onclick: function (e) {
                console.log(e);
                $(e).valid();
            },
            onkeyup: function (e) {
                $(e).valid();
                console.log(e)
            },
            highlight: function (e) {
                $(e).addClass("is-invalid").tooltip({
                    title: messages[e.name],
                    placement: 'right',
                }).tooltip('show');
            },
            unhighlight: function (e) {
                console.log(e);
                // $(e).removeClass("is-invalid")
                // $(e).removeClass("is-invalid").tooltip('dispose');
            },
            rules: {
                projectCode: {
                    required: true
                },
                taskCode: {
                    required: true
                },
                timeWork: {
                    required: true,
                    digits: true
                },
                note: {
                    required: true,
                    minlength: 10
                }
            },
            errorPlacement: function (error, element) {},
            submitHandler: function (form) {
                var data = {};
                var projectCode = $("#projectCode").val();
                var tasks = [];
                var item = $("div").find(".card-body");
                $.each(item, function () {
                    var record = {};
                    var recordItem = $(this).find("input, select, textarea");
                    $.each(recordItem, function () {
                        record[this.name] = this.value;
                    });
                    tasks.push(record);
                });
                data.projectCode = projectCode;
                data.tasks = tasks;
                console.table(data);

                var fakeJson = {
                    projectId: 1,
                    tasks: [
                        {
                            taskId: 1,
                            timeWork: 5,
                            note: "aofijeaijrioajfei"
                        }
                    ]
                };
                submit_ajax("api/report/add", fakeJson);
                return false;
            }
        })
    });
</script>