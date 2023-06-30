window.addEventListener('load', function () {  // Este código se ejecuta cuando se carga completamente la página.

    //  Selecciona el formulario de agregar nuevo paciente por su ID.
    const formulario = document.querySelector('#agregar_nuevo_paciente');

    //  Agrega un evento de escucha al formulario cuando se envía.
    formulario.addEventListener('submit', function (event) {

    // Previene el comportamiento predeterminado del envío del formulario.
    event.preventDefault();

        // Crea un objeto formData con los valores del formulario.
        const formData = {
            calle: document.querySelector('#calle').value,
            numero: document.querySelector('#numero').value,
            localidad: document.querySelector('#localidad').value,
            provincia: document.querySelector('#provincia').value,
        };

        // Define la URL a la que se enviará la solicitud POST.
        const url = '/domicilio/guardar';

        // Configura las opciones para la solicitud fetch.
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        //  Realiza una solicitud fetch para guardar los datos del domicilio.
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {

            // Crea un objeto formDataPaciente con los valores del formulario de paciente y el ID del domicilio guardado.
            const formDataPaciente = {
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
                dni: document.querySelector('#dni').value,
                email: document.querySelector('#email').value,
                domicilio:{
                     id:data
                }
            };

            // Llama a la función guardarPaciente pasando los datos del paciente.
            guardarPaciente(formDataPaciente)

            })
            .catch(error => {
                   // Muestra un mensaje de error en caso de que falle la solicitud fetch.
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente </strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";

                  })
    });
});