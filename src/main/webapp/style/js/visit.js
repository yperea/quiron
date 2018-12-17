/*------------------------------------------------------
  Project Quiron
  Author: Yesid Perea
  Date: 12/11/2018

  Handles the dependency between listbox Symptoms and Diagnosis.
  And the modal dialog form for adding a prescription.
-------------------------------------------------------*/
(function($){
    $(document).ready(function() {

        var treatmentStartDate = $("#startDate");
        var treatmentEndDate = $("#endDate");
        var treatmentDosis = $("#dosis");
        var medication = $("#medication");
        var treatmentInstructions = "";

        $('#symptom').change(function() {
            var symptomName = $('#symptom').find(":selected").text();
            $('#symptomName').val(symptomName.trim());
            $('#diagnosticName').val(null);
            loadDiagnosisList($(this).val());
        });

        $('.rt-control').change(function() {
            var controlName = $(this).attr('id');
            var controlValue = $(this).val();
            var finalControl = controlName.replace('tmp-', '');
/*
            console.log("controlName: " + controlName);
            console.log("controlValue: " + controlValue);
            console.log("finalControl: " + finalControl);
*/
            eval("$('#" + finalControl + "').val("+ controlValue +")");

        });


        $('#diagnosis').change(function() {
            var diagnosisName = $('#diagnosis').find(":selected").text();
            $('#diagnosticName').val(diagnosisName.trim());
        });

        $(".date-picker").datepicker({
            changeMonth: true,
            changeYear: true
        })

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
            addDiagnosticToList("", "Choose", false);

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


        /*Modal form scripts*/

        $("#frmPrescription").submit(function (event) {
            console.log("adding prescription");
            event.preventDefault();
            return addPrescription();
        });

        function addPrescription() {
            var valid = true;
            var medicationName = $("#medication option:selected").text();

            valid = valid && checkLength( treatmentStartDate, 10, 10 );
            valid = valid && checkLength( treatmentEndDate, 10, 10 );
            valid = valid && checkLength( treatmentDosis, 0, 512 );

            if ( valid ) {

                $("#treatmentStartDate").val(treatmentStartDate.val());
                $("#treatmentEndDate").val(treatmentEndDate.val());
                $("#treatmentInstructions").val(treatmentDosis.val());
                $("#medicationId").val(medication.val());

                treatmentInstructions = "Please "
                                      + $("#treatmentInstructions").val() + " of "
                                      + medicationName + ", from "
                                      + $("#treatmentStartDate").val() + " to "
                                      + $("#treatmentEndDate").val() + ". "


                $("#prescriptionDetails").text(treatmentInstructions);
                $( "#dialog-form" ).modal( "hide" );
            }
            return valid;
        }

        function checkLength( o, min, max ) {
            if ( o.val().length > max || o.val().length < min ) {
                return false;
            } else {
                return true;
            }
        }
    });
})(jQuery);


