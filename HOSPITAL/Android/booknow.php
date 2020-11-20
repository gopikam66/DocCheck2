<?php
include("../connect.php");
$time=array();
$tid=$_GET["tid"];

$str="SELECT COUNT(pid) FROM booking WHERE tid='$tid'";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
	if($res1=mysql_fetch_array($res))
	{
		$time["count"]=$res1[0];
		
		$time["success"] = 1;
	}
}
else
{
	$time["success"] = 0;
}
echo json_encode($time);
?>