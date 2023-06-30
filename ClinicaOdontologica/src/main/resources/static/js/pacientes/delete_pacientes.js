// Función para eliminar un paciente por su ID
function deleteBy(id){
    console.log("delete")

    // URL para eliminar al paciente con el ID proporcionado
    const url = '/paciente/eliminar/' + id;
    const settings = {
        method: 'DELETE'
    }

    // Realizar la solicitud para eliminar al paciente
    fetch(url, settings)
        .then(response => response.json())

    // Obtener el ID de la fila correspondiente al paciente y eliminarla de la tabla
    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
}
