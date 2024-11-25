const modal = document.getElementById("modal");
const openModalButton = document.getElementById("createGroup");
const closeModalButton = document.getElementById("closeModal");
const createModalButton = document.getElementById("createModal");
const groupNameInput = document.getElementById("groupName");

const groups_deck = document.getElementById("groups_deck")


openModalButton.addEventListener("click", () => {
    modal.style.display = "flex";
    groupNameInput.value = ""; 
    groupNameInput.focus(); 
});


closeModalButton.addEventListener("click", () => {
    modal.style.display = "none";
});

createModalButton.addEventListener("click", async() => {
    const groupName = groupNameInput.value.trim();
    if (groupName != ''){
        const response = await fetch(
            'http://localhost:8080/spaces',
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + sessionStorage.getItem('token')
                },
                body: JSON.stringify(
                    {
                        name: groupName
                    }
                )
            }
        )
            alert(`Grupo "${groupName}" criado com sucesso!`),
            modal.style.display = "none",
            location.reload()
        
    } else {
        alert("Por favor, insira um nome para o grupo.");
    }
});













