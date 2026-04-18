const loginForm = document.getElementById("loginForm");

if (loginForm) {
    loginForm.addEventListener("submit", async function(e) {

        e.preventDefault();

        let email = document.getElementById("email").value;
        let password = document.getElementById("password").value;

        let formData = new URLSearchParams();
        formData.append("email", email);
        formData.append("password", password);

        try {
            let response = await fetch("login", {
                method: "POST",
                body: formData
            });

            let result = await response.text();

            if (result.trim() === "success") {
                window.location.href = "dashboard.html";
            } else {
                document.getElementById("errorMsg").innerText =
                    "Invalid Email or Password";
            }

        } catch(error) {
            document.getElementById("errorMsg").innerText = "Server Error";
        }

    });
}
else {
	console.log("null");
}