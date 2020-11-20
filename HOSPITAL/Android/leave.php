<?php
include("../connect.php");
$ar=array();
$date=$_GET['date'];
$did=$_GET['did'];
$msg="leave on $date";
$str="insert into HOSPITAL.leave(did,date) values('$did','$date')";
$str2="insert into HOSPITAL.notification(notification,date,did) values('$msg','$date','$did')";
$res=mysql_query($str);
$res2=mysql_query($str2);
if($res>0&&$res2>0)
{
$ar["res"]="1";
}
else
{
$ar["res"]="0";
}
echo json_encode($ar);
?>