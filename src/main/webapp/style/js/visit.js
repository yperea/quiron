(function($){
    $(document).ready(function() {

        console.log("jquery function");

        var btnSave = $("#btnSave");
        var birthYear = $('#birthYear');
        var gender = $('#gender');

        btnSave.click(function(){
            console.log("Save");
        })

        $("#startDate").datepicker({
            changeMonth: true,
            changeYear: true
        })

        $("#symptom2").flexdatalist({
            minLength: 1,
            searchIn: 'name',
            data: 'countries.json'
        });

/*
        $('#symptom2').flexdatalist({
            searchContain: false,
            textProperty: '{id}, {Name}',
            valueProperty: 'id',
            minLength: 1,
            focusFirstResult: true,
            selectionRequired: true,
            visibleProperties: ["id","Name"],
            searchIn: ["Name"],
            url: 'http://localhost:8080/quiron/service/apimedic/json/symptoms',
            relatives: '#relative'
        });
*/

/*        $('#symptom2').flexdatalist({
            minLength: 0,
            valueProperty: 'id',
            selectionRequired: true,
            visibleProperties: ["id","Name"],
            searchIn: 'Name',
            url: 'http://localhost:8080/quiron/service/apimedic/json/symptoms'
        });*/

        $('#diagnosis').flexdatalist({
            searchContain: false,
            textProperty: '{id}, {Name}',
            valueProperty: 'id',
            minLength: 0,
            focusFirstResult: true,
            selectionRequired: false,
            visibleProperties: ["id","Name"],
            searchIn: ["Name"],
            chainedRelatives: true,
            relatives: '#symptom',
            url: '/quiron/service/apimedic/json/diagnosis?birthYear='+ birthYear.val() +'&gender='+ gender.val()
        });


    });
})(jQuery)

function saveVisit() {

    $.post("/patient/visit", function (data) {

    })
}