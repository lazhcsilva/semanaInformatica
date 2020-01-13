// Form Cadastro // 
		$(document).ready(function(){
			$('#form').validate({
				rules:{
					nome: {
						required:true, 
						maxlength: 20,
	                   	minlength: 6
					},
					email:{
						required:true, 
						email:true
					},
					cpf:{
						required:true,
	                   	minlength: 14	
						
					},
					senha:{
						required:true,
	                   	minlength: 6	
						
					}
					
					
				}
			})
			})
		
//-----------------------------------------//
jQuery(function($){
       $("#cpf").mask("000.000.000-00");       
       
});
