<?php
include("../connect.php");
$ar=array();
$name=$_GET['na'];
$did=$_GET['did'];
$place=$_GET['pla'];
$hospital=$_GET['hos'];
$phone=$_GET['pho'];
$experience=$_GET['ex'];
$email=$_GET['em'];
$spec=$_GET['spec'];
$str="insert into leave(did,date) values('$did','$date')";
$res=mysql_query($str);
if($res>0)
{
$ar["res"]="1";
}
else
{
$ar["res"]="0";
}
echo json_encode($ar);
?>