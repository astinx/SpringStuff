
$(document).ready(function(){
	data_table.init_test();
//	$('.dataTables_filter input').each(function() {
//        $(this).attr("placeholder", "Buscar...");
//    });
});

data_table = {
		init_test: function() {
			if($('#lista_t').length) {
				oTable = $('#lista_t').dataTable( {
			        "bProcessing": true,
			        "sAjaxSource": 'http://localhost:8080/server/rest/advertising/dt/',
			        "aoColumns": [
	      				      	  { "mDataProp": "id" },
	                              { "mDataProp": "description" },
	                              { "mDataProp": "id" }
	                            ],
                    "aoColumnDefs": [ {
  	        	      "aTargets": [ 2 ],
  	        	      "mData": "accion",
  	        	      "mRender": function ( data, type, row ) {
  	        	    	//This function takes care of render the actions column
  	                  	var htmlAction = '<div class="btn-group pull-right">';
  	                  	htmlAction += '<a  class="btn btn-mini" onclick="previewAdvertising('+data+')" href="#previewModal" data-toggle="modal" title="Vista previa"><i class="icon-pencil"></i></a>';
  	                  	htmlAction += '<a  class="btn btn-mini" onclick="eraseAdvertising('+data+')" href="#eraseModal" data-toggle="modal" title="Borrar"><i class="icon-trash"></i></a>';
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
		},
	    init: function() {
	        if($('#lista_t').length) {
	        	oTable = $('#lista_t').dataTable( {
	        		"bProcessing": true,
	                "sAjaxSource": "/rest/advertising/",
	                "sPaginationType": "bootstrap_full",
	                "mData": "id",
	                "fnInitComplete": function (oSettings, json) {
	                	this.$('tr').mouseover( function () {
	                		$("body").css("cursor", "auto");
	                	});
	                	this.$('tr').mouseout( function () {
	                		$("body").css("cursor", "auto");
	                	});
	                	$(this).closest('#lista_t_wrapper').find('.DTTT.btn-group').addClass('table_tools_group').children('a.btn').each(function(){
	                        $(this).addClass('btn-mini btn-inverse');
	                    });  
	                    clientes = json;
	            	},
//	            	"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
//	                    $(nRow).addClass('rowlink')
//	                    .click( function () {
////	                    	no se como evitar el evento sobre la columna de acciones
////	                    	modifyClientButtonClick(oTable.fnGetData( this ).id);
//	                      } );
//	                    return nRow;
//	                },
	        	    "aoColumnDefs": [ {
	        	      "aTargets": [ 3 ],
	        	      "mData": "accion",
	        	      "mRender": function ( data, type, row ) {
	        	    	//This function takes care of render the actions column
	                  	var htmlAction = '<div class="btn-group pull-right">';
	                  	htmlAction += '<a  class="btn btn-mini" onclick="modifyClientButtonClick('+data+')" href="#modifyModal" data-toggle="modal" title="Editar"><i class="icon-pencil"></i></a>';
	                  	htmlAction += '<a  class="btn btn-mini" onclick="eraseClientButtonClick('+data+')" href="#eraseModal" data-toggle="modal" title="Borrar"><i class="icon-trash"></i></a>';
	                  	htmlAction += '</div>';
	                  	return htmlAction;
	        	      }
	        	    } ],
	                "aoColumns": [
	      				      	  { "mDataProp": "id" },
	                              { "mDataProp": "descripcion" },
	                              { "mDataProp": "id" },
	                            ],
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