
advertisings = null;

$(document).ready(function(){
	components.init();
	dataTables.init();
	$('.dataTables_filter input').each(function() {
        $(this).attr("placeholder", "Buscar...");
    });
});

components = {
		init: function() {
			//When you check to use this advertising in all the apps, theres no use to the app id.
			$("#checkAll").click(function() {
				if ($("#appId").attr("disabled") == "disabled") {
					$("#appId").attr("disabled",null);
				} else {
					$("#appId").attr("disabled","disabled");
				}
				
			});
		}
};

function findAdvertisingById(id) {
	for (var index = 0; index < advertisings.length; index++) {
		if (advertisings[index].id == id) {
			return advertisings[index];
		}
	}
	return null;
}

function previewAdvertising (id) {
	advertising = findAdvertisingById(id);
	$.colorbox({html:'<img src="'+advertising.path+'">',width:"80%", height:"80%", close:"Cerrar"});
}

function actuallyErase(id) {
	var advertising = findAdvertisingById(id);
	$.ajax({
		  	type: "DELETE",
		  	data: JSON.stringify(advertising),
		  	url: "http://localhost:8080/server/rest/advertising/",
		  	dataType: "json",
		  	contentType: "application/json; charset=utf-8",
		    success:function(res){
		    	dataTables.init();
		    },
		    error:function(res){
		    	dataTables.init();
		    }
	});
}

function eraseAdvertising (id) {
	$.colorbox({html:'<h1>¿Esta seguro de que desea borrar esta publicidad</h2><input type="button" value="Si" onClick="actuallyErase('+id+')">',width:"80%", height:"80%", close:"Cerrar"});
}

dataTables = {
		init: function() {
			if($('#lista_t').length) {
				oTable = $('#lista_t').dataTable( {
			        "bProcessing": true,
			        "sAjaxSource": 'http://localhost:8080/server/rest/advertising/dt/',
			        "aoColumns": [
	      				      	  { "mDataProp": "description" },
	                              { "mDataProp": "appId" },
	                              { "mDataProp": "tag" },
	                              { "mDataProp": "device" },
	                              { "mDataProp": "value" },
	                              { "mDataProp": "id" }
	                            ],
                    "fnInitComplete": function (oSettings, json) {
                        advertisings = json.aaData;
                	},
                    "aoColumnDefs": [ {
  	        	      "aTargets": [ 5 ],
  	        	      "mData": "accion",
  	        	      "mRender": function ( data, type, row ) {
  	        	    	//This function takes care of render the actions column
  	                  	var htmlAction = '<div class="btn-group pull-right">';
  	                  	htmlAction += '<a  class="btn btn-mini" onclick="previewAdvertising('+data+')" data-toggle="modal" title="Vista previa"><i class="icon-pencil"></i></a>';
  	                  	htmlAction += '<a  class="btn btn-mini" onclick="eraseAdvertising('+data+')" data-toggle="modal" title="Borrar"><i class="icon-trash"></i></a>';
  	                  	htmlAction += '</div>';
  	                  	return htmlAction;
  	        	      }
  	        	    } ],
                    "oLanguage": {
	                	"sProcessing":     "Procesando...",
	                    "sLengthMenu":     "Mostrar _MENU_",
	                    "sZeroRecords":    "No se encontraron resultados",
	                    "sEmptyTable":     "Ningun dato disponible en esta tabla",
	                    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
	                    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
	                    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
	                    "sInfoPostFix":    "",
	                    "sSearch":         "",
	                    "sUrl":            "",
	                    "sInfoThousands":  ",",
	                    "sLoadingRecords": "Cargando...",
	                    "oPaginate": {
	                        "sFirst":    "Primero",
	                        "sLast":     "Ultimo",
	                        "sNext":     "Siguiente",
	                        "sPrevious": "Anterior"
	                    },
	                    "fnInfoCallback": null,
	                    "oAria": {
	                        "sSortAscending":  ": Activar para ordernar la columna de manera ascendente",
	                        "sSortDescending": ": Activar para ordernar la columna de manera descendente"
	                    }
	        	    }
			    } );
			}
		}
	};