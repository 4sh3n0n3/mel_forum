$("#login_form").validate({
    rules: {
        log_username: {
            required: true,
        },
        log_password: {
            required: true,
        }
    },
    messages: {
        log_username: {
            required: 'Пожалуйста, введите ваш логин',
        },
        log_password: {
            required: 'Пожалуйста, введите ваш пароль'
        }
    },
    errorElement: 'span',
    errorClass: 'help-block',
    errorPlacement: function (error, element) {
        error.insertBefore(element);
    }
});

function try_submit(){
    if ($("#login_form").valid) {
        return true;
    };
    return false;
}