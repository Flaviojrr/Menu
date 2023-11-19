function validateMenuItem(event) {
    let name = frmNovoPrato.name.value;
    let ingredients = frmNovoPrato.ingredients.value;
    let type = frmNovoPrato.type.value;

    if (name === "") {
        alert("Preencha o campo Nome");
        frmNovoPrato.name.focus();
        event.preventDefault(); 
        return false;
    } else if (ingredients === "") {
        alert("Preencha o campo Ingredientes");
        frmNovoPrato.ingredients.focus();
        event.preventDefault(); 
        return false;
    } else if (type === "") {
        alert("Preencha o campo Tipo");
        frmNovoPrato.type.focus();
        event.preventDefault();
        return false;
    }

    return true;
}