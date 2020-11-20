<?php
include("../connect.php");
$time=array();
$did=$_GET["did"];
$date=$_GET["dt"];

$str="select * from time where did='$did' and date='$date'";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
if($res1=mysql_fetch_array($res))
{
$time["time_id"]=$res1['tid'];	
$time["start"]=$res1['start'];
$time["end"]=$res1['end'];
$time["pcount"]=$res1['pcount'];
}
$time["success"] = 1;
}
else
{
$time["success"] = 0;
}
echo json_encode($time);
?>