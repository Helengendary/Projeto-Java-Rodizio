const searchParams = new URLSearchParams(window.location.search);

const lista = document.getElementById("lista");

document.addEventListener("DOMContentLoaded", async () => {
    const response = await fetch(
        ("http://localhost:8080/question/space/" + searchParams.get('id') + "?size=100"),
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
    
    content.forEach(async (element, index) => {
        const responseAnswers = await fetch("http://localhost:8080/answer/question/" + element.id, 
            {method: "GET", 
            headers: {'Accept': 'application/json', "Content-Type": "application/json", "Authorization": "Bearer " + sessionStorage.getItem("token")}}
        )
        const answers = await responseAnswers.json();

        lista.innerHTML += `<div id="questao_${index}" class="mb-5">
            <ul class="list-group list-group-horizontal list-group-light mb-3">
                <li class="list-group-item d-flex flex-row gap-3">
                    ${element.questioner.email}
                </li>
                <li class="list-group-item d-flex flex-row gap-3">
                    ${element.statement}
                </li>
                <input placeholder="responder" class="p-2" id="r_${element.id}" name="resposta">
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

const modal = document.getElementById("modal");
const openModalButton = document.getElementById("createGroup");
const closeModalButton = document.getElementById("closeModal");
const createModalButton = document.getElementById("createModal");
const questionInput = document.getElementById("question");



openModalButton.addEventListener("click", () => {
    modal.style.display = "flex";
    questionInput.value = ""; 
    questionInput.focus(); 
});


closeModalButton.addEventListener("click", () => {
    modal.style.display = "none";
});

createModalButton.addEventListener("click", async() => {
    const question = questionInput.value.trim();
    if (question != ''){
        const response = await fetch(
            'http://localhost:8080/question',
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
                },
                body: JSON.stringify(
                    {
                        "statement": question,
                        "idSpace": searchParams.get('id')
                    }
                )
            }
        )
            alert(`Questão "${question}" criada com sucesso!`),
            modal.style.display = "none",
            location.reload()
        
    } else {
        alert("Por favor, insira a questão.");
    }
});

document.getElementsByName("resposta").forEach(r => {
    r.addEventListener("", async() => {
        const resposta = document.getElementById(r.id).value;
        if (resposta != ''){
            const response = await fetch(
                'http://localhost:8080/answer',
                {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
                    },
                    body: JSON.stringify(
                        {
                            "statement": resposta,
                            "questionId": r.id.replace("r_",""),
                            "spaceId": searchParams.get('id')
                        }
                    )
                }
            )
                alert(`Resposta "${resposta}" criada com sucesso!`),
                modal.style.display = "none",
                location.reload()
            
        } else {
            alert("Por favor, insira a resposta.");
        }
    });
})
