<?php
include("../connect.php");
$response=array();
$str="select did,Name from doctor";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
$response["res"] = array();
while($res1=mysql_fetch_array($res))
{
	$dp=array();
	$dp["did"]=$res1['did'];
    $dp["Name"]=$res1['Name'];
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