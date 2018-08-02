function validate(form, usernameFlag){
    var username = form.elements["username"];
    var email = form.elements["email"];
    var password = form.elements["password"];

    var usernamePattern = new RegExp("^[a-zA-Z0-9_-]{3,15}$");
    var emailPattern = new RegExp("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    var passwordPattern = new RegExp("^(?=.*[A-Za-z])(?=\\S+$)[A-Za-z\\d#$%^&*?!]{5,32}$");

    var valid = true;

    if (usernameFlag) {
        if (!usernamePattern.test(username.value)) {
            showError("username");
            valid =  false;
        } else {
            showSuccess("username")
        }
    }

    if (!emailPattern.test(email.value.toLowerCase())){
        showError("email");
        valid = false;
    } else {
        showSuccess("email");
    }

    if (!passwordPattern.test(password.value)){
        showError("password");
        valid =  false;
    } else {
        showSuccess("password");
    }
    return valid;
}

function showError(id){
    document.getElementById(id).style.borderColor = "red";
    document.getElementById(id + "-error").style.display = "inline-block";
}

function showSuccess(id){
    document.getElementById(id).style.borderColor = "green";
    document.getElementById(id + "-error").style.display = "none";
}

function showDefault(id){
    document.getElementById(id).style.borderColor = "#ccc";
    document.getElementById(id + "-error").style.display = "none";
}

function resetForm(){
    showDefault("username");
    showDefault("email");
    showDefault("password");
}

function showForm(id){
    var element = document.getElementById(id);
    var display = window.getComputedStyle(element, null).getPropertyValue("display");
    if (display == "none"){
        element.style.display = "block";
    } else {
        element.style.display = "none";
    }
}
