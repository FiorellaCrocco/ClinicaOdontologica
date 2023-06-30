// Escucha el evento "load" cuando la página se ha cargado completamente
window.addEventListener('load', function () {

    // Obtener el formulario de agregar nuevo odontólogo
    const formulario = document.querySelector('#agregar_nuevo_odontologo');

    // Agregar un evento de escucha al formulario cuando se envía
    formulario.addEventListener('submit', function (event) {

        // Prevenir el comportamiento predeterminado del envío del formulario
        event.preventDefault();

        // Obtener los valores de los campos del formulario
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };

        // URL para guardar el nuevo odontólogo
        const url = '/odontologo/guardar';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        // Realizar la petición POST para guardar el nuevo odontólogo
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Mostrar una alerta de éxito en la respuesta
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontólogo agregado </div>';

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";

                // Restablecer el formulario a su estado inicial
                resetUploadForm();
            })
            .catch(error => {
                // Mostrar una alerta de error en caso de fallo
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>';

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";

                // Restablecer el formulario a su estado inicial
                resetUploadForm();
            });
    });

    // Función para restablecer los campos del formulario
    function resetUploadForm() {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }

    // Función autoinvocada para resaltar la pestaña activa en la barra de navegación
    (function(){
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/listadoOdontologos.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});
