<script>
    $(document).ready(function () {
        var reportList = $("#reports");
        var addReportForm = $("#addReportForm");
        
        var addWorkBtnClick = function(){
            $(".box-body").first().clone().appendTo(reportList);
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
            var b = $(".btn-note-remove");
            if (!(b.length > 1)) {
                b.prop('disabled', true);
                return;
            }
            seft.closest(".box-body").remove();
            b = $(".btn-note-remove");
            console.log(b);
            if (b.length == 1) {
                b.prop('disabled', true);
            } else {
                b.prop("disabled", false);
            }
            $(".btn-add-work").hide().filter(":last").show();
        }
        $("body").on("click", ".btn-note-remove", noteRemoveBtnClick);

        // on submit report
        $("body").on("click", "#report-submit", function () {
            var data = {};
            var projectCode = $("#projectCode").val();
            var tasks = [];
            var item = $("div").find(".box-body");
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
        });
    });
</script>
