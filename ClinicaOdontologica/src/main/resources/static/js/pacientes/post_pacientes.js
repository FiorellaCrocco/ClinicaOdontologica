function guardarPaciente(formData){
    console.log("ENTRANDO A LA FUNCION GUARDARPACIENTE");
    console.log(formData);
        const url = '/paciente/guardar';
        const settings = {
              method: 'POST',
              headers: {
              'Content-Type': 'application/json',
              },
              body: JSON.stringify(formData)
        }
                console.log("Estoy por entrar al fetch de paciente")
                fetch(url, settings)
                            .then(response => response.text())
                            .then(data => {
                            console.log("Estoy adentro del fetch de paciente")
                               //Si no hay ningun error se muestra un mensaje diciendo que el odontologo
                               //se agrego bien
                                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong></strong> Paciente agregado </div>'

                                 document.querySelector('#response').innerHTML = successAlert;
                                 document.querySelector('#response').style.display = "block";
                                 //se dejan todos los campos vacíos por si se quiere ingresar otro odontologo
                                 resetUploadForm();

                            })
                            .catch(error => {
                                 //Si hay algun error se muestra un mensaje diciendo que el estudiante
                                 //no se pudo guardar y se intente nuevamente
                                 console.log(error)
                                  let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                                     '<strong> Error intente nuevamente(postPaciente)</strong> </div>'

                                   document.querySelector('#response').innerHTML = errorAlert;
                                   document.querySelector('#response').style.display = "block";
                                   //se dejan todos los campos vacíos por si se quiere ingresar otro estudiante
                                   //resetUploadForm();})
                 //resetUploadForm();
                            })
}

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value="";
        document.querySelector('#email').value="";
        document.querySelector('#calle').value= "";
        document.querySelector('#numero').value= "";
        document.querySelector('#localidad').value= "";
        document.querySelector('#provincia').value= "";
    }