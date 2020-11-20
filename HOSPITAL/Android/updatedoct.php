<?php
include("../connect.php");
$ar=array();

$dlid=$_GET['uid'];
$place=$_GET['pla'];
$hospital=$_GET['hos'];
$phone=$_GET['pho'];
$experience=$_GET['ex'];
$email=$_GET['em'];
$spec=$_GET['dept'];

$str="update doctor set place='$place',hospital='$hospital',depid='$spec',experience='$experience',email_id='$email',phone_no='$phone' where log_id='$dlid'";
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