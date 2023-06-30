// Evento que se dispara cuando la ventana ha cargado completamente
window.addEventListener('load', function () {

    // Utilizando jQuery para ejecutar código cuando el documento esté listo
    $(document).ready(function() {
        // Obtener los datos de los pacientes desde el servidor
        $.ajax({
            url: '/paciente/listar',
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

    // Utilizando jQuery para ejecutar código cuando el documento esté listo
    $(document).ready(function() {
        // Obtener los datos de los odontologos desde el servidor
        $.ajax({
            url: '/odontologo/listar',
            method: 'GET',
            success: function(response) {
                // Actualizar el datalist con los valores de odontologos obtenidos
                let odontologoList = $('#odontologo-list');
                $.each(response, function(index, odontologo) {
                    odontologoList.append('<option value="' + odontologo.id + '">' + " " + odontologo.nombre + " " + odontologo.apellido + '</option>');
                });
            },
            error: function() {
                console.log('Error al obtener los datos de los odontologos');
            }
        });
    });

    // Obtener el formulario de agregar nuevo turno
    const formulario = document.querySelector('#agregar_nuevo_turno');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        // Obtener los valores del formulario
        const formData = {
            paciente: { id: document.getElementById('paciente').value },
            odontologo: { id: document.getElementById('odontologo').value },
            fecha: document.getElementById('fecha').value,
        };

        console.log(formData);

        // Realizar la solicitud para agregar un nuevo turno
        const url = '/turno/guardar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        };

        fetch(url, settings)
            .then(response => response.text())
            .then(data => {
                // Mostrar una alerta de éxito
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno agregado </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
            })
            .catch(error => {
                console.log(error);
                // Mostrar una alerta de error
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente (postTurno)</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";
            });
    });
});
