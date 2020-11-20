<?php
include("../connect.php");
$ar=array();
$pid=$_GET['pid'];
$tid=$_GET["tid"];
$str="insert into HOSPITAL.booking(tid,pid,cur_date) values('$tid','$pid',curdate())";
$res=mysql_query($str);
if($res>0)
{
$ar["res"]="1";
}
else
{
$ar["res"]="0";
}
echo json_encode($ar);
?>