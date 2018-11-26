(function($){
    $(document).ready(function() {

        var btnSave = $("#btnSave");
        btnSave.click(function(){
            console.log("Save");
        })

        $("#startDate").datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
})(jQuery)

function saveVisit() {

    $.post("/patient/visit", function (data) {

    })
}