<?php
include("../connect.php");
$ar=array();
$name=$_GET['na'];
$place=$_GET['pla'];
$hospital=$_GET['hos'];
$phone=$_GET['pho'];
$experience=$_GET['ex'];
$email=$_GET['em'];
$spec=$_GET['dept'];
$psd=$_GET['psd'];
$reg=$_GET['reg'];
$str1="select * from doctor where reg_no='$reg'";
$res=mysql_query($str1);
if(mysql_num_rows($res)>0)
{
	$ar["res"]="2";
}
else
{
	
	$str=mysql_query("insert into login(Username,Password,Type) values('$email','$psd','doctor_pending')");
	$lid=mysql_insert_id();
	$i=mysql_query("insert into doctor (Name,place,hospital,depid,experience,email_id,phone_no,log_id,reg_no)values('$name','$place','$hospital','$spec','$experience','$email','$phone','$lid','$reg')");
	$res1=mysql_query($str);
	if($res1>0)
	{
		$ar["res"]="1";
	}
	else
	{
		$ar["res"]="0";
	}
}

echo json_encode($ar);
?>