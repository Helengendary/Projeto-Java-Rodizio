const lista = document.getElementById("lista");

document.addEventListener("DOMContentLoaded", async () => {
    const response = await fetch(
        ("http://localhost:8080/question/space/" + 1 + "?size=100"),
        {
            method: "GET",
            headers: {
                'Accept': 'application/json',
                "Content-Type": "application/json",
                "Authorization": "Bearer " + sessionStorage.getItem("token")
            }
        }
    )

    console.log(response)
    const content = await response.json();
    console.log(content);
    content.forEach((element, index) => {
        lista.innerHTML += (`<div id="questao_${index}" class="mb-5">
            <ul class="list-group list-group-horizontal list-group-light">
                <li class="list-group-item d-flex flex-row gap-3">
                    ${element.questioner.participant.email}
                </li>
                <li class="list-group-item d-flex flex-row gap-3">
                    ${element.statement}
                </li>
            </ul>
            
            </div>
            `)
    });
})

// ${async () => {
//     await fetch("http://localhost:8080/answer/question/" + element.idSpace, 
//                 {method: "GET", 
//                 headers: {'Accept': 'application/json', "Content-Type": "application/json", "Authorization": "Bearer " + sessionStorage.getItem("token")}}
//              ).then(response.json.forEach(element => {
//                 document.getElementById("questao_" + index).innerHTML += 
//                 `<ul class="list-group list-group-horizontal list-group-light">
//                     <li class="list-group-item d-flex flex-row gap-3">
//                         ju
//                     </li>
//                     <li class="list-group-item d-flex flex-row gap-3">
//                         ${element.statement}
//                     </li>
//                 </ul>`
//              }))
// }}