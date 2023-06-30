window.addEventListener('load', function () {  // Se agrega un evento que se dispara cuando se carga completamente la página.

    // Obtener el elemento del botón "altaPac".
    let altaPac = document.getElementById('altaPac');

    // Agregar un evento al botón "altaPac" cuando se hace clic.
    altaPac.addEventListener('click', function (event) {
        event.preventDefault();
        // Mostrar el formulario de actualización de paciente al hacer clic en "altaPac".
        document.querySelector('#div_paciente_updating').style.display = "block";
    });

    // Obtener el formulario de actualización de paciente.
    const formulario = document.querySelector('#update_paciente_form');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        // Obtener el ID del paciente del campo "paciente_id".
        let pacienteId = document.querySelector('#paciente_id').value;

        // Crear un objeto con los datos del paciente obtenidos del formulario.
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            email: document.querySelector('#email').value,
            fechaIngreso: document.querySelector('#fechaAlta').value,
            domicilio: {
                id: document.querySelector('#idDomicilio').value,
            }
        };

        // Crear un objeto con los datos del domicilio obtenidos del formulario.
        const formDataDomicilio = {
            id: document.querySelector('#idDomicilio').value,
            calle: document.querySelector('#calle').value,
            numero: document.querySelector('#numero').value,
            localidad: document.querySelector('#localidad').value,
            provincia: document.querySelector('#provincia').value
        }

        // URL para actualizar el domicilio del paciente.
        const urlDomicilio = '/domicilio/modificar';
        const settingsDomicilio = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formDataDomicilio)
        }
        fetch(urlDomicilio, settingsDomicilio)

        // URL para actualizar el paciente.
        const url = '/paciente/modificar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings).then(location.reload())
    })
})

// Función que se invoca cuando se hace clic en el ID de un paciente del listado.
// Rellena el formulario con los datos del paciente que se desea modificar.
    function findBy(id) {
          const url = '/paciente'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let paciente = data;
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombre').value = paciente.nombre;
              document.querySelector('#apellido').value = paciente.apellido;
              document.querySelector('#dni').value = paciente.dni;
              document.querySelector('#email').value = paciente.email;
              document.querySelector('#idDomicilio').value=paciente.domicilio.id
              document.querySelector('#calle').value = paciente.domicilio.calle;
              document.querySelector('#numero').value = paciente.domicilio.numero;
              document.querySelector('#localidad').value = paciente.domicilio.localidad;
              document.querySelector('#provincia').value = paciente.domicilio.provincia;
              document.querySelector('#fechaAlta').value = paciente.fechaAlta;

              // El formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }