function registrar() {
    emailInput = document.getElementById("email")
    edvInput = document.getElementById("edv")
    passInput = document.getElementById("pass")

    const response = fetch(
        'http://localhost:8080/user',
        {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: emailInput,
                edv: edvInput,
                pass: passInput
            })
        }
    )
    .then()

}