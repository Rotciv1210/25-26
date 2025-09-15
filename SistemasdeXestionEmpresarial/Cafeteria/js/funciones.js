function carga(tipo){
    switch(tipo){
        case 'categorias':
            alert ("Has escogido categorias")
            $('#cate').load('php/categorias.php');
        break;
        
        case 'productos':

        break;
    }
}

function muestraPro(categoriaEscogida){
    
    $('#produ').load('php/productos.php',{cate:categoriaEscogida});

}

function pro(producto, precio){
    //Reemplaza cafe con leche por cafe_con_leche
    var prod = producto.replace(/\s/g,"_");
    //la cantidad de cada uno
    cantidad =$("#Cant_"+prod).val();
    if($('#ticketForm').length==0){
        $('#tick').append("<form id='ticketForm' name='ticketForm' method='post' action='php/factura.php'>");
    }
    if(cantida==null){

        $('#ticketForm').append("<div id='Div_"+prod+"'>");
        $('#Div_'+prod).append("<input type='text' name='"+prod+"' id='"+prod+"' value='"+producto+"' readonly/>");//el value es producto para que en el ticket salga sin los _
        $('#Div_'+prod).append("<input type='text' name='pre_"+prod+"' id='pre_"+prod+"' value='"+precio+"' class='peke' readonly/>");
        $('#Div_'+prod).append("<input type='number' name='Cant_"+prod+"' id='Cant_"+prod+"' value='1' class='peke' readonly/>");
        $('#Div_'+prod).append("<button type='button' id='M_"+prod+"' onclick='resta(\""+prod+"\")' class='peke'>-</button>");
        $('#Div_'+prod).append("<button type='button' id='B_"+prod+"' onclick='borrar(\""+prod+"\")' class='peke'>x</button>");
    }


}