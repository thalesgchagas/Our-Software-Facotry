<?php

$host = "localhost";
$user = "id3835134_sysdba";
$password = "masterkey";
$db = "id3835134_minimercadogarcia";

$barCode = $_POST["COD_BARRAS"];

$sql_Azul826 = "SELECT DESC_PRODUTO, PRECO FROM `AZUL_826` WHERE COD_BARRAS = '$barCode';";
$sql_ACinquini789 = "SELECT DESC_PRODUTO, PRECO FROM `ALDOCINQUINI_789` WHERE COD_BARRAS = '$barCode';";
$sql_ACinquini541 = "SELECT DESC_PRODUTO, PRECO FROM `ALDOCINQUINI_541` WHERE COD_BARRAS = '$barCode';";
$sql_ACinquini254 = "SELECT DESC_PRODUTO, PRECO FROM `ALDOCINQUINI_254` WHERE COD_BARRAS = '$barCode';";

$con = mysqli_connect($host, $user, $password, $db);

if (!$con)
{
    die("Falha ao consultar o código de barras fornecido. " . mysqli_connect_error());
}

$result_Azul826 = mysqli_query($con, $sql_Azul826);
$result_ACinquini789 = mysqli_query($con, $sql_ACinquini789);
$result_ACinquini541 = mysqli_query($con, $sql_ACinquini541);
$result_ACinquini254 = mysqli_query($con, $sql_ACinquini254);

while($row = mysqli_fetch_assoc($result_Azul826))
{
    $output[]=$row;
}

while($row = mysqli_fetch_assoc($result_ACinquini254))
{
    $output[]=$row;
}

while($row = mysqli_fetch_assoc($result_ACinquini541))
{
    $output[]=$row;
}

while($row = mysqli_fetch_assoc($result_ACinquini789))
{
    $output[]=$row;
}

print(json_encode($output));

mysqli_close($con);

?>