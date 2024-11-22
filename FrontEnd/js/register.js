const emailInput = document.getElementById("email")
const edvInput = document.getElementById("edv")
const passInput = document.getElementById("pass")


async function registrar() {

    const response = await fetch(
        'http://localhost:8080/user',
        {
            method: 'post',
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                edv: edvInput.value,
                email: emailInput.value,
                password: passInput.value
            })
        }
    )

    const content = await response.text();
    alert(content);
    location.replace("/index.html");
}