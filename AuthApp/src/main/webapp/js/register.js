const registerForm = document.getElementById("registerForm");

if (registerForm) {
    registerForm.addEventListener("submit", async function(e) {

        e.preventDefault();

        let fullname = document.getElementById("fullname").value;
        let email = document.getElementById("email").value;
        let password = document.getElementById("password").value;
        let confirmPassword =
            document.getElementById("confirmPassword").value;

        let message = document.getElementById("message");

        message.innerText = "";

        if (password !== confirmPassword) {
            message.style.color = "red";
            message.innerText = "Passwords do not match";
            return;
        }

        let formData = new URLSearchParams();
        formData.append("fullname", fullname);
        formData.append("email", email);
        formData.append("password", password);

        try {
            let response = await fetch("register", {
                method: "POST",
                body: formData
            });

            let result = await response.text();

            if (result.trim() === "success") {
                message.style.color = "green";
                message.innerText = "Registration Successful";

                setTimeout(() => {
                    window.location.href = "index.html";
                }, 1500);

            } else {
                message.style.color = "red";
                message.innerText = result;
            }

        } catch(error) {
            message.style.color = "red";
            message.innerText = "Server Error";
        }

    });
}