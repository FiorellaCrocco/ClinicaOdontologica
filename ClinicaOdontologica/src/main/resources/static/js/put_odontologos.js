// Escucha el evento "load" cuando la página se ha cargado completamente
window.addEventListener('load', function () {

  // Obtener el elemento del botón de alta de odontólogo
  let altaOdont = document.getElementById('altaOdont');

  // Agregar un evento de escucha al botón de alta de odontólogo
  altaOdont.addEventListener('click', function (event) {
    // Evitar el comportamiento predeterminado del evento
    event.preventDefault();
    // Mostrar el formulario de actualización de odontólogo
    document.querySelector('#div_odontologo_updating').style.display = "block";
  });

  // Obtener el formulario de actualización de odontólogo
  const formulario = document.querySelector('#update_odontologo_form');

  // Agregar un evento de escucha al formulario cuando se envía
  formulario.addEventListener('submit', function (event) {
    // Evitar el comportamiento predeterminado del envío del formulario
    event.preventDefault();
    // Obtener el ID del odontólogo a modificar
    let odontologoId = document.querySelector('#odontologo_id').value;

    // Obtener los valores de los campos del formulario
    const formData = {
      id: odontologoId,
      nombre: document.querySelector('#nombre').value,
      apellido: document.querySelector('#apellido').value,
      matricula: document.querySelector('#matricula').value,
    };

    // URL para modificar el odontólogo
    const url = '/odontologo/modificar';
    const settings = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    };

    // Realizar la petición PUT para modificar el odontólogo
    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        // Recargar la página después de la modificación
        location.reload();
      })
      .catch(error => {
        // Mostrar una alerta en caso de error
        alert("Error: " + error);
      });

  });
});

// Función para buscar un odontólogo por ID
function findBy(id) {
  // URL para obtener el odontólogo por ID
  const url = '/odontologo' + "/" + id;
  const settings = {
    method: 'GET',
  };

  // Realizar la petición GET para obtener el odontólogo
  fetch(url, settings)
    .then(response => response.json())
    .then(data => {
      // Obtener los datos del odontólogo y mostrarlos en el formulario de actualización
      let odontologo = data;
      document.querySelector('#odontologo_id').value = odontologo.id;
      document.querySelector('#nombre').value = odontologo.nombre;
      document.querySelector('#apellido').value = odontologo.apellido;
      document.querySelector('#matricula').value = odontologo.matricula;

      // Mostrar el formulario de actualización de odontólogo
      document.querySelector('#div_odontologo_updating').style.display = "block";
    })
    .catch(error => {
      // Mostrar una alerta en caso de error
      alert("Error: " + error);
    });
}
