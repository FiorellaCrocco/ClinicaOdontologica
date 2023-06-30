// Función para eliminar un odontólogo por su ID
function deleteBy(id) {
    // Construir la URL para la eliminación del odontólogo
    const url = '/odontologo/eliminar/' + id;

    // Configuración de la petición de eliminación
    const settings = {
        method: 'DELETE'
    };

    // Realizar la petición de eliminación
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            // Eliminar la fila correspondiente al odontólogo eliminado
            let row_id = "#tr_" + id;
            document.querySelector(row_id).remove();
        });
}
