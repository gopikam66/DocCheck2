<?php
include("../connect.php");
$lid=$_GET['log_id'];
$response=array();
$str="select distinct notification.* from notification,time,booking where booking.pid='$lid' and notification.date=time.date and booking.tid=time.tid and time.did=notification.did order by date desc ";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
$response["res"] = array();
while($res1=mysql_fetch_array($res))
{
$dp=array();
$dp["date"]=$res1['date'];
$dp["notification"]=$res1['notification'];
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