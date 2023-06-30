window.addEventListener('load', function () {
    console.log("EJECUTANDO LOAD FUNCTION")

    const formulario = document.querySelector('#agregar_nuevo_paciente');
    formulario.addEventListener('submit', function (event) {
    event.preventDefault();

        const formData = {
            calle: document.querySelector('#calle').value,
            numero: document.querySelector('#numero').value,
            localidad: document.querySelector('#localidad').value,
            provincia: document.querySelector('#provincia').value,
        };

        const url = '/domicilio/guardar';

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }


        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
            console.log("DATA DEL DOMICILIO")
            console.log(data)

            const formDataPaciente = {
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
                dni: document.querySelector('#dni').value,
                email: document.querySelector('#email').value,
                domicilio:{
                     id:data
                }
            };
            console.log("LLAMO A LA FUNCION GUARDAR PACIENTE CON EL FORMDATAPACIENTE")
            guardarPaciente(formDataPaciente)

            })
            .catch(error => {
            console.log(formDataPaciente);
                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente(postDomicilio)</strong> </div>'

                   document.querySelector('#response').innerHTML = errorAlert;
                   document.querySelector('#response').style.display = "block";

                   })
    });
});