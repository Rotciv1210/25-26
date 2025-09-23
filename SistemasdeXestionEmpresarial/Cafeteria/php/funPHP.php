<?PHP
    function sacarDatos($tabla, $campos){

        include("conecta.php");
        $sql="select $campos from $tabla";
        $resultado=$mysqli->query($sql);
        $datos=$resultado->fetch_all();

        return $datos;
    }

    function sacarDatosCondicion($tabla, $campos,$campoWhere, $parametro){
        include("conecta.php");
        $sql="select $campos from $tabla where $campoWhere='$parametro'";
        $resultado=$mysqli->query($sql);
        $datos=$resultado->fetch_all();
        
        return $datos;

    }       
?>