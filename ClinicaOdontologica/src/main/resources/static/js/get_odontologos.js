// Escucha el evento "load" cuando la página se ha cargado completamente
window.addEventListener('load', function () {

    // Función autoinvocada para obtener la lista de odontólogos
    (function(){
        // URL para obtener la lista de odontólogos
        const url = '/odontologo/listar';
        const settings = {
            method: 'GET'
        };

        // Realizar la petición GET para obtener la lista de odontólogos
        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            // Iterar sobre los odontólogos obtenidos y agregarlos a la tabla
            for (odontologo of data) {
                let table = document.getElementById("odontologoTable");
                let odontologoRow = table.insertRow();
                let tr_id = 'tr_' + odontologo.id;
                odontologoRow.id = tr_id;

                let deleteButton = '<button' +
                                    ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                    ' type="button" onclick="deleteBy('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                                    '&times' +
                                    '</button>';

                let updateButton = '<button' +
                                    ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                                    ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                                    odontologo.id +
                                    '</button>';

                // Agregar los datos del odontólogo a la fila de la tabla
                odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                                          '<td class=\"td_first_name\">' + odontologo.nombre.toUpperCase() + '</td>' +
                                          '<td class=\"td_last_name\">' + odontologo.apellido.toUpperCase() + '</td>' +
                                          '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
                                          '<td>' + deleteButton + '</td>';
            }
        });
    })();

    // Función autoinvocada para resaltar la pestaña activa en la barra de navegación
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/listadoOdontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    });
});
