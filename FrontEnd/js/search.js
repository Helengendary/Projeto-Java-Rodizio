const opcao = document.getElementById('opcao')
const input_search = document.getElementById('input_search')

let go_to = '/pageGroup.html'
let endpoint = '/spaces?query='

opcao.addEventListener('change', () => {
    switch (opcao.value) {
        case "Space":
            go_to = '/pageGroup.html'
            endpoint = '/spaces?query='
            break;
    
        case "Question":
            // go_to = '/space.html'
            break;
    
        case "User":
            // go_to = '/pageGroup.html'
            endpoint = '/user?query='
            break;
    }
})

const button_search= document.getElementById('button_search')
const body = document.body
button_search.addEventListener('click', async() => {
    const response = await fetch(
        'http://localhost:8080' + endpoint + input_search.value,
        {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json",
                "Authorization": "Bearer " + sessionStorage.getItem("token")
            }
        }
    )
    console.log(await response.json())



    // body.innerHTML += 
    // `
    //     <button id="active_modal" hidden type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
    //     Launch demo modal
    //     </button>

    //     <!-- Modal -->
    //     <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    //     <div class="modal-dialog">
    //         <div class="modal-content">
    //         <div class="modal-header">
    //             <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
    //             <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    //         </div>
    //         <div class="modal-body">
    //             ...
    //         </div>
    //         <div class="modal-footer">
    //             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
    //             <button type="button" class="btn btn-primary">Save changes</button>
    //         </div>
    //         </div>
    //     </div>
    //     </div>
    // `

    const active_modal = document.getElementById('active_modal')
    active_modal.click

})

