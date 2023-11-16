function validar() {
    let nome = frmNovoPrato.nome.value;
    let ingredientes = frmNovoPrato.ingredientes.value;
    let tipo = frmNovoPrato.tipo.value;

    if (nome === "") {
        alert("Preencha o campo Nome");
        frmNovoPrato.nome.focus();
        return false;
    } else if (ingredientes === "") {
        alert("Preencha o campo Ingredientes");
        frmNovoPrato.ingredientes.focus();
        return false;
    } else if (tipo === "") {
        alert("Preencha o campo Tipo");
        frmNovoPrato.tipo.focus();
        return false;
    }

    document.forms["frmNovoPrato"].submit();
    return true;
}
