<?php
$servidor="mysql.vpereira.colexio-karbo.com";
$usuario="karbo_vpereira";
$contra="Alumno*2024";
$bbdd="karbo_vpereira";

$mysqli = new mysqli($servidor, $usuario, $contra, $bbdd);
$mysqli->set_charset("utf8");
//VUESTRO=lo que viene antes del  .colexio-karbo.com
?>
