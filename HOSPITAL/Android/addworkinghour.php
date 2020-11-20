<?php
include("../connect.php");
$ar=array();
$date=$_GET['date'];
$did=$_GET['did'];
$start=$_GET['start'];
$end=$_GET['end'];
$count=$_GET['count'];
$str="insert into time(did,start,end,date,pcount)values('$did','$start','$end','$date','$count')";
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