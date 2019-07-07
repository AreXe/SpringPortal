$(document).ready(function() {
    $('input').keyup(function() {

        var empty = false;
        $('input').each(function() {
            if ($(this).val().length == 0) {
                empty = true;
            }
        });

        if (empty) {
            $('#submit').attr('disabled', 'disabled');
        } else {
            $('#submit').attr('disabled', false);
        }
    });
});