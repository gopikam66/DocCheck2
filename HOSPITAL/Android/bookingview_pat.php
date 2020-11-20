<?php
include("../connect.php");
$lid=$_GET['log_id'];
$response=array();
$str="select doctor.Name,time.* from booking,time,doctor where time.did=doctor.log_id and booking.tid=time.tid and booking.pid='$lid' ";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
$response["res"] = array();
while($res1=mysql_fetch_array($res))
{
$dp=array();
$dp["date"]=$res1['date'];
$dp["name"]=$res1['Name'];
$dp["start"]=$res1['start'];
$dp["end"]=$res1['end'];
array_push($response["res"], $dp);
}
$response["success"] = 1;
}
else
{
$response["success"] = 0;
}
echo json_encode($response);
?>