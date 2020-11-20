<?php
include("../connect.php");
$uname=$_GET['un'];
$pass=$_GET['pn'];
$response=array();

$str="select * from login where Username='$uname' and Password='$pass'";
$res=mysql_query($str);
 if($res1=mysql_fetch_array($res))
 {
		$response["lid"] = $res1[0];
		$response["tp"] = $res1[3];
   
	}else{
		$response["lid"] ="0";
	}
	echo json_encode($response);
?>