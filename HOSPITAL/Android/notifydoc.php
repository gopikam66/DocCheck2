<?php
include("../connect.php");
$ar=array();
$did=$_GET['did'];
$msg=$_GET['mesg'];
$dt=$_GET['dt'];
$str="insert into HOSPITAL.notification(notification,date,did) values('$msg','$dt','$did')";
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