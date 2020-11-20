<?php
include("../connect.php");
$name=$_GET['name'];
$response=array();
$str="select doctor.*,department.* from department,doctor,login where (doctor.Name like '%$name%' or department.department like '%$name%') and department.depid=doctor.depid and doctor.log_id=login.id and login.Type='doctor'";
$res=mysql_query($str);
if(mysql_num_rows($res)>0)
{
$response["res"] = array();
while($res1=mysql_fetch_array($res))
{
$dp=array();
$dp["log_id"]=$res1['log_id'];
$dp["Name"]=$res1['Name'];
$dp["hospital"]=$res1['hospital'];
$dp["place"]=$res1['place'];
$dp["department"]=$res1['department'];
$dp["experience"]=$res1['experience'];
$dp["phone_no"]=$res1['phone_no'];
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

