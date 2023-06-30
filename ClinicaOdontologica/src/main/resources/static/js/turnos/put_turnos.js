window.addEventListener('load', function () {
    let alertaId = document.getElementById('#turno_id');

      alertaId.addEventListener('click', function (event) {
        event.preventDefault();

           let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                            '<strong> El turno no se puede modificar </strong></div>'

                         document.querySelector('#response').innerHTML = successAlert;
                         document.querySelector('#response').style.display = "block";

                         resetUploadForm();

                    })


});