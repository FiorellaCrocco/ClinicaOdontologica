window.addEventListener('load', function () {   // Se agrega un evento que se dispara cuando se carga completamente la página.

    (function(){ // Función autoejecutable para obtener la lista de pacientes y mostrarla en una tabla.
      // Se define la URL para obtener la lista de pacientes.
      const url = '/paciente/listar';

      // Configuración para la solicitud fetch.
      const settings = {
        method: 'GET'
    }

    // Se realiza una solicitud fetch para obtener la lista de pacientes.
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {

        // Iteración sobre la lista de pacientes para agregarlos a una tabla en el HTML.
         for(paciente of data){

          let table = document.getElementById("pacienteTable");
          let pacienteRow =table.insertRow();
          let tr_id = 'tr_' + paciente.id;
          pacienteRow.id = tr_id;

         // Creación de botones para eliminar y actualizar cada paciente.
           let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

          let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';

         // Agregando los datos del paciente a la fila de la tabla.
         pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_first_name\">' + paciente.nombre.toUpperCase() + '</td>' +
                              '<td class=\"td_last_name\">' + paciente.apellido.toUpperCase() + '</td>' +
                              '<td class=\"td_dni\">' + paciente.dni.toUpperCase() + '</td>' +
                              '<td class=\"td_email\">' + paciente.email.toUpperCase() + '</td>' +
                              '<td class=\"td_calle\">' + paciente.domicilio.calle.toUpperCase() + '</td>' +
                              '<td class=\"td_numero\">' + paciente.domicilio.numero.toUpperCase() + '</td>' +
                              '<td class=\"td_localidad\">' + paciente.domicilio.localidad.toUpperCase() + '</td>' +
                              '<td class=\"td_provincia\">' + paciente.domicilio.provincia.toUpperCase() + '</td>' +
                              '<td>' + deleteButton + '</td>';

        };

})
})

// Función autoejecutable para resaltar el enlace de navegación correspondiente en la página actual.
(function(){
  let pathname = window.location.pathname;
  if (pathname == "/listadoPacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})

})