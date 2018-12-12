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
        var treatmentInstructions = $("#instructions");

        $('#symptom').change(function() {
           console.log('Symptom Id: ' + $(this).val());
           console.log('Symptom : ' + $("#symptom option:selected").text);
           loadDiagnosisList($(this).val());
        });

        $('#diagnosis').change(function() {
            console.log('Diagnosis Id: ' + $(this).val());
            console.log('Diagnosis : ' + $(this).text);
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


        /*Modal form scripts*/

        $("#frmPrescription").submit(function (event) {
            console.log("adding prescription");
            event.preventDefault();
            return addPrescription();
        });

        function addPrescription() {
            var valid = true;
            valid = valid && checkLength( treatmentStartDate, 10, 10 );
            valid = valid && checkLength( treatmentEndDate, 10, 10 );
            valid = valid && checkLength( treatmentInstructions, 0, 512 );

            if ( valid ) {
                $( "#prescriptions tbody" ).append( "<tr>" +
                    "<td>" + treatmentStartDate.val() + "</td>" +
                    "<td>" + treatmentEndDate.val() + "</td>" +
                    "<td>" + treatmentInstructions.val() + "</td>" +
                    "</tr>" );

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


