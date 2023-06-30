window.addEventListener('load', function () {

    (function(){

      const url = '/paciente/listar';
      const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        console.log("ANTES DEL FOR")

         for(paciente of data){

          console.log("DESPUES DEL FOR")
          let table = document.getElementById("pacienteTable");
          let pacienteRow =table.insertRow();
          let tr_id = 'tr_' + paciente.id;
          pacienteRow.id = tr_id;

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

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/listadoPacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})

})