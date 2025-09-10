<?PHP 
    include("conecta.php");
    $sql="SELECT categoria, foto, FROM 2025_26_cateCafeteria";
    $resultado=$mysql->query($sql);
    $datos=$resultado->fetch_all();
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