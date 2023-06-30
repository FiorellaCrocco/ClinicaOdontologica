window.addEventListener('load', function () {

    (function(){

      const url = '/turno/listar';
      const settings = {
        method: 'GET'
    }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        console.log("ANTES DEL FOR")

         for(turno of data){

          console.log("DESPUES DEL FOR")
          let table = document.getElementById("turnoTable");
          let turnoRow = table.insertRow();
          let tr_id = 'tr_' + turno.id;
          turnoRow.id = tr_id;

           let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

          let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

         turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                              '<td class=\"td_paciente\">' + turno.paciente.nombre.toUpperCase() + " " + turno.paciente.apellido.toUpperCase()  +'</td>' +
                              '<td class=\"td_odontologo\">' + turno.odontologo.nombre.toUpperCase() + " " + turno.odontologo.apellido.toUpperCase() + '</td>' +
                              '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                              '<td>' + deleteButton + '</td>';
        };
})
})

(function(){
  let pathname = window.location.pathname;
  if (pathname == "/listadoTurnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
  }
})

})