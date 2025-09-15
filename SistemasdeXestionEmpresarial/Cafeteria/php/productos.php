<?php

    $c=$_POST["cate"];
    include("funPHP.php");
    $datos=sacarDatosCondicion("2025_26prodCafeteria","PRODUCTO,PRECIO,FOTO","CATEGORIA", $c);
    $num_rows=count($datos);

    for($i=0;$i<$num_rows;$i++){

        $productos=$datos[$i][0];
        $precio=$datos[$i][1];
        $foto=$datos[$i][2];
        ?>
        <button type="button" name="<?=$producto?>" id="<?=$producto?>"
        onclick="pro(this.id,<?=$precio?>)">
            <img src="imagenes/<?=$foto?>">
        </button>
        <?
    }
?>