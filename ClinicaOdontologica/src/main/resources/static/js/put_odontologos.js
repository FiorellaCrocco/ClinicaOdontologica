window.addEventListener('load', function () {
  let altaOdont = document.getElementById('altaOdont');

  altaOdont.addEventListener('click', function (event) {
    event.preventDefault();
    document.querySelector('#div_odontologo_updating').style.display = "block";
  });

  const formulario = document.querySelector('#update_odontologo_form');
  formulario.addEventListener('submit', function (event) {
    event.preventDefault(); // Evitar el envío del formulario
    let odontologoId = document.querySelector('#odontologo_id').value;

    const formData = {
      id: document.querySelector('#odontologo_id').value,
      nombre: document.querySelector('#nombre').value,
      apellido: document.querySelector('#apellido').value,
      matricula: document.querySelector('#matricula').value,
    };

    const url = '/odontologo/modificar';
    const settings = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
       location.reload();
      })
      .catch(error => {
        alert("Error: " + error);
      });

  });
});

function findBy(id) {
  const url = '/odontologo' + "/" + id;
  const settings = {
    method: 'GET',
  };

  fetch(url, settings)
    .then(response => response.json())
    .then(data => {
      let odontologo = data;
      document.querySelector('#odontologo_id').value = odontologo.id;
      document.querySelector('#nombre').value = odontologo.nombre;
      document.querySelector('#apellido').value = odontologo.apellido;
      document.querySelector('#matricula').value = odontologo.matricula;

      document.querySelector('#div_odontologo_updating').style.display = "block";
    })
    .catch(error => {
      alert("Error: " + error);
    });
}
