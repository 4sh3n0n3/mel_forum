$.validator.addMethod('regex', function(value, element, regexp) {
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    }
);

$("#registration_form").validate({
    rules: {
        reg_username: {
            required: true,
            regex: /^[a-z0-9_-]{4,16}$/i,
        },
        reg_password: {
            required: true,
            regex: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/i,
        },
        reg_password_check: {
            required: true,
            equalTo: "#reg_password",
        },
        reg_email: {
            required: true,
            regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/i,
        }
    },
    messages: {
         reg_username: {
             required: 'Поле username не может быть пустым',
             regex: 'Username должен состоять из букв латиницы и цифр, длина от 5 до 16 символов.',
         },
         reg_password: {
             required: 'Поле password не может быть пустым',
             regex: 'Minimum eight characters, at least one letter and one number',
         },
         reg_password_check: {
             required: 'Пожалуйста, повторите пароль',
             equalTo: 'Пароли не совпадают',
         },
         reg_email: {
             required: 'Введите email',
             regex: 'Проверьте корректность введённого email',
         }
    },
    errorElement: 'span',
    errorClass: 'help-block',
    errorPlacement: function (error, element) {
        error.insertBefore(element);
    }
});

function try_submit() {
    if ($("#registration_form").valid) {
        return true;
    };
    return false;
};

