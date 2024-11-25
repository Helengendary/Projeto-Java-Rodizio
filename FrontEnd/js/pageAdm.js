const searchParams = new URLSearchParams(window.location.search);

const users_deck = document.getElementById('users_deck')
document.addEventListener("DOMContentLoaded", async() => {
    const response_users = await fetch(
        'http://localhost:8080/user?size=100',
        {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
            }
        }
    )
    if(!response_users.ok){
        alert('You are not and administrator!')
        return
    }

    const content_users = await response_users.json()

    const response_permissions = await fetch(
        'http://localhost:8080/user?size=100',
        {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
            }
        }
    )
    if(!response_permissions.ok){
        alert('You are not and administrator!')
        return
    }

    const content_permissions = await response_permissions.json()
    console.log(content_permissions),

    content_permissions.forEach(e => {
        users_deck.innerHTML += 
        `
            <tr>
                <td><input class="set_adm" id_user="${e.id}" type="checkbox"></td>
                <td><img src="https://via.placeholder.com/50" alt="User Image"></td>
                <td>${e.email}</td>
            </tr>
        `
        console.log(e)
    })

    const set_adm = document.querySelectorAll(".set_adm")
    set_adm.forEach((s) => {
        s.addEventListener('click', async() => {
            const response = await fetch(
                'http://localhost:8080/permission',
                {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
                    },
                    body: JSON.stringify({
                        userId: s.getAttribute('id_user'),
                        spaceId: searchParams.get('id'),
                        isAdm: s.checked
                    })
                }
            )
            alert(await response.text())
        })
    })

})


