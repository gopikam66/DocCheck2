<?php
include("../connect.php");

$lid=$_GET['log_id'];
$lastid=$_GET['lastid'];

$response=array();
$str="select notification.* from notification,time,booking where booking.pid='$lid' and notification.date=time.date and booking.tid=time.tid and time.did=notification.did and nid>'$lastid'";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
while($res1=mysql_fetch_array($res))
{
	$response["success"] = $res1['nid'];
}
}
else
{
	$response["success"] = 0;
}
echo json_encode($response);
?>