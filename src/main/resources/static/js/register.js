function validateForm() {
    let name = document.forms["myForm"]["name"].value;
    let username = document.forms["myForm"]["username"].value;
    let password = document.forms["myForm"]["password"].value;
    let confirm = document.forms["myForm"]["confirmpassword"].value;
    if (name == "") {
        alert("Name must not be empty !");
        return false;
    }
    if (name.length > 44 || name.length < 3) {
        alert("Name must be < 44 characters and > 3 characters !");
        return false;
    }
    if (username == "") {
        alert("UserName must not be empty !");
        return false;
    }
    if (username.length > 44 || username.length < 3) {
        alert("UserName must be < 44 characters and > 3 characters  !");
        return false;
    }

    if (password == "") {
        alert("Password must not be empty !");
        return false;
    }
    if (password.length > 250 || password.length < 3 ) {
        alert("Password must be < 250 characters and > 3 characters !");
        return false;
    }
    if(confirm != password){
        alert("Confirm Password must be equals  Password");
        return false;

    }

}
