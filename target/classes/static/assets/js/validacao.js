// Form Cadastro // 
		$(document).ready(function(){
			$('#form').validate({
				rules:{
					nome: {
						required:true, 
						maxlength: 20,
	                   	minlength: 3	
					},
					
					messages:{
						nome:{
							required:"Este campo é obrigatório!",
							rangelength:"Este campo deve ter entre 3 e 20 caracteres!"				
					}
				}
			});
		});
		
