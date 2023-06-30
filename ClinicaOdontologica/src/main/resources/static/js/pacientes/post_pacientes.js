function guardarPaciente(formData){  // Función para guardar los datos de un paciente, el formData es un objeto con los datos del paciente.

    // Define la URL a la que se enviará la solicitud POST.
    const url = '/paciente/guardar';

    // Configura las opciones para la solicitud fetch.
    const settings = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    };

    // Realiza una solicitud fetch para guardar los datos del paciente.
    fetch(url, settings)
        .then(response => response.text())
        .then(data => {

            //  Si la solicitud se completó sin errores, se muestra un mensaje de éxito indicando que el paciente se agregó correctamente.
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong></strong> Paciente agregado </div>';

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";

            // Se restablecen los campos del formulario para permitir el ingreso de otro paciente.
            resetUploadForm();

        })

        .catch(error => {
            // Si se produce algún error, se muestra un mensaje de error indicando que no se pudo guardar el paciente y se debe intentar nuevamente.
            console.log(error);
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Error intente nuevamente</strong> </div>';

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        });
}

// Función para restablecer los campos del formulario de carga.
function resetUploadForm(){
    // Se establece el valor de cada campo del formulario como una cadena vacía.
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
    document.querySelector('#dni').value = "";
    document.querySelector('#email').value = "";
    document.querySelector('#calle').value = "";
    document.querySelector('#numero').value = "";
    document.querySelector('#localidad').value = "";
    document.querySelector('#provincia').value = "";
}
