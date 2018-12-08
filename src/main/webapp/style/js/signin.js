(function($){
    $(document).ready(function() {

        console.log(getQueryStringValue("tp"));

        var tp = getQueryStringValue('tp');

        if (tp == 'provider') {
            $('#login-type2').prop("checked", true);
            $('button').removeClass("btn-success");
            $('button').addClass("btn-primary");
        } else {
            $('#login-type1').prop("checked", true);
            $('button').removeClass("btn-primary");
            $('button').addClass("btn-success");
        }

        $('input[type="radio"]').on('click', function() {
            window.location = $(this).val();
        });
    });
})(jQuery)

function getQueryStringValue (key)
{
    return decodeURIComponent(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + encodeURIComponent(key).replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));
}
