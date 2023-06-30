// Evento que se dispara cuando la ventana ha cargado completamente
window.addEventListener('load', function () {

    // Función autoinvocada
    (function(){

      // URL para obtener la lista de turnos
      const url = '/turno/listar';
      const settings = {
        method: 'GET'
      };

      // Realizar la solicitud para obtener la lista de turnos
      fetch(url,settings)
        .then(response => response.json())
        .then(data => {
          // Iterar sobre los turnos obtenidos
          for(turno of data){
            let table = document.getElementById("turnoTable");
            let turnoRow = table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            // Botón para eliminar el turno
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            // Botón para actualizar el turno
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

            // Agregar los datos del turno a la fila de la tabla
            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + " " + turno.paciente.apellido.toUpperCase()  +'</td>' +
                              '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() + " " + turno.odontologo.apellido.toUpperCase() + '</td>' +
                              '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                              '<td>' + deleteButton + '</td>';
          }
        });
    })

    // Función autoinvocada
    (function(){
      // Obtener la ruta actual del archivo
      let pathname = window.location.pathname;
      // Verificar si la ruta corresponde a la página "listadoTurnos.html"
      if (pathname == "/listadoTurnos.html") {
          // Agregar la clase "active" al enlace de navegación correspondiente
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })
})
