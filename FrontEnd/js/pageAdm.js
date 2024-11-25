const searchParams = new URLSearchParams(window.location.search);

const users_deck = document.getElementById('users_deck')
document.addEventListener("DOMContentLoaded", async() => {
    const response_users_permissions = await fetch(
        `http://localhost:8080/permission?spaceId=${searchParams.get('id')}`,
        {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
            }
        }
    )
    if(!response_users_permissions.ok){
        alert('You are not the problem!')
        return
    }

    const content_users_permissions = await response_users_permissions.json()
    console.log(content_users_permissions),

    content_users_permissions.forEach(e => {
        users_deck.innerHTML += 
        `
            <tr>
                <td><input class="set_adm" ${e.adm ? 'checked' : ''} id_user="${e.participant.id}" type="checkbox"></td>
                <td><img src="https://via.placeholder.com/50" alt="User Image"></td>
                <td>${e.participant.email}</td>
            </tr>
        `
    })

    const set_adm = document.querySelectorAll(".set_adm")
    set_adm.forEach((s) => {
        s.addEventListener('click', async() => {
            const response_permissions = await fetch(
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
        if(response_permissions.status == 403)
            s.checked = !s.checked

        alert(await response_permissions.text())
                
        })
    })

})



const delete_button = document.getElementById('delete_button')

delete_button.addEventListener('click', () => {
    console.log(searchParams.get('id'))
})


const add_user = document.getElementById('add_user')
const add_user_input = document.getElementById('add_user_input')
add_user.addEventListener('click', async() => {
    if(add_user_input.value == '')
        return

    const response_users = await fetch(
        `http://localhost:8080/user?query=${add_user_input.value}`,
        {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
            }
        }
    )

    let user = await response_users.json()
    user = user[0]

    
    const response_permissions = await fetch(
        'http://localhost:8080/permission',
        {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
            },
            body: JSON.stringify({
                userId: user.id,
                spaceId: searchParams.get('id'),
                isAdm: 0
            })
        }
    )

    alert(await response_permissions.text())
})