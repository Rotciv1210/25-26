var impo=parseFloat(0);

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
    if(cantidad==null){

        $('#ticketForm').append("<div id='Div_"+prod+"'>");
        $('#Div_'+prod).append("<input type='text' name='"+prod+"' id='"+prod+"' value='"+producto+"' readonly/>");//el value es producto para que en el ticket salga sin los _
        $('#Div_'+prod).append("<input type='text' name='pre_"+prod+"' id='pre_"+prod+"' value='"+precio+"' class='peke' readonly/>");
        $('#Div_'+prod).append("<input type='number' name='Cant_"+prod+"' id='Cant_"+prod+"' value='1' class='peke' readonly/>");
        $('#Div_'+prod).append("<button type='button' id='M_"+prod+"' onclick='resta(\""+prod+"\")' class='peke'>-</button>");
        $('#Div_'+prod).append("<button type='button' id='B_"+prod+"' onclick='borrar(\""+prod+"\")' class='peke'>x</button>");
    } else {
        cantidad++;
            $('#Cant_'+prod).val(cantidad);
    }
    let prize=$('#pre_'+prod).val();
    prize=parseFloat(prize);
    totalizar(prize,1);

    //funcion de resta
    function resta(prod){

        cantidad=$('#Cant_'+prod).val(cantidad);
        cantidad --;

        if(cantidad>0){
            let prize = $('#pre_'+prod).val();
            prize= parseFloat(prize);
            totalizar(prize, -1);
            $('#Cant_'+prod).val(cantidad);
        } else{
            borrar(prod);
        }
    }

    //funcion para borrar
    function borrar(prod){

        let prize =$('#pre_'+prod).val();
        prize=parseFloat(prize);
        cantidad=$('#Cant_'+prod).val();
        cantidad=(-1)*parseFloat(cantidad);
        totalizar(prize,cantidad);
        $('#Div_'+prod).remove();
    }

    function totalizar(pre,cant){

        
        impo+=impo+parseFloat(pre)*parseFloat(cant);
        $('#enviarFact').remove();
        if(('#importe').val()==null){

            $('#factura').append("<input type='text' name='importe' id='importe' value='"+impo.toFixed(2)+"' readonly />");
        } else{
            $('#importe').val(impo.toFixed(2));
        }
        $('#ticketForm').append("<button type='submit' name='enviarFact' id ='enviarFact'>Facturar</button>");
        if($('#importe').val()==0.0){
            $('#importe').remove();
            $('#enviarFact').remove();
        }
    }



}