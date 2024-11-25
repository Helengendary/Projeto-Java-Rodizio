const searchParams = new URLSearchParams(window.location.search);

const lista = document.getElementById("lista");

document.addEventListener("DOMContentLoaded", async () => {
    const response = await fetch(
        ("http://localhost:8080/question/" + 1 + "?size=100"),
        {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json",
                "Authorization": "Bearer " + sessionStorage.getItem("token")
            }
        }
    )

    const content = await response.json();
    console.log(content);
    // content.forEach(element => {
    //     lista.appendChild(`<div id="questao" class="mb-5">
    //         <ul class="list-group list-group-horizontal list-group-light">
    //             <li class="list-group-item d-flex flex-row gap-3">
    //                 ${element.}
    //             </li>
    //             <li class="list-group-item d-flex flex-row gap-3">
    //                 como faço um commit?
    //             </li>
    //         </ul>
    //         </div>`)
    // });
})

const go_configuration = document.getElementById('go_configuration')
go_configuration.addEventListener('click', () => {
    location.replace('/pageAdm.html?id=' + searchParams.get('id'))
})