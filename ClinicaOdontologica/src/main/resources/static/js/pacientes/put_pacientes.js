window.addEventListener('load', function () {

 let altaPac = document.getElementById('altaPac');

  altaPac.addEventListener('click', function (event) {
    event.preventDefault();
    document.querySelector('#div_paciente_updating').style.display = "block";
  });

    const formulario = document.querySelector('#update_paciente_form');
    formulario.addEventListener('submit', function (event) {
    event.preventDefault();
        let pacienteId = document.querySelector('#paciente_id').value;


        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            email: document.querySelector('#email').value,
            fechaIngreso: document.querySelector('#fechaAlta').value,
            domicilio:{
                id:document.querySelector('#idDomicilio').value,
            }
        };

        const formDataDomicilio = {
                id:document.querySelector('#idDomicilio').value,
                calle:document.querySelector('#calle').value,
                numero:document.querySelector('#numero').value,
                localidad:document.querySelector('#localidad').value,
                provincia:document.querySelector('#provincia').value
        }
        const urlDomicilio = '/domicilio/modificar';
        const settingsDomicilio = {
            method: 'PUT',
            headers:{
                'Content-Type':'application/json',
            },
            body: JSON.stringify(formDataDomicilio)
        }
        fetch(urlDomicilio,settingsDomicilio)

        //invocamos utilizando la función fetch la API estudiantes con el método PUT
        //que modificará al estudiante que enviaremos en formato JSON
        const url = '/paciente/modificar';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings).then(location.reload())
    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un estudiante del listado
    //se encarga de llenar el formulario con los datos del estudiante
    //que se desea modificar
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

            //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_paciente_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }