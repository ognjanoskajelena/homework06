function loginValidation() {
    let username = $("#username").val();
    let password = $("#password").val();
    if (username.length === 0 || password.length === 0) {
        $(".required-login-fields").css("display", "block");
    }
}

function registerValidation() {
    let name = $("#name").val().length;
    let surname = $("#surname").val().length;
    let username = $("#username").val().length;
    let password = $("#password").val().length;
    let repeatedPassword = $("#repeatedPassword").val().length;
    let email = $("#email").val().length;
    let birthDate = $("#birthDate").val().length;
    if (name === 0 || surname === 0 || username === 0 || password === 0 || repeatedPassword === 0 || email === 0 || birthDate === 0) {
        $(".required-register-fields").css("display", "block");
    }
}