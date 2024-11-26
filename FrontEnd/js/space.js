const searchParams = new URLSearchParams(window.location.search);

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
    
    content.forEach(async (element, index) => {
        console.log(1)
        const responseAnswers = await fetch("http://localhost:8080/answer/question/" + element.id, 
            {method: "GET", 
            headers: {'Accept': 'application/json', "Content-Type": "application/json", "Authorization": "Bearer " + sessionStorage.getItem("token")}}
        )
        console.log(responseAnswers);
        const answers = await responseAnswers.json();
        console.log(answers);
        lista.innerHTML += `<div id="questao_${index}" class="mb-5">
            <ul class="list-group list-group-horizontal list-group-light mb-3">
                <li class="list-group-item d-flex flex-row gap-3">
                    ${element.questioner.email}
                </li>
                <li class="list-group-item d-flex flex-row gap-3">
                    ${element.statement}
                </li>
            </ul> 
            </div>`
        answers.forEach(elemento => {
            document.getElementById("questao_" + index).innerHTML += 
            `<ul class="list-group list-group-horizontal list-group-light">
                <li class="list-group-item d-flex flex-row gap-3">
                    ${elemento.respondent.email}
                </li>
                <li class="list-group-item d-flex flex-row gap-3">
                    ${elemento.answer}
                </li>
            </ul>`
        })
            
    })
})

const go_configuration = document.getElementById('go_configuration')
go_configuration.addEventListener('click', () => {
    location.replace('/pageAdm.html?id=' + searchParams.get('id'))
})
