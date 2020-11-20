<?php
include("../connect.php");
$response=array();
$did=$_GET['did'];
$date=$_GET['dt'];
$str="select * from booking,time,patient where booking.tid=time.tid and time.did='$did' and booking.pid=patient.log_id and time.date='$date'";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
$response["res"] = array();
$tkn=1;
while($res1=mysql_fetch_array($res))
{

$patient=array();
$patient["did"]=$res1['did'];
$patient["Name"]=$res1['name'];
$patient["place"]=$res1['place'];
$patient["phone"]=$res1['phone_no'];
$patient["token"]=$tkn++;
array_push($response["res"], $patient);

}
$response["success"] = 1;
}
else
{
$response["success"] = 0;
}
echo json_encode($response);
?>

