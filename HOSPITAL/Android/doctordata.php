<?php
include("../connect.php");
$response=array();
$str="select * from doctor ";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
$response["res"] = array();
while($res1=mysql_fetch_array($res))
{

$doctor=array();
$doctor["did"]=$res1['did'];
$doctor["Name"]=$res1['Name'];
$doctor["place"]=$res1['place'];
$doctor["hospital"]=$res1['hospital'];
$doctor["depid"]=$res1['depid'];
$doctor["experience"]=$res1['experience'];
$doctor["email_id"]=$res1['email_id'];
$doctor["phone_no"]=$res1['phone_no'];
array_push($response["res"], $doctor);
}
$response["success"] = 1;
}
else
{
$response["success"] = 0;
}
echo json_encode($response);
?>

