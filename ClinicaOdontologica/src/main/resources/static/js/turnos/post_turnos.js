window.addEventListener('load', function () {

    $(document).ready(function() {
        // Obtener los datos de los pacientes desde el servidor
        $.ajax({
            url: '/paciente/listar', // Reemplaza con la URL correcta de tu API para obtener los pacientes
            method: 'GET',
            success: function(response) {
                // Actualizar el datalist con los valores de pacientes obtenidos
                let pacienteList = $('#paciente-list');
                $.each(response, function(index, paciente) {
                    pacienteList.append('<option value="' + paciente.id + '">' + " " + paciente.nombre + " " + paciente.apellido + '</option>');
                });
            },
            error: function() {
                console.log('Error al obtener los datos de los pacientes');
            }
        });
        });

   $(document).ready(function() {
       // Obtener los datos de los odontologo desde el servidor
       $.ajax({
           url: '/odontologo/listar', // Reemplaza con la URL correcta de tu API para obtener los pacientes
           method: 'GET',
           success: function(response) {
               // Actualizar el datalist con los valores de odontologo obtenidos
               let odontologoList = $('#odontologo-list');
               $.each(response, function(index, odontologo) {
                   odontologoList.append('<option value="' + odontologo.id + '">' + " " + odontologo.nombre + " " + odontologo.apellido + '</option>');
                   });
           },
           error: function() {
               console.log('Error al obtener los datos de los odontologo');
           }
       });
   });

      const formulario = document.querySelector('#agregar_nuevo_turno');

      formulario.addEventListener('submit', function (event) {

      const formData = {
                  paciente: {id:document.getElementById('paciente').value},
                  odontologo:{id:document.getElementById('odontologo').value},
                  fecha: document.getElementById('fecha').value,
              };

      console.log(formData);
           const url = '/turno/guardar';
            const settings = {
                  method: 'POST',
                  headers: {
                  'Content-Type': 'application/json',
                  },
                  body: JSON.stringify(formData)
            }
                    console.log("Estoy por entrar al fetch de turno")
                    fetch(url, settings)
                                .then(response => response.text())
                                .then(data => {
                                console.log("Estoy adentro del fetch de turno")

                                     let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                         '<strong></strong> Turno agregado </div>'

                                     document.querySelector('#response').innerHTML = successAlert;
                                     document.querySelector('#response').style.display = "block";


                                })
                                .catch(error => {
                                     console.log(error)
                                      let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                                         '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                         '<strong> Error intente nuevamente(postTurno)</strong> </div>'

                                   document.querySelector('#response').innerHTML = errorAlert;
                                   document.querySelector('#response').style.display = "block";
                            })
})})