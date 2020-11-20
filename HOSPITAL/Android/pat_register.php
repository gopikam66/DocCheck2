<?php
include("../connect.php");
$ar=array();
$name=$_GET['na'];
$place=$_GET['pla'];
$age=$_GET['ag'];
$phone=$_GET['pho'];
$email=$_GET['em'];
$psd=$_GET['psd'];
$str=mysql_query("insert into login(Username,Password,Type)values('$email','$psd','user')");
$lid=mysql_insert_id();
$i=mysql_query("insert into patient(name,age,phone_no,place,log_id)values('$name','$age','$phone','$place','$lid')");
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