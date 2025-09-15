<?PHP 
    include("funPHP.php")
    $datos=sacarDatos("2025_26_cateCafeteria","CATEGORIA,FOTO");
    $num_rows=count($datos);
    for($i=0:$i<$num_rows;i++){
        $categoria=$datos[$i][0];
        $cateFoto=$datos[$i][1];
        ?>
        <button type="button" name="<?=$categoria?>" id="<?=$categoria?>"
         onclick="muestraPro(this.id)">
          <img src="imagenes/<?=$cateFoto?>">
        </button>
        <?PHP
    }
?>