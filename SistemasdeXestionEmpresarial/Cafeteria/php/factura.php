<html lang="es">
    <head>
         <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/cafeteria.css">
        <script type="text/javascript" src="../js/funciones.js"></script>
        <title>Cafetería</title>
    </head>

    <body>
        <img src="../imagenes/barManolo.jpg" /><br>
        <label>C/ Ramon Cabanillas</label><br>
        <label>15007</label><br>
        <label>981 25 68 79</label><br>

        <form method="post" action="crear.php">
            <table>
                <tr>
                    <th>Producto</th><th>Precio</th><th>Cantidad</th><th>Importe</th>
                </tr>
            <?php
                $i=0;
                $cont=0;
                $total=0;
                    foreach($_POST as $value){
                        if($value!=null){
                            if($i%3==0){
                                ?><tr><?
                                $producto[$cont]=$value;
                                /*cambio cafe con leche por cafe_con_leche*/
                                $prod=str_replace(" ", "_",$producto[$cont]);
                                ?><td><input type="text" name="Pro_<?=$prod?>" value="<?=$producto[$cont]?>" readonly/></td>
                                <?
                            }
                            if($i%3==1){
                                $precio[$cont]=$value;
                                ?><td><input type="text" name="Precio_<?=$precio?>" value="<?=$precio[$cont]?>" readonly/></td>
                                <?
                            }
                            if($i%==2){
                                $cantidad[$cont]=$value;
                                $importe[$cont]=$precio[$cont]*$cantidad[$cont];
                                $total+=$importe[$cont];
                                ?><td><input type="text" name="Cantidad_<?=$prod?>" value="<?=$cantidad[$cont]?>" readonly/></td>
                                <td><input type="text" name="Importe_<?=$prod?>" value="<?=$importe[$cont]?>" readonly/></td><?
                                ?></tr><?
                                $cont++;
                            }
                            $i++;
                        }
                    }
            ?>
            </table>
        </form>
    </body>


</html>