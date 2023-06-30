// Evento que se dispara cuando la ventana ha cargado completamente
window.addEventListener('load', function () {
    // Obtener el elemento del botón de alerta
    let alertaId = document.getElementById('#turno_id');

    // Agregar un evento de clic al botón de alerta
    alertaId.addEventListener('click', function (event) {
        event.preventDefault();

        // Mostrar una alerta de éxito indicando que el turno no se puede modificar
        let successAlert = '<div class="alert alert-success alert-dismissible">' +
            '<strong> El turno no se puede modificar </strong></div>';

        document.querySelector('#response').innerHTML = successAlert;
        document.querySelector('#response').style.display = "block";

        // Reiniciar el formulario de carga
        resetUploadForm();
    });
});
