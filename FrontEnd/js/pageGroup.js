const groups_deck = document.getElementById('groups_deck')

document.addEventListener("DOMContentLoaded", async() => {
    const response = await fetch(
        'http://localhost:8080/spaces?size=100',
        {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
            }
        }
    )
    content = await response.json()
    console.log(content)

    content.forEach(e => {
        groups_deck.innerHTML += `
             <tr id_space="${e.id}">
                <td>${e.name}</td>
                <td><a href="./space.html?id=${e.id}"><button class="btn btn-custom" style="background-color: white;">Abrir</button></a></td>
            </tr>
        `
    })
    


})
