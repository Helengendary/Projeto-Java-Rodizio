const login_button = document.getElementById('login_button')
const edv = document.getElementById('edv')
const password = document.getElementById('pass')


login_button.addEventListener('click', async() => {
    if(edv.value == '' || password == '')
        return



    const response = await fetch(
        'http://localhost:8080/auth',
        {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(
                {
                    edv: edv.value,
                    password: password.value
                }
            )
        }
    )

    const content = await response.json()

    if(response.status !== 200){
        alert(content.message)
        return
    }

    sessionStorage.setItem(content.token)

})