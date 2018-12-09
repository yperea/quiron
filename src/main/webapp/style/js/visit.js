(function($){
    $(document).ready(function() {

        /*loadDiagnosisList();*/

        $('#symptom').change(function() {
           console.log('Symptom Id: ' + $(this).val());
           console.log('Symptom : ' + $("#symptom option:selected").text);
           loadDiagnosisList($(this).val());
        });

        $('#diagnosis').change(function() {
            console.log('Diagnosis Id: ' + $(this).val());
            console.log('Diagnosis : ' + $(this).text);
        });


        $("#btnSave").click(function(){
            console.log("Save");
        })

        $("#startDate").datepicker({
            changeMonth: true,
            changeYear: true
        })
    });
})(jQuery)

function saveVisit() {

    $.post("/patient/visit", function (data) {

    })
}

function loadDiagnosisList(symptomId) {

    var birthYear = $('#birthYear').val();
    var gender = $('#gender').val()
    if (symptomId == null) {
        symptomId = $('#symptom').val();
    }
    var url = '/quiron/service/apimedic/json/diagnosis?symptom=' + symptomId
            + '&birthYear=' + birthYear
            + '&gender=' + gender;

    $('#diagnosis').attr('disabled', 'disabled').find('option').remove();
    addDiagnosticToList(null, "Choose", false);

    $.getJSON( url, function(data){

        $(data).each(function(i, item){
            var selected = false;
            if (item.id == $('#diagnosticId').val()) {
                selected = true;
            }
            addDiagnosticToList(item.id, item.Name, selected);
        });
        $('#diagnosis').removeAttr('disabled');
    });
/*
    .done(function(data){
        console.log(data);
        $.each(data.items, function(i, item){
            console.log("item: " + item.Name);
        });
    });
*/
}

function addDiagnosticToList (id, name, selected) {
    var diagnosticItem = $('<option>')
                         .attr('value', id)
                         .attr('selected',selected)
                         .text(name);
    $('#diagnosis').append(diagnosticItem);
}