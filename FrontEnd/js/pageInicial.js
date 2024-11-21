const modal = document.getElementById("modal");
const openModalButton = document.getElementById("createGroup");
const closeModalButton = document.getElementById("closeModal");
const createModalButton = document.getElementById("createModal");
const groupNameInput = document.getElementById("groupName");

openModalButton.addEventListener("click", () => {
    modal.style.display = "flex";
    groupNameInput.value = ""; 
    groupNameInput.focus(); 
});


closeModalButton.addEventListener("click", () => {
    modal.style.display = "none";
});

createModalButton.addEventListener("click", () => {
    const groupName = groupNameInput.value.trim();
    if (groupName) {
        alert(`Grupo "${groupName}" criado com sucesso!`);
        modal.style.display = "none";
    } else {
        alert("Por favor, insira um nome para o grupo.");
    }
});
