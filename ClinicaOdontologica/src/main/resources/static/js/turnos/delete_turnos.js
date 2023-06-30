// Función para eliminar un turno por su ID
function deleteBy(id) {
    console.log("delete");

    // URL para eliminar el turno con el ID proporcionado
    const url = '/turno/eliminar/' + id;
    const settings = {
        method: 'DELETE'
    };

    // Realizar la solicitud para eliminar el turno
    fetch(url, settings)
        .then(response => response.json());

    // Obtener el ID de la fila correspondiente al turno y eliminarla de la tabla
    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
}
